package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBo;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.dto.ReservationDTO;
import lk.ijse.dto.VehicleDTO;
import lk.ijse.entity.Reservation;
import lk.ijse.entity.ReturnDetails;

import java.sql.SQLException;
import java.util.List;

public interface ReturnBo  extends SuperBo {

    public List<String> getIds() throws SQLException ;

    public CustomerDTO searchById(String customerld) throws SQLException ;

    public  List<String> getIdsVehicle() throws SQLException ;

    public VehicleDTO searchBynumberPlate(String numberPlate) throws SQLException ;

    public  List<String> getIdsForReturns() throws SQLException ;

    public ReservationDTO searchByIdForReturns (String reservationId) throws SQLException ;
    public  String getCurrentId() throws SQLException ;

    public  boolean returnComplete(ReturnDetails returnDetails) throws SQLException ;
}
