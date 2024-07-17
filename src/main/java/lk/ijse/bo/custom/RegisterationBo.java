package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBo;

import java.sql.SQLException;

public interface RegisterationBo extends SuperBo {
    public boolean saveUser(String userId, String name, String password) throws SQLException ;
}
