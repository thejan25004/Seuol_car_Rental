package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBo;

import java.io.IOException;
import java.sql.SQLException;

public interface LogInBo extends SuperBo {
    public String checkCreditianal(String userId, String pw) throws SQLException, IOException ;
}
