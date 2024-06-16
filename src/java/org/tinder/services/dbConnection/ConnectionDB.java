/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.tinder.services.dbConnection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.sql.SQLException;

/**
 *
 * @author emilio
 */
public class ConnectionDB {
      private Connection conn;

      private Properties properties = new Properties();
      
       public ConnectionDB() { 
//        try (InputStream input = new FileInputStream("server.properties")) {
//            properties.load(input);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
try (InputStream input = getClass().getClassLoader().getResourceAsStream("server.properties")) {
    if (input != null) {
        properties.load(input);
    } else {
        System.err.println("No se pudo encontrar el archivo server.properties");
    }
} catch (IOException ex) {
    ex.printStackTrace();
}

            }
       
       
    public Connection open() throws SQLException {
        String conexion = properties.getProperty("conexion");
        String dbUrl, dbUser, dbPassword, dbParametros, driverClass;

        if ("MYSQL".equalsIgnoreCase(conexion)) {
            dbUrl = properties.getProperty("db.mysql.url");
            dbUser = properties.getProperty("db.mysql.user");
            dbPassword = properties.getProperty("db.mysql.password");
            dbParametros = properties.getProperty("db.mysql.parametros");
             driverClass = "com.mysql.cj.jdbc.Driver";
        } else if ("MARIADB".equalsIgnoreCase(conexion)) {
            dbUrl = properties.getProperty("db.mariadb.url");
            dbUser = properties.getProperty("db.mariadb.user");
            dbPassword = properties.getProperty("db.mariadb.password");
            dbParametros = properties.getProperty("db.mariadb.parametros");
             driverClass = "org.mariadb.jdbc.Driver";
        } else {
            throw new IllegalArgumentException("Unsupported database type: " + conexion);
        }

        // parametros = properties.getProperty("parametros", "");

          try {
                Class.forName(driverClass);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException("Driver class not found: " + driverClass, e);
            }
        
        
        conn = DriverManager.getConnection(dbUrl + dbParametros, dbUser, dbPassword);
        return conn;
    }
       
//    public Connection open() {
//     
//        try {
//            Class.forName("org.mariadb.jdbc.Driver");
//            conn = DriverManager.getConnection(url + parametros, user, password);
//            return conn;
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }

    public void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
    
}
