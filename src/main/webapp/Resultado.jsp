<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, br.edu.ufrgs.model.Prescricao" %>
<%
    @SuppressWarnings("unchecked")
    List<Prescricao> lista = (List<Prescricao>) request.getAttribute("prescricoes");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Resultado</title>
    <style>
        body { font-family: Arial; margin: 40px; }
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #333; padding: 8px; text-align: center; }
        th { background: #2c7be5; color: white; }
        tr.alerta td {
            color: red;
            font-weight: bold;
}
        a {
            display: inline-block; margin-top: 15px; margin-right: 10px;
            padding: 8px 15px; background: #2c7be5; color: white;
            text-decoration: none; border-radius: 4px;
        }
        h1 { color: #2c7be5; }
    </style>
</head>
<body>
    <h1>Prescricoes Auditadas</h1>
    <table>
        <tr>
            <th>id_prescricao</th>
            <th>id_paciente</th>
            <th>medicamento</th>
            <th>dosagem_mg</th>
            <th>peso_paciente</th>
            <th>alergias</th>
            <th>alerta_seguranca</th>
            <th>motivo_alerta</th>
        </tr>
<% for (Prescricao p : lista) { %>
        <tr class="<​%= p.isAlerta_seguranca() ? "alerta" : "" %>">
            <td><%= p.getIdPrescricao() %></td>
            <td><%= p.getIdPaciente() %></td>
            <td><%= p.getMedicamento() %></td>
            <td><%= p.getDosagem_mg() %></td>
            <td><%= p.getPeso_paciente() %></td>
            <td><%= p.getAlergias() %></td>
            <td><%= p.isAlerta_seguranca() %></td>
            <td><%= p.getMotivo_alerta() %></td>
        </tr>
<% } %>
    </table>
    <a href="exportar">Exportar CSV Auditado</a>
    <a href="index.jsp">Voltar</a>
</body>
</html>
