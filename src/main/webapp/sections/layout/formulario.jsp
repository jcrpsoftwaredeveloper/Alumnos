<%@ page import="com.example.alumnos.model.entity.Alumno" %>
<%@ page import="com.example.alumnos.model.entity.Grupo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>


<%
    Alumno alumno = (Alumno) request.getAttribute("alumno");
    List<Grupo> grupos = (List<Grupo>) request.getAttribute("grupos");
    Map errorsItems = (Map) request.getAttribute("errorsItems");
    Boolean readonly = (Boolean) request.getAttribute("readonly");
    Boolean showSubmit = (Boolean) request.getAttribute("showSubmit");
%>

<input name="id" type="hidden" value="<%=alumno.getId()%>">

<div class="card bg-dark text-white row col-12 col-md-8 offset-md-2 mt-4">
    <div class="card-body">
        <div class="form-group row mt-2">
            <label for="nombre" class="col-12 col-md-3 col-form-label">
                Nombre <span class="text-danger text-sm-left">*</span>
            </label>
            <div class="col-12 col-md-9">
                <input id="nombre" name="nombre" type="text" value="<%=alumno.getNombre()%>" class="form-control">
                <small class="form-text text-danger">
                    <%=errorsItems != null && errorsItems.get("errorNombre") != null ? errorsItems.get("errorNombre") : ""%>
                </small>
            </div>
        </div>

        <div class="form-group row mt-2">
            <label for="apellido1" class="col-12 col-md-3 col-form-label">
                Primer Apellido <span class="text-danger text-sm-left">*</span>
            </label>
            <div class="col-12 col-md-9">
                <input id="apellido1" name="apellido1" type="text" value="<%=alumno.getApellido1()%>" class="form-control">
                <small class="form-text text-danger">
                    <%=errorsItems != null && errorsItems.get("errorApellido1") != null ? errorsItems.get("errorApellido1") : ""%>
                </small>
            </div>
        </div>

        <div class="form-group row mt-2">
            <label for="apellido2" class="col-12 col-md-3 col-form-label">
                Segundo Apellido <span class="text-danger text-sm-left">*</span>
            </label>
            <div class="col-12 col-md-9">
                <input id="apellido2" name="apellido2" type="text" value="<%=alumno.getApellido2()%>" class="form-control">
                <small class="form-text text-danger">
                    <%=errorsItems != null && errorsItems.get("errorApellido2") != null ? errorsItems.get("errorApellido2") : ""%>
                </small>
            </div>
        </div>

        <div class="form-group row mt-2">
            <label for="num_matricula" class="col-12 col-md-3 col-form-label">
                Nº Matrícula <span class="text-danger text-sm-left">*</span>
            </label>
            <div class="col-12 col-md-9">
                <input id="num_matricula" name="num_matricula" type="number" value="<%=alumno.getNum_matricula()%>" class="form-control">
                <small class="form-text text-danger">
                    <%=errorsItems != null && errorsItems.get("errorMatricula") != null ? errorsItems.get("errorMatricula") : ""%>
                </small>
            </div>
        </div>

        <div class="form-group row mt-2">
            <label for="grupoId" class="col-12 col-md-3 col-form-label">
                Grupo <span class="text-danger text-sm-left">*</span>
            </label>
            <div class="col-12 col-md-9">
                <select id="grupoId" name="grupoId" class="form-select">
                    <option selected <%=true?"disabled":""%>>Seleccione un Grupo</option>
                    <%for (Grupo grupo: grupos) {%>
                    <option
                            <%=alumno.getGrupo_id()!=null &&
                                   alumno.getGrupo_id().getId() != null
                                    && grupo.getId().intValue() == alumno.getGrupo_id().getId().intValue()
                                    ?"selected":""
                            %>
                            value="<%=grupo.getId()%>"
                            <%=true?"disabled":""%>
                    ><%=grupo.getCod_grupo()%></option>
                    <%}%>
                </select>
                <small class="form-text text-danger">
                    <%=errorsItems != null && errorsItems.get("errorGrupo") != null ? errorsItems.get("errorGrupo") : ""%>
                </small>
            </div>
        </div>

        <div class="row justify-content-end mt-3">
            <% if(true){%>
            <button type="submit" class="btn btn-primary col-12 col-md-4">
                <i class="fa-solid fa-floppy-disk"></i> <%=request.getParameter("tituloBotonPrincipal")%>
            </button>
            <%}%>
        </div>

    </div>
</div>