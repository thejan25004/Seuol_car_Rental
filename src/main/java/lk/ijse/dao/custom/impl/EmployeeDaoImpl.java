package lk.ijse.dao.custom.impl;
import lk.ijse.dao.custom.EmployeeDao;
import lk.ijse.dao.SQLUtil;
import lk.ijse.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

    @Override
    public List<Employee> getAll() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM employees");
        ArrayList<Employee> emList = new ArrayList<>();
        while (rst.next()){
            emList.add(new Employee(rst.getString("EmployeeId"), rst.getString("name"), rst.getString("employeeType"), rst.getDouble("salary"), rst.getString("userId")));
        }
        return emList;
    }

    @Override
    public  boolean delete(String employeeId) throws SQLException {
     return SQLUtil.execute("DELETE FROM employees WHERE EmployeeId = ?", employeeId);

    }
    @Override
    public  boolean save(Employee employee) throws SQLException {
       return SQLUtil.execute("INSERT INTO employees VALUES(?, ?, ?, ? , ? )" ,employee.getEmployeeId(),employee.getName(),employee.getEmployeeType(),employee.getSalary(),employee.getUserId());

    }
    @Override
    public  boolean update(Employee employee) throws SQLException {
        return SQLUtil.execute("UPDATE employees SET name = ? , employeeType = ? , salary = ? , userId = ?   WHERE EmployeeId = ? " , employee.getName(),employee.getEmployeeType(),employee.getSalary(),employee.getUserId(),employee.getEmployeeId());

    }

    @Override
    public  Employee searchById(String employeeId) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM employees WHERE EmployeeId  = ?" , employeeId + "");
        if (rst.next()){
            Employee employee = new Employee(employeeId + "" , rst.getString("name"), rst.getString("employeeType"), rst.getDouble("salary"), rst.getString("userId"));

            return employee;
        }

        return null;
    }

    @Override
    public Employee searchByName(String name) throws SQLException {
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException {
        return null;
    }

    @Override
    public List<Employee> getAllDesc() throws SQLException {
        return null;
    }

    @Override
    public List<String> getNameDetails() throws SQLException {
        return null;
    }


}
