package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBo;

import java.io.IOException;
import java.sql.SQLException;

public interface CheckIdAndPasswordBO extends SuperBo {

    String checkCreditianal(String userId, String pw) throws SQLException, IOException;
}
