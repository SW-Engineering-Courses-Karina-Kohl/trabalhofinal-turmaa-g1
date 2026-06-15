package br.edu.ufrgs.controller;

import br.edu.ufrgs.model.Prescricao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/upload")
@MultipartConfig
public class ServletMedia extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Prescricao> lista = new ArrayList<>();
        Part partPresc = request.getPart("file");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(partPresc.getInputStream()))) {
            String linha = br.readLine(); // pula cabeçalho
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;

                String[] campos = linha.split(",", -1);
                String alergias = campos.length > 5 ? campos[5].trim() : "";

                Prescricao pr = new Prescricao(
                    campos[0].trim(),
                    campos[1].trim(),
                    campos[2].trim(),
                    campos[3].trim(),
                    Double.parseDouble(campos[4].trim()),
                    alergias
                );
                lista.add(pr);
            }
        }

        aplicarRegrasSeguranca(lista);

        request.getSession().setAttribute("prescricoes", lista);
        request.setAttribute("prescricoes", lista);
        request.getRequestDispatcher("Resultado.jsp").forward(request, response);
    }

    private void aplicarRegrasSeguranca(List<Prescricao> lista) {

        // REGRA 1 — Varfarina + Aspirina no mesmo paciente
        for (Prescricao a : lista) {
            for (Prescricao b : lista) {
                if (a != b && a.getIdPaciente().equals(b.getIdPaciente())) {
                    String medA = a.getMedicamento();
                    String medB = b.getMedicamento();
                    if ((medA.equalsIgnoreCase("Varfarina") && medB.equalsIgnoreCase("Aspirina"))
                     || (medA.equalsIgnoreCase("Aspirina")  && medB.equalsIgnoreCase("Varfarina"))) {
                        a.ativarAlerta("Interacao Perigosa (Varfarina + Aspirina)");
                    }
                }
            }
        }

        // REGRA 2 — Peso < 20kg e dosagem > 500mg
        for (Prescricao p : lista) {
            int dose = Integer.parseInt(p.getDosagem_mg().replaceAll("[^0-9]", ""));
            if (p.getPeso_paciente() < 20 && dose > 500) {
                p.ativarAlerta("Dosagem Alta para Peso Infantil");
            }
        }

        // REGRA 3 — Paciente alergico ao medicamento prescrito
        for (Prescricao p : lista) {
            String alergias = p.getAlergias();
            if (alergias == null || alergias.isEmpty()) continue;

            String[] listaAlergias = alergias.split(";");
            for (String alergia : listaAlergias) {
                if (alergia.trim().equalsIgnoreCase(p.getMedicamento())) {
                    p.ativarAlerta("Paciente Alergico a " + p.getMedicamento());
                    break;
                }
            }
        }
    }
}