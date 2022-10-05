package com.example.alumnos.model.dao;

import com.example.alumnos.model.entity.Alumno;

import java.util.List;

public interface AlumnoDao extends Dao<Alumno, Integer> {

    /*-------------------------*/
    /* Métodos complementarios */
    /*-------------------------*/
    List<Alumno> listAllFillGrupo();

    List<Alumno> listAllFillAlumno();
}
