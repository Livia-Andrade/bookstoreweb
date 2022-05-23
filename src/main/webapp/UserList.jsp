<%-- 
    Document   : UserList
    Created on : 17 de mai de 2022, 23:50:05
    Author     : Amethyst
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
        <title>Aplicação BookStoreWeb Users</title>
        <jsp:include page="assets/cabecalho.jsp"/>
    </head>
    <body>

        <br>
        <div class = "container mt-3">
            <table class = "table table-striped">

                <caption><h2>List of Users</h2></caption>
                <tr>
                    <th>ID</th>
                    <th>Email</th>
                    <th>FullName</th>
                    <th>Password</th>
                    <th>Ações</th>
                </tr>

                <c:forEach var="us" items="${listaUsers}">
                    <tr>
                        <td><c:out value="${us.id}" /></td>
                        <td><c:out value="${us.email}" /></td>
                        <td><c:out value="${us.fullname}" /></td>
                        <td><c:out value="${us.password}" /></td>

                        <td>
                            <a href="<%=request.getContextPath()%>/edit?id=<c:out value='${user.id}'/>">
                                Edit
                            </a>&nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="<%=request.getContextPath()%>/delete?id=<c:out value='${user.id}'/>">
                                Delete
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <jsp:include page="contents/rodape.jsp"/>

        <script>
            function avisoDeletar() {
            var delete = window.confirm('Deletar o usuário?');
            if (delete) {
            window.alert('Usuário deletado com sucesso!');
            } else {
            window.alert('Ops... Você deletou o registro. Registre o usuário novamente!');
            }
            }
        </script>
    </body>
</html>

