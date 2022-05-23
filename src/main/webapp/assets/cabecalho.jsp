<%-- 
    Document   : cabecalho
    Created on : 18 de mai de 2022, 01:27:10
    Author     : Amethyst
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Inicio cabecalho-->

<div class=" p-4 bg-primary text-white text-center"><h1>Aplicação BookStore</h1>

    <div class = "col-6 mx-auto">

        <a href="<%=request.getContextPath()%>/bstore/new" class="btn btn-outline-light">
            <span class="glyphicon glyphicon-plus"></span>Adicionar novo Livro</a>

        <a href="<%=request.getContextPath()%>/bstore/list" class="btn btn-outline-light ">
            <span class="glyphicon glyphicon-th-list"></span>
            Lista todos Livros</a>

        <a href="<%=request.getContextPath()%>/bsuser/list" class="btn btn-outline-light ">
            <span class="glyphicon glyphicon-th-list"></span>
            Listar usuarios</a>

    </div>
</div>

<!-- Fim cabecalho-->
</html>
