<%-- 
    Document   : BookForm
    Created on : 17 de mai de 2022, 23:49:08
    Author     : Amethyst
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="contents/headerTags.jsp"/>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">
        <title>Aplicação Books Store</title>
    </head>
    <body>
        <jsp:include page="contents/cabecalho.jsp"/>



        <div align="left">
            <c:if test="${book != null}">
                <form action="update" method="post">
                </c:if>
                <c:if test="${book == null}">
                    <form action="insert" method="post">
                    </c:if>
                    <div class = "container mt-3">
                        <table class = "table table-striped">
                            <caption>
                                <h2 style = text-decoration: none;>
                                    <c:if test="${book != null}">
                                        Editar Livro
                                    </c:if>
                                    <c:if test="${book == null}">
                                        Adicionar novo Livro
                                    </c:if>
                                </h2>
                            </caption>
                            <c:if test="${book != null}">
                                <input type="hidden" name="formId" value="<c:out value='${book.id}' />" />
                            </c:if>
                            <tr>
                                <th>Titulo: </th>
                                <td>
                                    <input type="text" name="formTitulo" size="45" value="<c:out value='${book.titulo}' />"/>
                                </td>
                            </tr>
                            <tr>
                                <th>Autor: </th>
                                <td>
                                    <input type="text" name="formAutor" size="45" value="<c:out value='${book.autor}' />"/>
                                </td>
                            </tr>
                            <tr>
                                <th>Preco: </th>
                                <td>
                                    <input type="text" name="formPreco" size="5" value="<c:out value='${book.preco}' />"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" align="center">
                                    <input type="submit" value="Enviar" />
                                </td>
                            </tr>
                        </table>
                    </div>
                </form>
        </div>

        <jsp:include page="contents/rodape.jsp"/> 
    </body>
</html>