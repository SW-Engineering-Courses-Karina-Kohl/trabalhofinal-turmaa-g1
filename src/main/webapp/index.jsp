<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
    <h2>Validador de Prescrição</h2>
    <form action="processa" method="post">
        Nome: <input type="text" name="nome" required><br><br>
        Peso (kg): <input type="number" step="0.1" name="peso" min="0" required><br><br>
        Medicamento: <input type="text" name="medicamento" required><br><br>
        Dosagem (mg): <input type="text" name="dosagem" required><br><br>
        <button type="submit">Verificar Situação</button>
    </form>

    <%-- Exibe o resultado se ele existir no request --%>
    <% if (request.getAttribute("resultado") != null) { %>
        <hr>
        <h3>Resultado:</h3>
        <p><strong><%= request.getAttribute("resultado") %></strong></p>
    <% } %>
</body>
</html>