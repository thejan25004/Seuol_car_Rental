package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBo;
import lk.ijse.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBo extends SuperBo {
    public List<CustomerDTO> getAll () throws SQLException, ClassNotFoundException;

    public  boolean save (CustomerDTO customerDTO) throws SQLException ;

    public  boolean update(CustomerDTO customerDTO) throws SQLException ;

    public  boolean delete(String customerld) throws SQLException ;

    public CustomerDTO searchById(String customerld) throws SQLException ;

}
