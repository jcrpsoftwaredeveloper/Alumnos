package com.example.alumnos.model.dao.impl;

import com.example.alumnos.model.dao.AlumnoDao;
import com.example.alumnos.model.dao.ConectaBD;
import com.example.alumnos.model.dao.GrupoDao;
import com.example.alumnos.model.entity.Alumno;
import com.example.alumnos.model.entity.Grupo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GrupoDaoImpl implements GrupoDao {

    private Connection connection;
    private boolean error;

    public GrupoDaoImpl() {
        super();
        error = false;
        try {
            connection = ConectaBD.getConnection();
        } catch (SQLException e) {
            error = true;
            System.out.println("Se ha producido un error al acceder a la BD.");
        }
    }

    @Override
    public Integer add(Grupo entity) {
        Integer id = null;
        error = false;

        try {
            id = _add(entity);
            connection.commit();
        } catch (SQLException ex) {
            error = true;
            try {
                connection.rollback();
            } catch (SQLException ex1) {
            }
        }
        return id;
    }

    @Override
    public boolean add(List<Grupo> list) {
        error = false;
        try {
            for (Grupo entity : list) {
                _add(entity);
            }
            connection.commit();
        } catch (SQLException ex) {
            error = true;
            try {
                connection.rollback();
            } catch (SQLException ex1) {
            }
        }
        return !error;
    }

    @Override
    public boolean update(Grupo entity) {
        error = false;
        String sql = "UPDATE grupo SET "
                + "cod_grupo=?, descripcion=? "
                + "WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, entity.getCod_grupo());
            ps.setString(2, entity.getDescripcion());
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            error = true;
            try {
                connection.rollback();
            } catch (SQLException ex1) {
            }
        }
        return !error;
    }

    @Override
    public boolean delete(int id) {
        error = false;
        String sql = "DELETE FROM grupo "
                + "WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            error = true;
            try {
                connection.rollback();
            } catch (SQLException ex1) {
            }
        }
        return !error;
    }

    @Override
    public boolean deleteAll() {
        error = false;
        try {
            String sql = "DELETE FROM grupo";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            error = true;
        }
        return !error;
    }

    @Override
    public Grupo get(int id) {
        error = false;
        Grupo entity = null;
        String sql = "SELECT "
                + "id, cod_grupo, descripcion "
                + "FROM grupo WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                entity = new Grupo();
                entity.setId(rs.getInt("id"));
                entity.setCod_grupo(rs.getInt("cod_grupo"));
                entity.setDescripcion(rs.getString("descripcion"));
            }
            rs.close();
        } catch (Exception ex) {
            error = true;
        }
        return entity;
    }

    @Override
    public List<Grupo> listAll() {
        error = false;
        Grupo entity = null;
        List<Grupo> list = new ArrayList<>();
        String sql = "SELECT "
                + "id, cod_grupo, descripcion "
                + "FROM grupo";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                entity = new Grupo();
                entity.setId(rs.getInt("id"));
                entity.setCod_grupo(rs.getInt("cod_grupo"));
                entity.setDescripcion(rs.getString("descripcion"));
                list.add(entity);
            }
            rs.close();
        } catch (SQLException ex) {
            error = true;
            list = null;
        }
        return list;
    }

    @Override
    public boolean isError() {
        return error;
    }

    /*--------------------*/
    /* Métodos auxiliares */
    /*--------------------*/

    private Integer _add(Grupo entity) throws SQLException {
        Integer id;
        String sql = "INSERT INTO grupo "
                + "(cod_grupo, descripcion) VALUES "
                + "(?,?)";
        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, entity.getCod_grupo());
        ps.setString(2, entity.getDescripcion());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            id = rs.getInt(1);
        } else {
            id = -1; // Si se ha añadido pero no se ha devuelto un id
        }

        return id;
    }

    @Override
    public List<Grupo> listAllFillGrupo() {
        return null;
    }

    /*-------------------------*/
    /* Métodos complementarios */
    /*-------------------------*/

}