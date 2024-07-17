package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBo;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.EmployeeDao;
import lk.ijse.dto.EmployeeDTO;
import lk.ijse.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeBo extends SuperBo {
    public List<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException ;


    public  boolean delete(String employeeId) throws SQLException ;


    public  boolean save(EmployeeDTO employeeDTO) throws SQLException ;


    public  boolean update(EmployeeDTO employeeDTO) throws SQLException ;


    public  EmployeeDTO searchById(String employeeId) throws SQLException ;

}
