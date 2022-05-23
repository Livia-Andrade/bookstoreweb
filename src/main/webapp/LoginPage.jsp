<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : loginPage
    Created on : 17 de mai de 2022, 23:50:21
    Author     : Amethyst
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Bookstore Login</title>
        <link rel="icon" type="image/x-icon" href="img/icone/iconcadastro.png">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <style>
            
            body{
                background-color: yellow
            }
            
        </style>
    </head>
    <body>

        
        <header align="center">
            <h1> Admin Login </h1>
        </header>

         <div class="container-fluid" align="center">


                <!--Campo Email-->
                <div class="form-outline mb-4">

                    <input class="form-control" name="email" placeholder="Digite seu email" size="30" />
                    <br>
                    <label class="form-label" for="email">Email</label>

                </div>

                <!--Campo Senha-->
                <div class="form-outline mb-4">

                    <input class="form-control" type="password" name="password" placeholder="Digite sua senha" size="30" />
                    <br>
                    <label class="form-label" for="password">Password</label>

                </div>

                <!--
                Esse atributo MESSAGE será utilizado como retorno de
               mensagem ao usuário caso
                login inválido.
                -->
                ${message}<br><br>
                <!--Bottom-->
                <button class="btn btn-primary btn-lg" type="submit"
                        style="padding-left: 2.5rem; padding-right: 2.5rem;">
                    Login
                </button>

                <!--Registro-->
                <p class="small fw-bold mt-2 pt-1 mb-0">Não tem uma conta? <a href="/bookstoreweb/bsuser/new"class="link-danger">Registro</a></p>
            </form>
        </div>
    </div>


</form>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</body>
</html>
