package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.VehicleDao;
import lk.ijse.entity.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDaoImpl implements VehicleDao {

    @Override
    public List<Vehicle> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Vehicles");
        ArrayList<Vehicle> vehicleListList = new ArrayList<>();

        while (resultSet.next()){
            vehicleListList.add(new Vehicle(resultSet.getString("vehicleId"), resultSet.getString("modelName"), resultSet.getDouble("bookingCost"), resultSet.getString("numberPlate"), resultSet.getDouble("currentMilage"), resultSet.getDouble("first_100Km_1km_charge"), resultSet.getDouble("after_100Km_1km_charge"), resultSet.getString("userId")));

        }
        return vehicleListList;
    }
    @Override
    public  boolean delete(String vehicleId) throws SQLException {
        return SQLUtil.execute("DELETE FROM Vehicles WHERE vehicleId = ?" , vehicleId);
    }
    @Override
    public  boolean save(Vehicle vehicle) throws SQLException {
        return SQLUtil.execute("INSERT INTO Vehicles VALUES(?, ?, ?, ? , ?, ? ,? , ?)" , vehicle.getVehicleId(), vehicle.getModelName(), vehicle.getBookingCost(), vehicle.getNumberPlate(), vehicle.getCurrentMilage(), vehicle.getFirst_100Km_1km_charge(), vehicle.getAfter_100Km_1km_charge(), vehicle.getUserId());
    }
    @Override
    public  boolean update(Vehicle vehicle) throws SQLException {
        return SQLUtil.execute("UPDATE Vehicles SET modelName = ? , bookingCost = ? ,   numberPlate = ? ,  currentMilage = ?,first_100Km_1km_charge = ?,after_100Km_1km_charge = ?, userId = ?   WHERE vehicleId = ? " ,  vehicle.getModelName(), vehicle.getBookingCost(), vehicle.getNumberPlate(), vehicle.getCurrentMilage(), vehicle.getFirst_100Km_1km_charge(), vehicle.getAfter_100Km_1km_charge(), vehicle.getUserId(),vehicle.getVehicleId());
    }
    @Override
    public  Vehicle searchById(String vehicleId) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Vehicles WHERE vehicleId  = ?" , vehicleId + "");
        if (resultSet.next()){
            Vehicle vehicle = new Vehicle(vehicleId + "" , resultSet.getString("modelName"), resultSet.getDouble("bookingCost"), resultSet.getString("numberPlate"), resultSet.getDouble("currentMilage"), resultSet.getDouble("first_100Km_1km_charge"), resultSet.getDouble("after_100Km_1km_charge"), resultSet.getString("userId"));
            return vehicle;
        }
        return null;
    }

    @Override
    public Vehicle searchByName(String name) throws SQLException {
        return null;
    }


    @Override
    public  List<String> getIds() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT numberPlate FROM Vehicles");
        List<String> idList = new ArrayList<>();

        while (resultSet.next()) {
            idList.add(resultSet.getString("numberPlate"));

        }
        return idList;
    }

    @Override
    public List<Vehicle> getAllDesc() throws SQLException {
        return null;
    }

    @Override
    public List<String> getNameDetails() throws SQLException {
        return null;
    }


    public  Vehicle searchBynumberPlate(String numberPlate) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Vehicles WHERE numberPlate  = ?" , numberPlate + "");

        if (resultSet.next()){
            Vehicle vehicle = new Vehicle(resultSet.getString("vehicleId"), resultSet.getString("modelName"), resultSet.getDouble("bookingCost"), numberPlate + "", resultSet.getDouble("currentMilage"), resultSet.getDouble("first_100Km_1km_charge"), resultSet.getDouble("after_100Km_1km_charge"), resultSet.getString("userId"));
            return vehicle;
        }
        return null;
    }


//    public  boolean updateMillage(ReturnDetails returnDetails) throws SQLException {
//        return SQLUtil.execute("UPDATE Vehicles SET currentMilage = ? WHERE vehicleId = ?", returnDetails.getAfterRideMilage(), returnDetails.getVehicleId());
//
//    }

    public  List<String> getIdsForReservaton() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT vehicleId FROM Vehicles");
        List<String> idList = new ArrayList<>();

        while (resultSet.next()) {
            idList.add(resultSet.getString("vehicleId"));
        }
        return idList;
    }



}
