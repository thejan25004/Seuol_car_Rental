package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Vehicle;

import java.sql.SQLException;
import java.util.List;

public interface VehicleDao extends CrudDAO<Vehicle> {
//    public  boolean delete(String vehicleId) throws SQLException ;
//
//
//    public  boolean save(Vehicle vehicle) throws SQLException ;
//
//
//    public  boolean update(Vehicle vehicle) throws SQLException ;
//
//    public  Vehicle searchById(String vehicleId) throws SQLException ;
//
//
//    public  List<Vehicle> getAll() throws SQLException ;
//
//
//    public  List<String> getIds() throws SQLException ;


    public  Vehicle searchBynumberPlate(String numberPlate) throws SQLException ;


    public  List<String> getIdsForReservaton() throws SQLException ;


    // public  boolean updateMillage(ReturnDetails returnDetails) throws SQLException ;

}
