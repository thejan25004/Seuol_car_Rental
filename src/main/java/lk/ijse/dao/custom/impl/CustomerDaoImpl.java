package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.CustomerDao;
import lk.ijse.dao.SQLUtil;
import lk.ijse.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

   @Override
    public List<Customer> getAll () throws SQLException , ClassNotFoundException{
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer");

        ArrayList <Customer> customers = new ArrayList<>();

        while (resultSet.next()){
            customers.add(new Customer(
                    resultSet.getString("customerld"),
                    resultSet.getString("name"),
                    resultSet.getString("contact"),
                    resultSet.getString("email"),
                    resultSet.getString("address"),
                    resultSet.getString("nic"),
                    resultSet.getString("bailVehicleNm")));
        }

        return customers;
    }

    @Override
    public  boolean save (Customer customer) throws SQLException {
    return SQLUtil.execute("INSERT INTO customer VALUES(?, ?, ?, ? , ? , ?, ?)" , customer.getCustomerld() , customer.getName() , customer.getContact() , customer.getEmail() , customer.getAddress() , customer.getNic() , customer.getBailVehicleNm()) ;

    }

    @Override
    public  boolean update(Customer customer) throws SQLException {
       return SQLUtil.execute("UPDATE customer SET name = ? , contact = ? , email = ? , address = ? , nic = ? , bailVehicleNm = ?  WHERE customerld = ? " , customer.getName() , customer.getContact() , customer.getEmail() , customer.getAddress() , customer.getNic() , customer.getBailVehicleNm(), customer.getCustomerld())  ;


    }

    @Override
    public  boolean delete(String customerld) throws SQLException {
       return SQLUtil.execute("DELETE FROM customer WHERE customerld = ?" , customerld) ;

    }

    @Override
    public Customer searchById(String customerld) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer WHERE customerld  = ?" , customerld + "");
        if (resultSet.next()){
            Customer customer = new Customer(customerld + "",resultSet.getString("name"),resultSet.getString("contact"),resultSet.getString("email"),resultSet.getString("address"),resultSet.getString("nic"),resultSet.getString("bailVehicleNm"));
            return customer;

        }

        return null;
    }



    @Override
    public Customer searchByName(String name) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer WHERE name  = ?" , name + "");
        if (resultSet.next()){
            Customer customer = new Customer(resultSet.getString("customerld"),name + "",resultSet.getString("contact"),resultSet.getString("email"),resultSet.getString("address"),resultSet.getString("nic"),resultSet.getString("bailVehicleNm"));
            return customer;

        }

        return null;
    }



    @Override
    public List<String> getIds() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT customerld FROM customer");

        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString("customerld"));
        }

        return idList;

    }




    @Override
    public List<Customer> getAllDesc() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer ORDER BY customerld DESC");

        List<Customer> cusList = new ArrayList<>();
        while (resultSet.next()) {
            cusList.add(new Customer(
                    resultSet.getString("customerld"),
                    resultSet.getString("name"),
                    resultSet.getString("contact"),
                    resultSet.getString("email"),
                    resultSet.getString("address"),
                    resultSet.getString("nic"),
                    resultSet.getString("bailVehicleNm")));
        }

        return cusList;

    }

    @Override
    public List<String> getNameDetails() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT name FROM customer");

        List<String> nameList = new ArrayList<>();
        while (resultSet.next()) {
            nameList.add(resultSet.getString("name"));
        }

        return nameList;
    }




}
