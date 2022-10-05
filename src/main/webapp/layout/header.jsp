<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<ul>
    <li><a href="#home">Home</a></li>
    <li><a href="Alumnos_war/alumno/alta">Nuevo Alumno</a></li>
    <li class="dropdown">
        <a href="javascript:void(0)" class="dropbtn">Dropdown</a>
        <div class="dropdown-content">
            <a href="Alumnos_war/alumno/alta">Nuevo Alumno</a>
            <a href="#">Link 2</a>
            <a href="#">Link 3</a>
        </div>
    </li>
</ul>

<%if (request.getAttribute("alertSuccess") != null) {%>
<div class="alert alert-success mensaje-temporal">
    <i class="fa-solid fa-thumbs-up fa-1x"></i>
    <%=request.getAttribute("alertSuccess")%>
</div>
<%}%>

<%if (request.getAttribute("alertDanger") != null) {%>
<div class="alert alert-danger mensaje-temporal">
    <i class="fa-solid fa-thumbs-down fa-1x"></i>
    <%=request.getAttribute("alertDanger")%>
</div>
<%}%>

<%if (request.getAttribute("alertWarning") != null) {%>
<div class="alert alert-warning mensaje-temporal">
    <i class="fa-solid fa-triangle-exclamation fa-1x"></i>
    <%=request.getAttribute("alertWarning")%>
</div>
<%}%>

<%if (request.getAttribute("alertInfo") != null) {%>
<div class="alert alert-info mensaje-temporal">
    <i class="fa-solid fa-square-info fa-1x"></i>
    <%=request.getAttribute("alertInfo")%>
</div>
<%}%>
