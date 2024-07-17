package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.VehicleBo;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.VehicleDao;
import lk.ijse.dto.VehicleDTO;
import lk.ijse.entity.Vehicle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleBoImpl implements VehicleBo {

    VehicleDao vehicleDao = (VehicleDao) DAOFactory.getDAO(DAOFactory.DAOTypes.VEHICLE);
    public List<VehicleDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList <VehicleDTO> vehicles = new ArrayList<>();
        List <Vehicle> all = vehicleDao.getAll();

        for (Vehicle v : all){
            vehicles.add(new VehicleDTO(v.getVehicleId(),v.getModelName(),v.getBookingCost(),v.getNumberPlate(),v.getCurrentMilage(),v.getFirst_100Km_1km_charge(),v.getAfter_100Km_1km_charge(),v.getUserId()));
        }
        return  vehicles;
    }

    public  boolean delete(String vehicleId) throws SQLException {
        return  vehicleDao.delete(vehicleId);
    }

    public  boolean save(VehicleDTO vehicleDTO) throws SQLException {
        Vehicle vehicle = new Vehicle(vehicleDTO.getVehicleId(),vehicleDTO.getModelName(),vehicleDTO.getBookingCost(),vehicleDTO.getNumberPlate(),vehicleDTO.getCurrentMilage(),vehicleDTO.getFirst_100Km_1km_charge(),vehicleDTO.getFirst_100Km_1km_charge(),vehicleDTO.getUserId());
        return vehicleDao.save(vehicle);
    }

    public  boolean update(VehicleDTO vehicleDTO) throws SQLException {
        Vehicle vehicle = new Vehicle(vehicleDTO.getVehicleId(),vehicleDTO.getModelName(),vehicleDTO.getBookingCost(),vehicleDTO.getNumberPlate(),vehicleDTO.getCurrentMilage(),vehicleDTO.getFirst_100Km_1km_charge(),vehicleDTO.getFirst_100Km_1km_charge(),vehicleDTO.getUserId());
        return  vehicleDao.update(vehicle);
    }

    public  VehicleDTO searchById(String vehicleId) throws SQLException {
        Vehicle vehicle = vehicleDao.searchById(vehicleId);
        return new VehicleDTO(vehicle.getVehicleId(),vehicle.getModelName(),vehicle.getBookingCost(),vehicle.getNumberPlate(),vehicle.getCurrentMilage(),vehicle.getFirst_100Km_1km_charge(),vehicle.getFirst_100Km_1km_charge(),vehicle.getUserId());

    }



}
