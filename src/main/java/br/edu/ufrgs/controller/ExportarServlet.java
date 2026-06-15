package br.edu.ufrgs.controller;

import br.edu.ufrgs.model.Prescricao;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/exportar")
public class ExportarServlet extends HttpServlet {

    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Prescricao> lista = (List<Prescricao>) request.getSession().getAttribute("prescricoes");
        if (lista == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=prescricoes_auditadas.csv");

        PrintWriter pw = response.getWriter();
        pw.println("id_prescricao,id_paciente,medicamento,dosagem_mg,peso_paciente,alergias,alerta_seguranca,motivo_alerta");

        for (Prescricao p : lista) {
            pw.println(
                p.getIdPrescricao()    + "," +
                p.getIdPaciente()      + "," +
                p.getMedicamento()     + "," +
                p.getDosagem_mg()      + "," +
                p.getPeso_paciente()   + "," +
                p.getAlergias()        + "," +
                p.isAlerta_seguranca() + "," +
                p.getMotivo_alerta()
            );
        }
    }
}