/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.tinder.services.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author jaime
 */
public class ConnectionMysql {
        Connection conn;
    
    public Connection open(){
        String user = "root";
        String password = "admin123";
        String url = "jdbc:mysql://127.0.0.1:3306/tinder_app";
        String params = "?useSSL=false&useUnicode=true&characterEncoding=utf-8&allowPublicKeyRetrieval=true";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url+params,user,password);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    public void close(){
        if(conn != null){
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
}
