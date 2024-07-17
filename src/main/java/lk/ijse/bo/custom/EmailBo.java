package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBo;
import lk.ijse.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public interface EmailBo extends SuperBo {
    public List<String> getIds() throws SQLException ;

    public CustomerDTO searchById(String customerld) throws SQLException ;
}
