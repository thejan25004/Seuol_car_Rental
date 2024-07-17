package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBo;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.List;

public interface DashboardBo extends SuperBo {
    public List<CustomerDTO> getAllDesc() throws SQLException ;
    public List<SupplierDTO> getAll() throws SQLException, ClassNotFoundException ;
}
