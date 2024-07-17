package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.LogInBo;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.UserDao;

import java.io.IOException;
import java.sql.SQLException;

public class LogInBoImpl implements LogInBo {
    UserDao userDao = (UserDao) DAOFactory.getDAO(DAOFactory.DAOTypes.USER);

    public String checkCreditianal(String userId, String pw) throws SQLException, IOException {
        return userDao.checkCreditianal(userId,pw);
    }
}
