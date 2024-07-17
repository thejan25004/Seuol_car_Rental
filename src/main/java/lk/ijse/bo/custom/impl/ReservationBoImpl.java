package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.CustomerBo;
import lk.ijse.bo.custom.ReservationBo;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CustomerDao;
import lk.ijse.dao.custom.ReservationDao;
import lk.ijse.dao.custom.ReservationDetailsDao;
import lk.ijse.dao.custom.VehicleDao;
import lk.ijse.dao.custom.impl.ReservationDetailsDaoImpl;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.dto.VehicleDTO;
import lk.ijse.entity.Customer;
import lk.ijse.entity.ReservationDetails;
import lk.ijse.entity.Vehicle;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationBoImpl implements ReservationBo {

    CustomerDao customerDao = (CustomerDao) DAOFactory.getDAO(DAOFactory.DAOTypes.CUSTOMER);

    VehicleDao vehicleDao = (VehicleDao) DAOFactory.getDAO(DAOFactory.DAOTypes.VEHICLE);

    ReservationDao reservationDao = (ReservationDao) DAOFactory.getDAO(DAOFactory.DAOTypes.RESERVATION);

    ReservationDetailsDao reservationDetailsDao = (ReservationDetailsDao) DAOFactory.getDAO(DAOFactory.DAOTypes.RESERVATIONDETAILS);

    public List<String> getNameDetails() throws SQLException {
        return customerDao.getNameDetails();
    }

    public CustomerDTO searchByName(String name) throws SQLException {
        Customer customer = customerDao.searchByName(name);
        return new CustomerDTO(customer.getCustomerld(),customer.getName(),customer.getContact(),customer.getEmail(),customer.getAddress(),customer.getNic(),customer.getBailVehicleNm());
    }
    public  List<String> getIdsForReservaton() throws SQLException {
        return  vehicleDao.getIdsForReservaton();
    }

    public VehicleDTO searchById(String vehicleId) throws SQLException {
         Vehicle vehicle = vehicleDao.searchById(vehicleId);
        return new VehicleDTO(vehicle.getVehicleId(),vehicle.getModelName(),vehicle.getBookingCost(),vehicle.getNumberPlate(),vehicle.getCurrentMilage(),vehicle.getFirst_100Km_1km_charge(),vehicle.getFirst_100Km_1km_charge(),vehicle.getUserId());

    }

    public  String getCurrentId() throws SQLException {
       return  reservationDao.getCurrentId();
    }

    public  boolean reservationComplete(ReservationDetails reservationDetails) throws SQLException {
        /*  TRANSACTION CODE BLOCK  */
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        //   ReservationDao reservationDao = new ReservationDaoImpl();
     //   ReservationDetailsDao reservationDetailsDao = new ReservationDetailsDaoImpl();

        try {
            if (reservationDetailsDao.SaveReservation(reservationDetails)){ //return table
                if (reservationDetailsDao.save(reservationDetails)){
                    connection.commit();
                    return true;
                }
            }
            connection.rollback();
            return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }

}
