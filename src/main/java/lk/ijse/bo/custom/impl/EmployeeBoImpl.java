package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.EmployeeBo;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.EmployeeDao;
import lk.ijse.dto.EmployeeDTO;
import lk.ijse.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBoImpl implements EmployeeBo {

    EmployeeDao employeeDao = (EmployeeDao) DAOFactory.getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    public List<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList <EmployeeDTO> employeeDTOS = new ArrayList<>();
        List<Employee> all = employeeDao.getAll();
        for (Employee e : all){
            employeeDTOS.add(new EmployeeDTO(e.getEmployeeId(),e.getName(),e.getEmployeeType(),e.getSalary(),e.getUserId()));
        }
      return  employeeDTOS;
    }

    public  boolean delete(String employeeId) throws SQLException {
       return employeeDao.delete(employeeId);
    }

    public  boolean save(EmployeeDTO employeeDTO) throws SQLException {
        Employee employee = new Employee(employeeDTO.getEmployeeId(),employeeDTO.getName(),employeeDTO.getEmployeeType(),employeeDTO.getSalary(),employeeDTO.getUserId());
        return employeeDao.save(employee);
    }

    public  boolean update(EmployeeDTO employeeDTO) throws SQLException {
        Employee employee = new Employee(employeeDTO.getEmployeeId(),employeeDTO.getName(),employeeDTO.getEmployeeType(),employeeDTO.getSalary(),employeeDTO.getUserId());
        return  employeeDao.update(employee);
    }

    public  EmployeeDTO searchById(String employeeId) throws SQLException {
        Employee employee = employeeDao.searchById(employeeId);
            return new EmployeeDTO(employee.getEmployeeId(),employee.getName(),employee.getEmployeeType(),employee.getSalary(),employee.getUserId());

    }
}
