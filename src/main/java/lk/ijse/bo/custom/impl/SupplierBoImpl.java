package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.SupplierBo;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CustomerDao;
import lk.ijse.dao.custom.SupplierDao;
import lk.ijse.dao.custom.VehicleDao;
import lk.ijse.dto.SupplierDTO;
import lk.ijse.dto.VehicleDTO;
import lk.ijse.entity.Supplier;
import lk.ijse.entity.Vehicle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBoImpl implements SupplierBo  {

    SupplierDao supplierDao = (SupplierDao) DAOFactory.getDAO(DAOFactory.DAOTypes.SUPPLIER);
    VehicleDao vehicleDao = (VehicleDao) DAOFactory.getDAO(DAOFactory.DAOTypes.VEHICLE);

    public List<SupplierDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList <SupplierDTO> suppliers = new ArrayList<>();
        List <Supplier> all = supplierDao.getAll();

        for (Supplier s : all){
            suppliers.add(new SupplierDTO(s.getSupplierId(),s.getName(),s.getContact(),s.getSpecialCar(),s.getSpecialCarCost(),s.getVehicleId()));
        }
      return  suppliers ;
    }

    public  boolean delete(String supplierId) throws SQLException {
        return supplierDao.delete(supplierId);
    }

    public  boolean save(SupplierDTO supplierDTO) throws SQLException {
        Supplier supplier = new Supplier(supplierDTO.getSupplierId(),supplierDTO.getName(),supplierDTO.getContact(),supplierDTO.getSpecialCar(),supplierDTO.getSpecialCarCost(),supplierDTO.getVehicleId());
        return supplierDao.save(supplier);
    }

    public  boolean update(SupplierDTO supplierDTO) throws SQLException {
        Supplier supplier = new Supplier(supplierDTO.getSupplierId(),supplierDTO.getName(),supplierDTO.getContact(),supplierDTO.getSpecialCar(),supplierDTO.getSpecialCarCost(),supplierDTO.getVehicleId());
        return supplierDao.update(supplier);
    }

    public  SupplierDTO searchById(String supplierId) throws SQLException {
         Supplier supplier = supplierDao.searchById(supplierId);
       //  if (supplier == null){
             return new SupplierDTO(supplier.getSupplierId(),supplier.getName(),supplier.getContact(),supplier.getSpecialCar(),supplier.getSpecialCarCost(),supplier.getVehicleId());
      //   }
       //  return null ;
    }

    public VehicleDTO searchByVehicleId(String vehicleId) throws SQLException {
        Vehicle vehicle = vehicleDao.searchById(vehicleId);
     //   if (vehicle == null){
            return new VehicleDTO(vehicle.getVehicleId(),vehicle.getModelName(),vehicle.getBookingCost(),vehicle.getNumberPlate(),vehicle.getCurrentMilage(),vehicle.getFirst_100Km_1km_charge(),vehicle.getAfter_100Km_1km_charge(),vehicle.getUserId());
     //   }

     //   return null ;
    }



}
