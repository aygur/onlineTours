package models.dao;

import common.ClientDAOException;
import models.connector.Connector;
import models.pojo.Client;
import models.pojo.Tour;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitrii on 23.02.17.
 */

public class ClientDAO {
    private static Logger logger = Logger.getLogger(ClientDAO.class);
    private static final String SQL_SELECT_ALL = "SELECT * FROM client";
    private static final String SQL_SET_DELETE = "UPDATE client SET " +
            " blocked = 1 " +
            " WHERE idclient = ?";
    private static final String SQL_FIND_CLIENT = "SELECT * FROM client where login=? AND password=?";
    private static final String SQL_SELECT_CLIENT_BY_ID = "SELECT * FROM client where idclient=?";
    private static final String SQL_CREATE_CLIENT_REGISTRATION =
            "INSERT INTO client (login, password, email, role) " +
                    "VALUES(?, ?, ?, ?)";
    private static final String SQL_UPDATE_CLIENT = "UPDATE client SET lastName= ?, " +
            " firstName = ?, phone = ?, doc =?, birthDate =? , address= ?, " +
            " gender = ? " +
            " WHERE idclient = ?";


    public static List<Client> getAll() throws ClientDAOException {
        List<Client> list = new ArrayList<>();
        try {
            Statement statement = Connector.getDbCon().createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);

            while (resultSet.next()){
                Client client = new Client();
                client.setIdclient(resultSet.getInt("idclient"));
                client.setLastName(resultSet.getString("lastName"));
                client.setFirstName(resultSet.getString("firstName"));
                client.setPhone(resultSet.getString("phone"));
                client.setDoc(resultSet.getString("doc"));
                client.setBirthDate(resultSet.getDate("birthDate"));
                client.setAddress(resultSet.getString("address"));
                client.setGender(resultSet.getString("gender"));
                client.setLogin(resultSet.getString("login"));
                client.setPassword(resultSet.getString("password"));
                client.setPhone(resultSet.getString("email"));
                client.setRole(resultSet.getString("role"));
                client.setBlocked(resultSet.getByte("blocked"));
                list.add(client);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new ClientDAOException();
        }
        return list;
    }

    public static boolean setClientBlocked(int id) throws ClientDAOException {
        Client client = new Client();
        Connection conn = Connector.getDbCon();
        try (PreparedStatement prepS = conn.prepareStatement(SQL_SET_DELETE)) {
            prepS.setInt(1, id);

                int count = prepS.executeUpdate();
                if(count > 0){
                    logger.debug("blocked client " + count);
                    return true;
                }else{
                    logger.debug(client.getLogin() + " not updated");
                }

        } catch (SQLException e) {
            logger.error(e);
            throw new ClientDAOException();
        }
        return true;
    }

    public static Client getClientByLoginAndPassword(String login, String password) throws ClientDAOException {
        Client client = new Client();
        logger.trace("Connection to DB");
        Connection conn = Connector.getDbCon();
        try (
             PreparedStatement prepS = conn.prepareStatement(SQL_FIND_CLIENT)) {
            prepS.setString(1, login);
            prepS.setString(2, password);
            ResultSet resultSet = prepS.executeQuery();
            while (resultSet.next()){
                client.setIdclient(resultSet.getInt("idclient"));
                client.setLastName(resultSet.getString("lastName"));
                client.setFirstName(resultSet.getString("firstName"));
                client.setPhone(resultSet.getString("phone"));
                client.setDoc(resultSet.getString("doc"));
                client.setBirthDate(resultSet.getDate("birthDate"));
                client.setAddress(resultSet.getString("address"));
                client.setGender(resultSet.getString("gender"));
                client.setLogin(resultSet.getString("login"));
                client.setPassword(resultSet.getString("password"));
                client.setEmail(resultSet.getString("email"));
                client.setRole(resultSet.getString("role"));
                client.setBlocked(resultSet.getByte("blocked"));
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new ClientDAOException();
        }
        logger.trace("Return user");
        return client;
    }

    public static boolean registrationClient(String login, String password, String email) throws ClientDAOException {
        Connection connection = Connector.getDbCon();
        try(PreparedStatement preparedStatement
                    = connection.prepareStatement(SQL_CREATE_CLIENT_REGISTRATION)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, "user");
            int count = preparedStatement.executeUpdate();
            if(count > 0){
                logger.debug("inserted " + count);
                return true;
            }else{
                logger.debug(login + " " + password + " not inserted");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new ClientDAOException();
        }
        return false;
    }

    public static boolean updateClient(Client client) throws ClientDAOException{
        try( PreparedStatement ps = Connector.getDbCon()
                .prepareStatement(SQL_UPDATE_CLIENT)) {

            ps.setString(1, client.getLastName());
            ps.setString(2, client.getFirstName());
            ps.setString(3, client.getPhone());
            ps.setString(4, client.getDoc());
            ps.setDate(5, client.getBirthDate());
            ps.setString(6, client.getAddress());
            ps.setString(7, client.getGender());
            ps.setInt(8, client.getIdclient());
            int count = ps.executeUpdate();
            if(count > 0){
                logger.debug("update client " + count);
                return true;
            }else{
                logger.debug(client.getLogin() + " not updated");
            }
        }
        catch (SQLException se)
        {
            // log the exception
            logger.error(se);
            throw new ClientDAOException();
        }
        return false;
    }

    public static Client getClientById(int id) throws ClientDAOException {
        Client client = new Client();
        logger.trace("Connection to DB");
        Connection conn = Connector.getDbCon();
        try (
                PreparedStatement prepS = conn.prepareStatement(SQL_SELECT_CLIENT_BY_ID)) {
            prepS.setInt(1, id);
            ResultSet resultSet = prepS.executeQuery();
            while (resultSet.next()){
                client.setIdclient(resultSet.getInt("idclient"));
                client.setLastName(resultSet.getString("lastName"));
                client.setFirstName(resultSet.getString("firstName"));
                client.setPhone(resultSet.getString("phone"));
                client.setDoc(resultSet.getString("doc"));
                client.setBirthDate(resultSet.getDate("birthDate"));
                client.setAddress(resultSet.getString("address"));
                client.setGender(resultSet.getString("gender"));
                client.setLogin(resultSet.getString("login"));
                client.setPassword(resultSet.getString("password"));
                client.setEmail(resultSet.getString("email"));
                client.setRole(resultSet.getString("role"));
                client.setBlocked(resultSet.getByte("blocked"));
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new ClientDAOException();
        }
        logger.trace("Return user");
        return client;
    }


}
