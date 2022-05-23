<%-- 
    Document   : BookList
    Created on : 17 de mai de 2022, 23:41:59
    Author     : Amethyst
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="assets/headerTags.jsp"/>
        <title>Aplicação BookStoreWeb</title>
         <link rel="icon" type="image/x-icon" href="../img/icone/icons8.png">
        <jsp:include page="assets/cabecalho.jsp"/>
    </head>
    <body>
        <br>
        <div class = "container mt-3">
            <table class = "table table-striped">
                <img src="img/table/books.jpg" width="450" height="450">
>                     <caption><h2>List of Books</h2></caption>
                <tr>
                    <th>ID</th>
                    <th>Titulo</th>
                    <th>Autor</th>
                    <th>Preço</th>
                    <th>Ações</th>
                </tr>

                <c:forEach var="book" items="${listaBook}">
                    <tr>
                        <td><c:out value="${book.id}" /></td>
                        <td><c:out value="${book.titulo}" /></td>
                        <td><c:out value="${book.autor}" /></td>
                        <td><c:out value="${book.preco}" /></td>
                        <td>
                            <a href="<%=request.getContextPath()%>/edit?id=<c:out value='${book.id}'/>">
                                Editar
                            </a>&nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="<%=request.getContextPath()%>/delete?id=<c:out value='${book.id}'/>">
                                Excluir
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <jsp:include page="assets/rodape.jsp"/>
        
        <script>
            function avisoDeletar() {
                var teste = window.confirm('Deletar o livro?');

                if (teste) {
                    window.alert('Livro deletado com sucesso!');
                } else {
                    window.alert('Ops... Você deletou o registro. Registre o livro novamente!');
                }
            }
        </script>
    </body>
</html>
