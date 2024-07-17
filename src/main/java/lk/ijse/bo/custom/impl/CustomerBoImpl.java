package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.CustomerBo;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CustomerDao;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl  implements CustomerBo {

    CustomerDao customerDao = (CustomerDao) DAOFactory.getDAO(DAOFactory.DAOTypes.CUSTOMER);

    public List<CustomerDTO> getAll () throws SQLException, ClassNotFoundException{
        ArrayList<CustomerDTO> customers = new ArrayList<>();
        List<Customer> all = customerDao.getAll();
            for (Customer c : all){
                customers.add(new CustomerDTO(c.getCustomerld(),c.getName(),c.getContact(),c.getEmail(),c.getAddress(),c.getNic(),c.getBailVehicleNm()));
            }

        return customers;
    }


    public  boolean save (CustomerDTO customerDTO) throws SQLException {
        Customer customer = new Customer(customerDTO.getCustomerld(),customerDTO.getName(),customerDTO.getContact(),customerDTO.getEmail(),customerDTO.getAddress(),customerDTO.getNic(),customerDTO.getBailVehicleNm());
        return customerDao.save(customer);

    }


    @Override
    public  boolean update(CustomerDTO customerDTO) throws SQLException {
        Customer customer = new Customer(customerDTO.getCustomerld(),customerDTO.getName(),customerDTO.getContact(),customerDTO.getEmail(),customerDTO.getAddress(),customerDTO.getNic(),customerDTO.getBailVehicleNm());
        return customerDao.update(customer);

    }


    @Override
    public  boolean delete(String customerld) throws SQLException {
        return customerDao.delete(customerld);
    }

    @Override
    public CustomerDTO searchById(String customerld) throws SQLException {
        Customer customer = customerDao.searchById(customerld);
            return new CustomerDTO(customer.getCustomerld(),customer.getName(),customer.getContact(),customer.getEmail(),customer.getAddress(),customer.getNic(),customer.getBailVehicleNm());

    }




























//    public CustomerDTO searchById(String customerld) throws SQLException {
//        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer WHERE customerld  = ?" , customerld + "");
//        if (resultSet.next()){
//            CustomerDTO customer = new CustomerDTO(customerld + "",resultSet.getString("name"),resultSet.getString("contact"),resultSet.getString("email"),resultSet.getString("address"),resultSet.getString("nic"),resultSet.getString("bailVehicleNm"));
//            return customer;
//
//        }
//
//        return null;
//    }

//    public CustomerDTO searchByName(String name) throws SQLException {
//        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer WHERE name  = ?" , name + "");
//        if (resultSet.next()){
//            CustomerDTO customer = new CustomerDTO(resultSet.getString("customerld"),name + "",resultSet.getString("contact"),resultSet.getString("email"),resultSet.getString("address"),resultSet.getString("nic"),resultSet.getString("bailVehicleNm"));
//            return customer;
//
//        }
//
//        return null;
//
//
//    }

//    public List<String> getIds() throws SQLException {
//        ResultSet resultSet = SQLUtil.execute("SELECT customerld FROM customer");
//
//        List<String> idList = new ArrayList<>();
//        while (resultSet.next()) {
//            idList.add(resultSet.getString("customerld"));
//        }
//
//        return idList;
//
//    }



//
//    public List<CustomerDTO> getAllDesc() throws SQLException {
//        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer ORDER BY customerld DESC");
//
//        List<CustomerDTO> cusList = new ArrayList<>();
//        while (resultSet.next()) {
//            cusList.add(new CustomerDTO(
//                    resultSet.getString("customerld"),
//                    resultSet.getString("name"),
//                    resultSet.getString("contact"),
//                    resultSet.getString("email"),
//                    resultSet.getString("address"),
//                    resultSet.getString("nic"),
//                    resultSet.getString("bailVehicleNm")));
//        }
//
//        return cusList;
//
//    }

//    public List<String> getNameDetails() throws SQLException {
//        ResultSet resultSet = SQLUtil.execute("SELECT name FROM customer");
//
//        List<String> nameList = new ArrayList<>();
//        while (resultSet.next()) {
//            nameList.add(resultSet.getString("name"));
//        }
//
//        return nameList;
//    }
}
