package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBo;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CustomerDao;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.dto.VehicleDTO;
import lk.ijse.entity.ReservationDetails;

import java.sql.SQLException;
import java.util.List;

public interface ReservationBo extends SuperBo {

    public List<String> getNameDetails() throws SQLException ;
    public CustomerDTO searchByName(String name) throws SQLException ;

    public  List<String> getIdsForReservaton() throws SQLException ;

    public VehicleDTO searchById(String vehicleId) throws SQLException ;

    public  String getCurrentId() throws SQLException ;

    public  boolean reservationComplete(ReservationDetails reservationDetails) throws SQLException ;

    }
