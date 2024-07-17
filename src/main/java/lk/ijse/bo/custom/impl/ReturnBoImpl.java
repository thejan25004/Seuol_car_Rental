package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.ReturnBo;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.*;
import lk.ijse.dao.custom.impl.ReturnDetailsDaoImpl;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.dto.ReservationDTO;
import lk.ijse.dto.VehicleDTO;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Reservation;
import lk.ijse.entity.ReturnDetails;
import lk.ijse.entity.Vehicle;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ReturnBoImpl  implements ReturnBo {

    CustomerDao customerDao = (CustomerDao) DAOFactory.getDAO(DAOFactory.DAOTypes.CUSTOMER);

    VehicleDao vehicleDao = (VehicleDao) DAOFactory.getDAO(DAOFactory.DAOTypes.VEHICLE);

    ReservationDao reservationDao = (ReservationDao) DAOFactory.getDAO(DAOFactory.DAOTypes.RESERVATION);

    ReturnDao returnDao = (ReturnDao) DAOFactory.getDAO(DAOFactory.DAOTypes.RETURN);

    ReturnDetailsDao returnDetailsDao = (ReturnDetailsDao) DAOFactory.getDAO(DAOFactory.DAOTypes.RETURNDETAILS);


    public List<String> getIds() throws SQLException {
        return  customerDao.getIds();
    }

    public CustomerDTO searchById(String customerld) throws SQLException {
        Customer customer = customerDao.searchById(customerld);
        return new CustomerDTO(customer.getCustomerld(),customer.getName(),customer.getContact(),customer.getEmail(),customer.getAddress(),customer.getNic(),customer.getBailVehicleNm());
    }

    public  List<String> getIdsVehicle() throws SQLException {
        return  vehicleDao.getIds();
    }

    public VehicleDTO searchBynumberPlate(String numberPlate) throws SQLException {
        Vehicle vehicle = vehicleDao.searchBynumberPlate(numberPlate);
        return new VehicleDTO(vehicle.getVehicleId(),vehicle.getModelName(),vehicle.getBookingCost(),vehicle.getNumberPlate(),vehicle.getCurrentMilage(),vehicle.getFirst_100Km_1km_charge(),vehicle.getFirst_100Km_1km_charge(),vehicle.getUserId());

    }

    public  List<String> getIdsForReturns() throws SQLException {
        return reservationDao.getIds();
    }

    public ReservationDTO searchByIdForReturns (String reservationId) throws SQLException {
        Reservation reservation = reservationDao.searchById(reservationId);
        return new ReservationDTO(reservation.getReservationId(),reservation.getReservationDate(),reservation.getReturnDate(),reservation.getCustomerld());
    }

    public  String getCurrentId() throws SQLException {
        return  returnDao.getCurrentId();
    }
    public  boolean returnComplete(ReturnDetails returnDetails) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

//        VehicleDao vehicleDao = new VehicleDaoImpl();
//        ReturnDao returnDao = new ReturnDaoImpl();
    //    ReturnDetailsDao returnDetailsDao = new ReturnDetailsDaoImpl();

        try {
            if (returnDetailsDao.saveReturn(returnDetails)){ //return table
                if (returnDetailsDao.save(returnDetails)){
                    if (returnDetailsDao.updateMillage(returnDetails)){
                        connection.commit();
                        return true;
                    }
                }
            }
            connection.rollback();
            return false;
        } catch (SQLException e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
