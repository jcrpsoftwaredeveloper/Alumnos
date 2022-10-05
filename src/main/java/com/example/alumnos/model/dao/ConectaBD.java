package com.example.alumnos.model.dao;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

// http://castelarlamp.duckdns.org:8090/phpmyqdmin
// jdbc:mysql://castelarlamp.duckdns.org:33069/daw2_bdprof?serverTimezone=Europe/Madrid
public class ConectaBD {
    //final static String DB_URL = "iescastelar.duckdns.org";
    final static String DB_URL = "localhost";
    //final static int DB_PORT = 33062;
    final static int DB_PORT = 3306;
    final static String DB_NAME = "alumno";
    final static String DB_USER = "root";
    final static String DB_PASSWORD = "";
    private static ConectaBD conectaBD;
    private static DataSource ds;

    // https://mvnrepository.com/artifact/commons-dbcp/commons-dbcp
    private static DataSource estableceConexionPool() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        basicDataSource.setUsername(DB_USER);
        basicDataSource.setPassword(DB_PASSWORD);
        String urlAcceso = "jdbc:mysql://" + DB_URL + ":" + DB_PORT + "/" + DB_NAME;
        basicDataSource.setUrl(urlAcceso);
        basicDataSource.setMaxActive(50);
        basicDataSource.setMaxIdle(5);
        basicDataSource.setDefaultAutoCommit(false);
        basicDataSource.setInitialSize(10);
        basicDataSource.setValidationQuery("select 1");
        return basicDataSource;
    }

    public static Connection getConnection() throws SQLException {
        if (ds == null)
            ds = estableceConexionPool();
        return ds.getConnection();
    }
}
