package com.naraikin.onlineturs.tables;

import com.naraikin.onlineturs.databasemanager.MysqlConnect;
import com.naraikin.onlineturs.models.Client;
import com.naraikin.onlineturs.parser.Parser;
import com.naraikin.onlineturs.parser.impl.JaxbParser;
import com.naraikin.onlineturs.wraps.WrapClients;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.sql.*;


/**
 * Created by dmitrii on 19.02.17.
 */

public class TableClient extends ParentDAO implements DAOI{

    private WrapClients wrapClients = new WrapClients();
    private File file = new File("clients1.xml");
    private Parser parser = new JaxbParser();

    public TableClient(){
        tableName = "client";
    }

    public void selectAllRowDB() {
        String sqlReq = "SELECT * FROM client";
        try {
            Statement statement =
                    statement = MysqlConnect.getDbCon().createStatement();
        ResultSet rs = statement.executeQuery(sqlReq);

        while (rs.next()){
            Client client = new Client();
            client.setIdclient(rs.getInt("idclient"));
            client.setLastName(rs.getString("lastName"));
            client.setFirstName(rs.getString("firstName"));
            client.setPhone(rs.getString("phone"));
            client.setBirthDate(rs.getDate("birthDate"));
            client.setDoc(rs.getString("doc"));
            client.setAddress(rs.getString("address"));
            client.setGender(rs.getString("gender"));
            wrapClients.append(client);
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void insertAllRowDB() {

        String sqlReq = "INSERT INTO client "
                + "(idclient, lastName, firstName, phone, " +
                "birthDate, address, gender, doc) VALUES"
                + "(?,?,?,?,?,?,?,?)";
        for(Client client: wrapClients.getList()) {
            try {
            PreparedStatement prepStat =
                    MysqlConnect.getDbCon().prepareStatement(sqlReq);
            prepStat.setInt(1, client.getIdclient());
            prepStat.setString(2, client.getLastName());
            prepStat.setString(3, client.getFirstName());
            prepStat.setString(4, client.getPhone());
            prepStat.setDate(5, new Date(client.getBirthDate().getTime()));
            prepStat.setString(6, client.getAddress());
            prepStat.setString(7, client.getGender());
            prepStat.setString(8, client.getDoc());
            prepStat.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void saveXML() throws JAXBException {
        this.parser.saveObject(file, wrapClients);
    }

    public void parseXML() throws JAXBException {
        wrapClients = (WrapClients) parser.getObject(file, WrapClients.class);
    }


}
