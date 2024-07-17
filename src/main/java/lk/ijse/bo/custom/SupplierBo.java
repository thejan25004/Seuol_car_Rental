package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBo;
import lk.ijse.dto.SupplierDTO;
import lk.ijse.dto.VehicleDTO;
import lk.ijse.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SupplierBo extends SuperBo {
    public List<SupplierDTO> getAll() throws SQLException, ClassNotFoundException ;


    public  boolean delete(String supplierId) throws SQLException ;


    public  boolean save(SupplierDTO supplierDTO) throws SQLException ;


    public  boolean update(SupplierDTO supplierDTO) throws SQLException ;


    public  SupplierDTO searchById(String supplierId) throws SQLException ;


    public VehicleDTO searchByVehicleId(String vehicleId) throws SQLException ;

}
