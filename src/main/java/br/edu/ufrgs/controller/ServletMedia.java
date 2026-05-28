package br.edu.ufrgs.controller;

import br.edu.ufrgs.model.Paciente; // Importação do Model
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/processa")
public class ServletMedia extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            String cpf = request.getParameter("cpf");
            double pesoKG = Double.parseDouble(request.getParameter("pesoKG"));

            // Uso da classe Model
            Paciente paciente = new Paciente(cpf, pesoKG);
            //String mensagem = paciente.getMensagemFinal();

            //request.setAttribute("resultado", mensagem);
            
        } //catch (NumberFormatException e) {
          //  request.setAttribute("resultado", "Erro: Informe uma nota válida.");
       // }

       // request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}