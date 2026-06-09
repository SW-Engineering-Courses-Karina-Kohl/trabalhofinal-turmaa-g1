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
@MultipartConfig   //  NECESSÁRIO PARA UPLOAD DE ARQUIVO
public class ServletMedia extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ---------- LER CSV ----------
        List<Prescricao> lista = new ArrayList<>();
        Part partPresc = request.getPart("file");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(partPresc.getInputStream()))) {
            String linha = br.readLine(); // pula cabeçalho
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;

                String[] campos = linha.split(",", -1);
                String alergiaStr = (campos.length >= 6) ? campos[5].trim() : "";

                Prescricao pr = new Prescricao(
                    campos[0].trim(),
                    campos[1].trim(),
                    campos[2].trim(),
                    campos[3].trim(),
                    Double.parseDouble(campos[4].trim()),
                    alergiaStr
                );
                lista.add(pr);
            }
        } // try-with-resources fecha sozinho, não precisa de br.close()

        // ---------- APLICAR REGRAS ----------
        aplicarRegrasSeguranca(lista);

        // ---------- ENVIAR PARA JSP ----------
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
            // remove "mg" da string e converte para número
            int dose = Integer.parseInt(p.getDosagem_mg().replaceAll("[^0-9]", ""));
            if (p.getPeso_paciente() < 20 && dose > 500) {
                p.ativarAlerta("Dosagem alta para paciente com peso inferior a 20kg.");
            }
        }

        // REGRA 3 — Alergia (vem do próprio CSV)
        for (Prescricao p : lista) {
            String alergiasStr = p.getAlergia();
            if (alergiasStr != null && !alergiasStr.isEmpty()) {
                String[] alergias = alergiasStr.split(";");
                for (String medAlergico : alergias) {
                    if (medAlergico.trim().equalsIgnoreCase(p.getMedicamento())) {
                        p.ativarAlerta("Alergia do Paciente ao " + p.getMedicamento());
                    }
                }
            }
        }
    }
}