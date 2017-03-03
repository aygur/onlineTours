package com.naraikin.onlinetours.models.connector;


import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created by dmitrii on 23.02.17.
 */
public class Connector2 {
    private static Connection conn;
    private static  String user = "root";//Логин пользователя
    private static String password = "mysql2017";//Пароль пользователя
    private static String url = "jdbc:mysql://localhost:3306/mydb_tours";//URL адрес
    private static String driver = "com.mysql.jdbc.Driver";//Имя драйвера
    private static Connector2 db;
    private static Properties p=new Properties();
    static {
        p.put("user",user);
        p.put("password",password);
        p.put("characterEncoding","UTF-8");
    }


    public static synchronized Connection getDbCon() {
        if ( db == null ) {
            db = new Connector2();
        }
        return db.conn;
    }

    private Connector2() {
        try {
            Class.forName(driver).newInstance();
            this.conn = (Connection) DriverManager.getConnection(url, p);
        }
        catch (Exception sqle) {
            sqle.printStackTrace();
        }

    }
}
