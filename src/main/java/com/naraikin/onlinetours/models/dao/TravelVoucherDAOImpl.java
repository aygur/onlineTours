package com.naraikin.onlinetours.models.dao;

import com.naraikin.onlinetours.common.exception.ClientDAOException;
import com.naraikin.onlinetours.common.exception.TourDAOException;
import com.naraikin.onlinetours.common.exception.TravelVoucherDAOException;
import com.naraikin.onlinetours.common.exception.VoucherStatusDAOException;
import com.naraikin.onlinetours.models.connector.Connector;
import com.naraikin.onlinetours.models.dao.interfaces.ClientDAO;
import com.naraikin.onlinetours.models.dao.interfaces.TourDAO;
import com.naraikin.onlinetours.models.dao.interfaces.TravelVoucherDAO;
import com.naraikin.onlinetours.models.dao.interfaces.VoucherStatusDAO;
import com.naraikin.onlinetours.models.pojo.Client;
import com.naraikin.onlinetours.models.pojo.Tour;
import com.naraikin.onlinetours.models.pojo.TravelVoucher;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitrii on 07.03.17.
 */
@Repository("TravelVoucherDAOImpl")
public class TravelVoucherDAOImpl implements TravelVoucherDAO {

    private TourDAO tourDAO;
    private ClientDAO clientDAO;
    private VoucherStatusDAO voucherStatusDAO;

    @Autowired
    @Qualifier("TourDAOImplH")
    public void setTourDAO(TourDAO tourDAO) {
        this.tourDAO = tourDAO;
    }

    @Autowired
    @Qualifier("ClientDAOImplH")
    public void setClientDAO(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Autowired
    @Qualifier("VoucherStatusDAOImplH")
    public void setVoucherStatusDAO(VoucherStatusDAO voucherStatusDAO) {
        this.voucherStatusDAO = voucherStatusDAO;
    }

    private static Logger logger = Logger.getLogger(TravelVoucherDAOImpl.class);
    public static final String SELECT_ALL_TRAVEL_VOUCHERS = "SELECT * FROM travel_voucher";
    public static final String DELETE_TRAVEL_VOUCHERS_BY_ID = "delete from travel_voucher " +
            "where idtravel_voucher = ? ";
    public static final String SELECT_TRAVEL_VOUCHERS_BY_ID= "SELECT * FROM travel_voucher WHERE idtravel_voucher=?";
    public static final String SELECT_TRAVEL_VOUCHERS_BY_CLIENT= "SELECT * FROM travel_voucher WHERE client_id=?";
    public static final String UPDATE_TRAVEL_VOUCHERS = "UPDATE travel_voucher " +
            "SET payment_date = ?, payment_num =?, status_id = ? " +
            "WHERE idtravel_voucher =?";
    public static final String INSERT_TRAVEL_VOUCHERS = "INSERT INTO travel_voucher " +
            "(tour_id, client_id, status_id, booking_date)" +
            "VALUES(?, ?, ?, ? )";



    public List<TravelVoucher> getAllByClient(Client client)  throws TravelVoucherDAOException{
        List<TravelVoucher> travelVouchers = new ArrayList<>();
        try (PreparedStatement preparedStatement = Connector.getDbCon().prepareStatement(SELECT_TRAVEL_VOUCHERS_BY_CLIENT)){
            preparedStatement.setInt(1, client.getIdclient());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                TravelVoucher travelVoucher = new TravelVoucher();
                travelVoucher.setIdtravel_voucher(rs.getInt("idtravel_voucher"));
                travelVoucher.setTour(tourDAO.getTourById(
                        rs.getInt("tour_id")));
                travelVoucher.setClient(clientDAO.getClientById(
                        rs.getInt("client_id")));
                travelVoucher.setVoucherStatus(
                        voucherStatusDAO.getVoucherStatusById(
                                rs.getInt("status_id")
                        ));
                travelVoucher.setPayment_date(rs.getTimestamp("payment_date"));
                travelVoucher.setBooking_date(rs.getTimestamp("booking_date"));
                travelVoucher.setPayment_num(rs.getString("payment_num"));
                travelVouchers.add(travelVoucher);
            }

        }catch (SQLException | ClientDAOException | VoucherStatusDAOException e){
            logger.error(e);
            throw new TravelVoucherDAOException();
        } catch (TourDAOException e){
            logger.error(e);
            throw new TravelVoucherDAOException();
        }
        return travelVouchers;
    }


    public List<TravelVoucher> getAll() throws TravelVoucherDAOException {
        List<TravelVoucher> travelVouchers = new ArrayList<>();
        try (Statement statement = Connector.getDbCon().createStatement()){
            ResultSet rs = statement.executeQuery(SELECT_ALL_TRAVEL_VOUCHERS);
            while (rs.next()){
                TravelVoucher travelVoucher = new TravelVoucher();
                travelVoucher.setIdtravel_voucher(rs.getInt("idtravel_voucher"));
                travelVoucher.setTour(tourDAO.getTourById(
                        rs.getInt("tour_id")));
                travelVoucher.setClient(clientDAO.getClientById(
                        rs.getInt("client_id")));
                travelVoucher.setVoucherStatus(
                        voucherStatusDAO.getVoucherStatusById(
                                rs.getInt("status_id")
                        ));
                travelVoucher.setPayment_date(rs.getTimestamp("payment_date"));
                travelVoucher.setBooking_date(rs.getTimestamp("booking_date"));
                travelVoucher.setPayment_num(rs.getString("payment_num"));
                travelVouchers.add(travelVoucher);
            }

        }catch (SQLException | ClientDAOException | VoucherStatusDAOException e){
            logger.error(e);
            throw new TravelVoucherDAOException();
        } catch (TourDAOException e){
            logger.error(e);
            throw new TravelVoucherDAOException();
        }
        return travelVouchers;
    }

    public int createTravelVoucher(TravelVoucher travelVoucher) throws TravelVoucherDAOException{
        int id_v= 0;
        try (PreparedStatement ps
                     = Connector.getDbCon().prepareStatement(INSERT_TRAVEL_VOUCHERS, Statement.RETURN_GENERATED_KEYS)){

            tourDAO.setBooking(travelVoucher.getTour());
            ps.setInt(1, travelVoucher.getTour().getIdtur());
            ps.setInt(2, travelVoucher.getClient().getIdclient());
            ps.setInt(3, travelVoucher.getVoucherStatus().getIdvoucher_status());
            ps.setTimestamp(4, travelVoucher.getBooking_date());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            while (keys.next()){
                id_v = keys.getInt(1);
            }


        } catch (SQLException | TourDAOException e){
            logger.error(e);
            throw new TravelVoucherDAOException();
        }
        return id_v;
    }

    @Override
    public TravelVoucher getTravelVoucherById(int id) throws TravelVoucherDAOException {
        TravelVoucher travelVoucher = new TravelVoucher();
        try (PreparedStatement preparedStatement = Connector.getDbCon().prepareStatement(SELECT_TRAVEL_VOUCHERS_BY_ID)){
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){

                travelVoucher.setIdtravel_voucher(rs.getInt("idtravel_voucher"));
                travelVoucher.setTour(tourDAO.getTourById(
                        rs.getInt("tour_id")));
                travelVoucher.setClient(clientDAO.getClientById(
                        rs.getInt("client_id")));
                travelVoucher.setVoucherStatus(
                        voucherStatusDAO.getVoucherStatusById(
                                rs.getInt("status_id")
                        ));
                travelVoucher.setPayment_date(rs.getTimestamp("payment_date"));
                travelVoucher.setBooking_date(rs.getTimestamp("booking_date"));
                travelVoucher.setPayment_num(rs.getString("payment_num"));
            }

        }catch (SQLException | ClientDAOException | VoucherStatusDAOException e){
            logger.error(e);
            throw new TravelVoucherDAOException();
        } catch (TourDAOException e){
            logger.error(e);
            throw new TravelVoucherDAOException();
        }
        return travelVoucher;
    }

    @Override
    public Integer deleteTravelVoucher(TravelVoucher travelVoucher) throws TravelVoucherDAOException {
        int rs = 0;
        try (PreparedStatement preparedStatement = Connector.getDbCon().prepareStatement(DELETE_TRAVEL_VOUCHERS_BY_ID)){
            preparedStatement.setInt(1, travelVoucher.getIdtravel_voucher());

            Tour tour = travelVoucher.getTour();
            tour.setBooking((short) 0);
            tourDAO.setBooking(tour);
            rs = preparedStatement.executeUpdate();
        }catch (SQLException  e){
            logger.error(e);
            throw new TravelVoucherDAOException();
        } catch (TourDAOException e){
            logger.error(e);
            throw new TravelVoucherDAOException();
        }
        return rs;
    }

    @Override
    public Integer updateTravelVoucher(TravelVoucher travelVoucher) throws TravelVoucherDAOException {
        int id_v = 0;
        try (PreparedStatement ps
                     = Connector.getDbCon().prepareStatement(UPDATE_TRAVEL_VOUCHERS)){

            ps.setTimestamp(1, travelVoucher.getPayment_date());
            ps.setString(2, travelVoucher.getPayment_num());
            ps.setInt(3, 2);
            ps.setInt(4, travelVoucher.getIdtravel_voucher());
            id_v = ps.executeUpdate();
        } catch (SQLException e){
            logger.error(e);
            throw new TravelVoucherDAOException();
        }
        return id_v;
    }


}
