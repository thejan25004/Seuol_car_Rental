package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.EmailBo;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CustomerDao;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public class EmailBoImpl implements EmailBo {

    CustomerDao customerDao = (CustomerDao) DAOFactory.getDAO(DAOFactory.DAOTypes.CUSTOMER);
    public List<String> getIds() throws SQLException {
      return  customerDao.getIds();

    }


    public CustomerDTO searchById(String customerld) throws SQLException {
        Customer customer = customerDao.searchById(customerld);
        return new CustomerDTO(customer.getCustomerld(),customer.getName(),customer.getContact(),customer.getEmail(),customer.getAddress(),customer.getNic(),customer.getBailVehicleNm());


    }


}
