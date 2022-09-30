package com.example.alumnos.model.entity;

public class Grupo {

    private int id;
    private int cod_grupo;
    private String descripcion;

    public Grupo() {
    }

    public Grupo(int id, int cod_grupo, String descripcion) {
        this.id = id;
        this.cod_grupo = cod_grupo;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCod_grupo() {
        return cod_grupo;
    }

    public void setCod_grupo(int cod_grupo) {
        this.cod_grupo = cod_grupo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Grupo{" +
                "id=" + id +
                ", cod_grupo=" + cod_grupo +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
