package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBo;
import lk.ijse.dto.VehicleDTO;
import lk.ijse.entity.Vehicle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface VehicleBo extends SuperBo {
    public List<VehicleDTO> getAll() throws SQLException, ClassNotFoundException;
    public  boolean delete(String vehicleId) throws SQLException ;
    public  boolean save(VehicleDTO vehicleDTO) throws SQLException ;
    public  boolean update(VehicleDTO vehicleDTO) throws SQLException ;
    public  VehicleDTO searchById(String vehicleId) throws SQLException ;
}
