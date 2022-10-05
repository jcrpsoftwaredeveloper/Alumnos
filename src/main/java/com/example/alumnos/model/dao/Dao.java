package com.example.alumnos.model.dao;

import java.io.Serializable;
import java.util.List;

public interface Dao<T,PK extends Serializable> {
    PK add(T entity);

    boolean add(List<T> list);

    boolean update(T entity);

    boolean delete(int id);

    boolean deleteAll();

    T get(int id);

    List<T> listAll();

    boolean isError();

    default boolean exist(int id) {
        return get(id) != null;
    }

    default java.sql.Date utilDateToSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    default java.util.Date sqlDateToUtilDate(java.sql.Date date) {
        return new java.sql.Date(date.getTime());
    }
}
