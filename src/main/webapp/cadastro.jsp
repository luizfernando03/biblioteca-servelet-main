<!DOCTYPE html>
<html>
<head>
    <title>Cadastro de Livro</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <h1>Cadastro de Livro</h1>
    <form action="LivroServlet?action=add" method="post">
        <label for="isbn">ISBN:</label>
        <input type="text" id="isbn" name="isbn" required><br>

        <label for="titulo">Título:</label>
        <input type="text" id="titulo" name="titulo" required maxlength="50"><br>

        <label for="categoria">Categoria:</label>
        <input type="text" id="categoria" name="categoria"><br>

        <label for="quantidade">Quantidade:</label>
        <input type="number" id="quantidade" name="quantidade" required><br>

        <input type="submit" value="Cadastrar">
    </form>
    <a href="home.jsp">Voltar à Página Principal</a>
</body>
</html>
