<!DOCTYPE html>
<html>
<head>
    <title>Editar Livro</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <h1>Editar Livro</h1>
    <form action="LivroServlet?action=update" method="post">
        <input type="hidden" name="isbn" value="${livro.isbn}">
        <label for="titulo">Título:</label>
        <input type="text" id="titulo" name="titulo" value="${livro.titulo}" required maxlength="50"><br>

        <label for="categoria">Categoria:</label>
        <input type="text" id="categoria" name="categoria" value="${livro.categoria}"><br>

        <label for="quantidade">Quantidade:</label>
        <input type="number" id="quantidade" name="quantidade" value="${livro.quantidade}" required><br>

        <input type="submit" value="Atualizar">
    </form>
    <a href="home.jsp">Voltar à Página Principal</a>
</body>
</html>
