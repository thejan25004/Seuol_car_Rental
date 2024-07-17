package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBo;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.User;

import java.sql.SQLException;

public interface UserSettingBo  extends SuperBo {
    public boolean update(UserDTO userDTO) throws SQLException ;
    public boolean delete(String userId) throws SQLException;

}
