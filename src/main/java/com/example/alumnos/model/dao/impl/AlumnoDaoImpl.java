package com.example.alumnos.model.dao.impl;

import com.example.alumnos.model.dao.AlumnoDao;
import com.example.alumnos.model.dao.ConectaBD;
import com.example.alumnos.model.dao.GrupoDao;
import com.example.alumnos.model.entity.Alumno;
import com.example.alumnos.model.entity.Grupo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDaoImpl implements AlumnoDao {

    private Connection connection;
    private boolean error;

    public AlumnoDaoImpl() {
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
    public Integer add(Alumno entity) {
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
    public boolean add(List<Alumno> list) {
        error = false;
        try {
            for (Alumno entity : list) {
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
    public boolean update(Alumno entity) {
        error = false;
        String sql = "UPDATE alumno SET "
                + "nombre=?, apellido1=?, apellido2=?, num_matricula=?, "
                + "grupo_id=?"
                + "WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, entity.getNombre());
            ps.setString(2, entity.getApellido1());
            ps.setString(3, entity.getApellido2());
            ps.setInt(4, entity.getNum_matricula());
            ps.setInt(5, entity.getGrupo_id()!=null?entity.getGrupo_id().getId():null);
            ps.setInt(6, entity.getId());
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
        String sql = "DELETE FROM alumno "
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
            String sql = "DELETE FROM alumno";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            error = true;
        }
        return !error;
    }

    @Override
    public Alumno get(int id) {
        error = false;
        Alumno entity = null;
        String sql = "SELECT "
                + "id, nombre, apellido1, apellido2, num_matricula, "
                + "grupo_id "
                + "FROM alumno WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                entity = new Alumno();
                entity.setId(rs.getInt("id"));
                entity.setNombre(rs.getString("nombre"));
                entity.setApellido1(rs.getString("apellido1"));
                entity.setApellido2(rs.getString("apellido2"));
                entity.setNum_matricula(rs.getInt("num_matricula"));
                Grupo grupo = new Grupo();
                grupo.setId(rs.getInt("grupo_id"));
                entity.setGrupo_id(grupo);
            }
            rs.close();
        } catch (Exception ex) {
            error = true;
        }
        return entity;
    }

    @Override
    public List<Alumno> listAll() {
        error = false;
        Alumno entity = null;
        List<Alumno> list = new ArrayList<>();
        String sql = "SELECT "
                + "id, nombre, apellido1, apellido2, num_matricula, grupo_id "
                + "FROM alumno";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                entity = new Alumno();
                entity.setId(rs.getInt("id"));
                entity.setNombre(rs.getString("nombre"));
                entity.setApellido1(rs.getString("apellido1"));
                entity.setApellido2(rs.getString("apellido2"));
                entity.setNum_matricula(rs.getInt("num_matricula"));
                Grupo grupo = new Grupo();
                grupo.setId(rs.getInt("grupo_id"));
                entity.setGrupo_id(grupo);
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

    private Integer _add(Alumno entity) throws SQLException {
        Integer id;
        String sql = "INSERT INTO alumno "
                + "(nombre, apellido1, apellido2, num_matricula, grupo_id) VALUES "
                + "(?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, entity.getNombre());
        ps.setString(2, entity.getApellido1());
        ps.setString(3, entity.getApellido2());
        ps.setInt(4, entity.getNum_matricula());
        ps.setInt(5, entity.getGrupo_id()!=null?entity.getGrupo_id().getId():null);
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            id = rs.getInt(1);
        } else {
            id = -1; // Si se ha añadido pero no se ha devuelto un id
        }

        return id;
    }

    /*-------------------------*/
    /* Métodos complementarios */
    /*-------------------------*/
    @Override
    public List<Alumno> listAllFillGrupo() {
        GrupoDao grupoDao = new GrupoDaoImpl();
        List<Alumno> list = listAll();
        list.forEach(e->e.setGrupo_id(grupoDao.get(e.getGrupo_id().getId())));
        return list;
    }

    @Override
    public List<Alumno> listAllFillAlumno() {
        return null;
    }
}
