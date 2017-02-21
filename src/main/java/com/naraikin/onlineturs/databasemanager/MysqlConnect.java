package com.naraikin.onlineturs.databasemanager;

import java.sql.*;
import java.util.Properties;

/**
 * Created by dmitrii on 19.02.17.
 */
public final class MysqlConnect {
    public Connection conn;
    private Statement statement;
    private static MysqlConnect db;
    private MysqlConnect() {
        String url= "jdbc:mysql://localhost:3306/";
        String dbName = "mydb_tours";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "mysql2017";
        Properties p=new Properties();
        p.put("user",userName);
        p.put("password",password);
        p.put("characterEncoding","UTF-8");
        try {
            Class.forName(driver).newInstance();
            this.conn = (Connection) DriverManager.getConnection(url+dbName, p);
        }
        catch (Exception sqle) {
            sqle.printStackTrace();
        }
    }
    /**
     *
     * @return MysqlConnect Database connection object
     */
    public static synchronized Connection getDbCon() {
        if ( db == null ) {
            db = new MysqlConnect();
        }
        return db.conn;

    }
    /**
     *
     * @param query String The query to be executed
     * @return a ResultSet object containing the results or null if not available
     * @throws SQLException
     */
    public ResultSet query(String query) throws SQLException{
        statement = db.conn.createStatement();
        ResultSet res = statement.executeQuery(query);
        return res;
    }
    /**
     * @desc Method to insert data to a table
     * @param insertQuery String The Insert query
     * @return boolean
     * @throws SQLException
     */
    public int insert(String insertQuery) throws SQLException {
        statement = db.conn.createStatement();
        int result = statement.executeUpdate(insertQuery);
        return result;

    }

}