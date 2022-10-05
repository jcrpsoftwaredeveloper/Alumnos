package com.example.alumnos.model.entity;

public class Alumno {

    private Integer id;
    private String nombre;
    private String apellido1;
    private String getApellido2;
    private int num_matricula;
    private Grupo grupo_id;

    public Alumno() {
    }

    public Alumno(Integer id, String nombre, String apellido1, String getApellido2, int num_matricula, Grupo grupo_id) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.getApellido2 = getApellido2;
        this.num_matricula = num_matricula;
        this.grupo_id = grupo_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return getApellido2;
    }

    public void setApellido2(String getApellido2) {
        this.getApellido2 = getApellido2;
    }

    public int getNum_matricula() {
        return num_matricula;
    }

    public void setNum_matricula(int num_matricula) {
        this.num_matricula = num_matricula;
    }

    public Grupo getGrupo_id() {
        return grupo_id;
    }

    public void setGrupo_id(Grupo grupo_id) {
        this.grupo_id = grupo_id;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido1='" + apellido1 + '\'' +
                ", getApellido2='" + getApellido2 + '\'' +
                ", num_matricula=" + num_matricula +
                ", grupo_id=" + grupo_id +
                '}';
    }
}
