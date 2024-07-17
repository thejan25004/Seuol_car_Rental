package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.RegisterationBo;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.UserDao;

import java.sql.SQLException;

public class RegisterationBoImpl implements RegisterationBo {

    UserDao userDao = (UserDao) DAOFactory.getDAO(DAOFactory.DAOTypes.USER);

    public boolean saveUser(String userId, String name, String password) throws SQLException {
        return userDao.saveUser(userId,name,password);
    }
}
