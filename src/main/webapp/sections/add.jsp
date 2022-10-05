

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" src="/css/main.css" type="text/css">
        <jsp:include page="../layout/head.jsp"></jsp:include>
    </head>
    <body style="background-image: url('../media/fondo_pagina_web.jpg')">
        <jsp:include page="../layout/header.jsp"></jsp:include>

        <div class="container">
            <h2 class="text-center mt-4">Nuevo Alumno</h2>
            <form method="post" action="Alumnos_war/alumno/alta">
                 <jsp:include page="layout/formulario.jsp">
                     <jsp:param name="tituloBotonPrincipal" value="Guardar"/>
                 </jsp:include>
            </form>
            <hr>
            <jsp:include page="layout/listado.jsp">
                <jsp:param name="titulo" value="Listado de Alumnos"/>
            </jsp:include>
        </div>

        <jsp:include page="../layout/footer.jsp"></jsp:include>
    </body>
</html>
