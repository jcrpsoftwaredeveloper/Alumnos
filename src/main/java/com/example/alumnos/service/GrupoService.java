package com.example.alumnos.service;

import com.example.alumnos.model.entity.Grupo;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

public class GrupoService {

    private static GrupoService grupoService;

    private GrupoService() {
    }

    public static GrupoService getInstance(){
        if (grupoService == null){
            grupoService = new GrupoService();
        }
        return grupoService;
    }

    public Grupo inicializaArticulo(){
        Grupo grupo = new Grupo();
        grupo.setCod_grupo(0);
        grupo.setDescripcion("");
        return grupo;
    }

    public Grupo requestToClass(HttpServletRequest request){
        Integer id = null;
        if (request.getParameter("id") != null){
            try{
                id = Integer.parseInt(request.getParameter("id"));
            }catch (NumberFormatException ex){

            }
        }

        String descripcion = request.getParameter("descripcion") != null ? request.getParameter("descripcion").trim() : "";

        int cod_grupo = 0;
        if (request.getParameter("cod_grupo") != null){
            try{
                cod_grupo = Integer.parseInt(request.getParameter("cod_grupo"));
            }catch (NumberFormatException ex){
                cod_grupo = 0;
            }
        }

        Grupo grupo = new Grupo(id, cod_grupo, descripcion);
        return grupo;
    }

    public Map errors(HttpServletRequest request){
        Map errors = new HashMap();
        if (request.getParameter("cod_grupo") == null || request.getParameter("cod_grupo").trim().isEmpty()){
            errors.put("errorRef", "Debe indicar la referencia");
        }
        if (request.getParameter("descripcion") == null || request.getParameter("descripcion").trim().isEmpty()){
            errors.put("errorRef", "Debe indicar la referencia");
        }

        return errors;
    }
}
