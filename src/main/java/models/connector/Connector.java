package models.connector;


import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by dmitrii on 23.02.17.
 */
public class Connector {
    static private Logger logger = Logger.getLogger(Connector.class);
    private static Connector datasource;
    public static BasicDataSource ds;


    private Connector() {
        ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUsername("root");
        ds.setPassword("mysql2017");
        ds.setUrl("jdbc:mysql://localhost/mydb_tours?useUnicode=yes&characterEncoding=UTF-8");

        // the settings below are optional -- dbcp can work with defaults
        ds.setMinIdle(5);
        ds.setMaxIdle(20);
        ds.setMaxOpenPreparedStatements(180);

    }
    public static Connector getInstance() {
        if (datasource == null) {
                datasource = new Connector();
            return datasource;
        } else {
            return datasource;
        }
    }

    public static Connection getDbCon() {
        if (datasource == null)
            logger.trace("Новое подключение");
            datasource = new Connector();

        try {
            logger.trace("Возращаем подлючение");
            logger.trace(ds.getNumActive());
            return ds.getConnection();
        } catch (SQLException e) {
            logger.error(e);
        }
        return null;
    }

}
