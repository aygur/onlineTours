package com.naraikin.onlineturs.tables;

import com.naraikin.onlineturs.databasemanager.MysqlConnect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by dmitrii on 21.02.17.
 */
public class ParentDAO {

    protected String tableName;

    public void resetAUTO_INCREMENT() {
        String sqlReq = "ALTER TABLE "+ tableName +" AUTO_INCREMENT = 1";
        try {
            Statement statement =
                    statement = MysqlConnect.getDbCon().createStatement();
            Boolean rs = statement.execute(sqlReq);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTableData() {
        String sqlReq = "DELETE FROM "+ tableName;
        try {
            Statement statement =
                    statement = MysqlConnect.getDbCon().createStatement();
            Boolean rs = statement.execute(sqlReq);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
