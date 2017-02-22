package com.naraikin.onlineturs.tables;

import com.naraikin.onlineturs.databasemanager.MysqlConnect;
import com.naraikin.onlineturs.models.Client;
import com.naraikin.onlineturs.parser.Parser;
import com.naraikin.onlineturs.parser.impl.JaxbParser;
import com.naraikin.onlineturs.threads.Counter;
import com.naraikin.onlineturs.wraps.WrapClients;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.sql.*;


/**
 * Created by dmitrii on 19.02.17.
 */

public class TableClient extends ParentDAO implements DAOI{

    public static final Logger logger = Logger.getLogger(TableClient.class);
    static {
        DOMConfigurator.configure("src/main/resources/log4j.xml");
    }

    private WrapClients wrapClients = new WrapClients();
    private File file = new File("clients.xml");
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
            logger.error(e.getMessage());
        }
    }

    public static Client selectID(int id) {
        String sqlReq = "SELECT * FROM client WHERE idclient="+id;
        Client client = new Client();
        try {
            Statement statement =
                    statement = MysqlConnect.getDbCon().createStatement();
            ResultSet rs = statement.executeQuery(sqlReq);

            while (rs.next()){
                client.setIdclient(rs.getInt("idclient"));
                client.setLastName(rs.getString("lastName"));
                client.setFirstName(rs.getString("firstName"));
                client.setPhone(rs.getString("phone"));
                client.setBirthDate(rs.getDate("birthDate"));
                client.setDoc(rs.getString("doc"));
                client.setAddress(rs.getString("address"));
                client.setGender(rs.getString("gender"));
            }
            return client;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return client;
    }

    @Override
    public void insertAllRowDB(Counter counter) {

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

                    logger.trace("Добавление Client " + client.getFirstName());
                    prepStat.executeUpdate();
                    counter.append(client);
                logger.trace("Выход из добавление " + client.getFirstName());

            } catch (SQLException e) {
                logger.error(e.getMessage());
            }


        }

    }

    public void saveXML() {
        try {
            this.parser.saveObject(file, wrapClients);
        } catch (JAXBException e) {
            logger.error(e.getMessage());
        }
    }

    public void parseXML() {
        try {
            wrapClients = (WrapClients) parser.getObject(file, WrapClients.class);
        } catch (JAXBException e) {
            logger.error(e.getMessage());
        }
    }


}
