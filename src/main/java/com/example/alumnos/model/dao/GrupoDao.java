package com.example.alumnos.model.dao;

import com.example.alumnos.model.entity.Grupo;

import java.util.List;

public interface GrupoDao extends Dao<Grupo, Integer>{

    List<Grupo> listAllFillGrupo();
}
