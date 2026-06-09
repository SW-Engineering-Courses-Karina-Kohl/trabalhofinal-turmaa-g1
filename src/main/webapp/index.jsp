<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Validador de Prescrições</title>
    <style>
        body { font-family: Arial; margin: 40px; }
        form { background: #f4f4f4; padding: 20px; border-radius: 8px; width: 500px; }
        input[type=submit] {
            padding: 8px 20px; background: #2c7be5; color: white;
            border: none; cursor: pointer; border-radius: 4px;
        }
        h1 { color: #2c7be5; }
    </style>
</head>
<body>
    <h1>Validador de Prescrições Médicas</h1>
    <form action="upload" method="post" enctype="multipart/form-data">
        <p><b>Selecione o arquivo CSV de prescrições:</b></p>
        <input type="file" name="file" accept=".csv" required/>
        <br><br>
        <input type="submit" value="Carregar e Validar"/>
    </form>
</body>
</html>