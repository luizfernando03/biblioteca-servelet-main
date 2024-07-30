<%--
  Created by IntelliJ IDEA.
  User: Luiz
  Date: 11/07/2024
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All Users In Database</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
        }
        header {
            background-color: #4CAF50;
            color: white;
            padding: 15px 10px;
            text-align: center;
        }
        nav a {
            margin: 0 10px;
            text-decoration: none;
            color: white;
        }
        nav {
            background-color: #333;
            overflow: hidden;
            padding: 10px;
        }
        main {
            padding: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
    </style>
</head>
<body>
<%@ include file="WEB-INF/jsp/include/header.jsp" %>
<main>
    <h2>Lista de Usuários</h2>
    <c:if test="${not empty users}">
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Email</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.nome}</td>
                    <td>${user.email}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</main>
<%@ include file="WEB-INF/jsp/include/footer.jsp" %>
</body>
</html>
