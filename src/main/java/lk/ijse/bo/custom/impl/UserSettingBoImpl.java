package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.UserSettingBo;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.UserDao;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.User;

import java.sql.SQLException;

public class UserSettingBoImpl  implements UserSettingBo {
    UserDao userDao = (UserDao) DAOFactory.getDAO(DAOFactory.DAOTypes.USER);

    public boolean update(UserDTO userDTO) throws SQLException {
        User user = new User(userDTO.getUserId(),userDTO.getName(),userDTO.getPassword());
        return userDao.update(user);
    }


    public boolean delete(String userId) throws SQLException {
        return userDao.delete(userId);
    }
}
