package com.naraikin.onlinetours.models.dao;

import com.naraikin.onlinetours.common.exception.ClientDAOException;
import com.naraikin.onlinetours.models.connector.Connector;
import com.naraikin.onlinetours.models.dao.interfaces.ClientDAO;
import com.naraikin.onlinetours.models.pojo.Client;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitrii on 23.02.17.
 */
@Repository("ClientDAOImpl")
public class ClientDAOImpl implements ClientDAO {
    private static Logger logger = Logger.getLogger(ClientDAOImpl.class);
    private static final String SQL_SELECT_ALL = "SELECT * FROM client";
    private static final String SQL_SET_DELETE = "UPDATE client SET " +
            " blocked = ? " +
            " WHERE idclient = ?";
    private static final String SQL_FIND_CLIENT = "SELECT * FROM client where login=? AND password=?";
    private static final String SQL_SELECT_CLIENT_BY_ID = "SELECT * FROM client where idclient=?";
    private static final String SQL_SELECT_CLIENT_BY_LOGIN = "SELECT * FROM client where login=?";
    private static final String SQL_CREATE_CLIENT_REGISTRATION =
            "INSERT INTO client (login, password, email, role) " +
                    "VALUES(?, ?, ?, ?)";
    private static final String SQL_CREATE_CLIENT_REGISTRATION_ALL =
            "INSERT INTO client (lastName, firstName, phone, doc, birthDate, address," +
                    " gender,login, password, email, role) " +
                    "VALUES(?, ?, ?, ?, ? , ? ,? , ? ,?,?, ? )";
    private static final String SQL_UPDATE_CLIENT = "UPDATE client SET lastName= ?, " +
            " firstName = ?, phone = ?, doc =?, birthDate =? , address= ?, " +
            " gender = ? " +
            " WHERE idclient = ?";

    public boolean registrationClient(Client client) throws ClientDAOException {
        try(PreparedStatement ps
                    = Connector.getDbCon().prepareStatement(SQL_CREATE_CLIENT_REGISTRATION_ALL)) {

            ps.setString(1, client.getLastName());
            ps.setString(2, client.getFirstName());
            ps.setString(3, client.getPhone());
            ps.setString(4, client.getDoc());
            ps.setDate(5, client.getBirthDate());
            ps.setString(6, client.getAddress());
            ps.setString(7, client.getGender());
            ps.setString(8, client.getLogin());
            ps.setString(9, client.getPassword());
            ps.setString(10, client.getEmail());
            ps.setString(11, client.getRole());
            int count = ps.executeUpdate();
            if(count > 0){
                logger.debug("update client " + count);
                return true;
            }else{
                logger.debug(client.getLogin() + " not updated");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new ClientDAOException();
        }
        return false;
    }

    public List<Client> getAll() throws ClientDAOException {
        List<Client> list = new ArrayList<>();
        try {Statement statement = Connector.getDbCon().createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            logger.trace(Connector.ds.getNumActive());
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
                client.setEmail(resultSet.getString("email"));
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

    public boolean setClientBlocked(Client client) throws ClientDAOException {
        try (PreparedStatement prepS = Connector.getDbCon().prepareStatement(SQL_SET_DELETE)) {

            prepS.setShort(1, client.getBlocked());
            prepS.setInt(2, client.getIdclient());

                int count = prepS.executeUpdate();
                if(count > 0){
                    logger.debug("block client " + count);
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

    public Client getClientByLoginAndPassword(String login, String password) throws ClientDAOException {
        Client client = new Client();
        logger.trace("Connection to DB");
        try (PreparedStatement prepS = Connector.getDbCon().prepareStatement(SQL_FIND_CLIENT)) {
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

    public boolean registrationClient(String login, String password, String email) throws ClientDAOException {
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

    public boolean updateClient(Client client) throws ClientDAOException{

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

    public Client getClientById(int id) throws ClientDAOException {
        Client client = new Client();
        logger.trace("getClientById");
        try (PreparedStatement prepS = Connector.getDbCon().prepareStatement(SQL_SELECT_CLIENT_BY_ID)) {
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

    public Client getClientByLogin(String login) throws ClientDAOException{
        Client client = new Client();
        logger.trace("getClientByLogin");
        try (PreparedStatement prepS = Connector.getDbCon().prepareStatement(SQL_SELECT_CLIENT_BY_LOGIN)) {
            prepS.setString(1, login);
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
