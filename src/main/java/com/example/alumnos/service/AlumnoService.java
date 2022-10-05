package com.example.alumnos.service;

import com.example.alumnos.model.dao.AlumnoDao;
import com.example.alumnos.model.dao.GrupoDao;
import com.example.alumnos.model.entity.Alumno;
import com.example.alumnos.model.entity.Grupo;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

public class AlumnoService {

    private static AlumnoService alumnoService;

    private AlumnoService() {
    }

    public static AlumnoService getInstance(){
        if (alumnoService == null){
            alumnoService = new AlumnoService();
        }
        return alumnoService;
    }

    public Alumno inicializaAlumno(){
        Alumno alumno = new Alumno();
        alumno.setNombre("");;
        alumno.setApellido1("");
        alumno.setApellido2("");
        alumno.setNum_matricula(0);
        alumno.setGrupo_id(new Grupo());
        return alumno;
    }

    public Alumno requestToClass(HttpServletRequest request){
        Integer id = null;
        if (request.getParameter("id") != null){
            try{
                id = Integer.parseInt(request.getParameter("id"));
            }catch (NumberFormatException ex){

            }
        }

        String nombre = request.getParameter("nombre") != null ? request.getParameter("nombre").trim() : "";
        String apellido1 = request.getParameter("apellido1") != null ? request.getParameter("apellido1").trim() : "";
        String apellido2 = request.getParameter("apellido2") != null ? request.getParameter("apellido2").trim() : "";

        int num_matricula = 0;
        if (request.getParameter("num_matricula") != null){
            try{
                num_matricula = Integer.parseInt(request.getParameter("num_matricula"));
            }catch (NumberFormatException ex){
                num_matricula = 0;
            }
        }

        Integer grupoId = null;
        if (request.getParameter("grupo_id") != null){
            try{
                grupoId= Integer.parseInt(request.getParameter("grupo_id"));
            }catch (NumberFormatException ex){

            }
        }

        Grupo grupo = new Grupo();
        grupo.setId(grupoId);

        Alumno alumno = new Alumno(id, nombre, apellido1, apellido2, num_matricula, grupo);
        return alumno;
    }

    public Map errors(HttpServletRequest request){
        Map errors = new HashMap();
        if (request.getParameter("nombre") == null || request.getParameter("nombre").trim().isEmpty()){
            errors.put("errorNombre", "Debe indicar la referencia");
        }
        if (request.getParameter("apellido1") == null || request.getParameter("apellido1").trim().isEmpty()){
            errors.put("errorApellido1", "Debe indicar la referencia");
        }
        if (request.getParameter("apellido2") == null || request.getParameter("apellido2").trim().isEmpty()){
            errors.put("errorApellido2", "Debe indicar la referencia");
        }
        if (request.getParameter("num_matricula") == null || request.getParameter("num_matricula").trim().isEmpty()){
            errors.put("errorMatricula", "Debe indicar la referencia");
        }
        if (request.getParameter("grupo_id") == null || request.getParameter("grupo_id").trim().isEmpty()){
            errors.put("errorGrupo", "Debe indicar la referencia");
        }
        return errors;
    }
}
