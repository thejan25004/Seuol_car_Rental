package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.DashboardBo;
import lk.ijse.bo.custom.SupplierBo;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CustomerDao;
import lk.ijse.dao.custom.SupplierDao;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.dto.SupplierDTO;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DashboardBoImpl implements DashboardBo {
    CustomerDao customerDao = (CustomerDao) DAOFactory.getDAO(DAOFactory.DAOTypes.CUSTOMER);

    SupplierDao supplierDao = (SupplierDao) DAOFactory.getDAO(DAOFactory.DAOTypes.SUPPLIER);


    public List<CustomerDTO> getAllDesc() throws SQLException {
        ArrayList <CustomerDTO> customerDTOS = new ArrayList<>();
        List<Customer> all = customerDao.getAllDesc();

        for (Customer c : all){
            customerDTOS.add(new CustomerDTO(c.getCustomerld(),c.getName(),c.getContact(),c.getEmail(),c.getAddress(),c.getNic(),c.getBailVehicleNm()));
        }

        return customerDTOS ;

    }

    public List<SupplierDTO> getAll() throws SQLException, ClassNotFoundException {
            ArrayList <SupplierDTO> supplierDTOS = new ArrayList<>();
            List <Supplier> all = supplierDao.getAll();

            for (Supplier  s : all ){
                supplierDTOS.add(new SupplierDTO(s.getSupplierId(),s.getName(),s.getContact(),s.getSpecialCar(),s.getSpecialCarCost(),s.getVehicleId()));
            }
        return  supplierDTOS;
    }


}
