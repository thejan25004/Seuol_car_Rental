package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.CheckIdAndPasswordBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.UserDao;

import java.io.IOException;
import java.sql.SQLException;

public class CheckIdAndPasswordBOImpl implements CheckIdAndPasswordBO {
    UserDao userDao = (UserDao) DAOFactory.getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public String checkCreditianal(String userId, String pw) throws SQLException, IOException {
        return userDao.checkCreditianal(userId,pw);
    }
}
