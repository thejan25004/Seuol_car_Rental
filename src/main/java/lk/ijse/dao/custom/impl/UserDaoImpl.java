package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.UserDao;
import lk.ijse.entity.User;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public List<User> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(User customer) throws SQLException {
        return false;
    }

    @Override
    public boolean update(User user) throws SQLException {
        return SQLUtil.execute("UPDATE users SET name = ? , password = ?   WHERE userId = ?", user.getName(), user.getPassword(), user.getUserId());

    }

    @Override
    public boolean delete(String userId) throws SQLException {
        return SQLUtil.execute("DELETE FROM users WHERE userId = ?", userId);

    }

    @Override
    public User searchById(String customerld) throws SQLException {
        return null;
    }

    @Override
    public User searchByName(String name) throws SQLException {
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException {
        return null;
    }

    @Override
    public List<User> getAllDesc() throws SQLException {
        return null;
    }

    @Override
    public List<String> getNameDetails() throws SQLException {
        return null;
    }

    @Override
    public String checkCreditianal(String userId, String pw) throws SQLException, IOException {
        String sql = "SELECT password FROM users WHERE userId = ?";
        ResultSet resultSet = SQLUtil.execute(sql, userId);

        String dbPw = null;

        if (resultSet.next()) {
             dbPw = resultSet.getString("password");
        }
        return dbPw ;
    }

    public boolean saveUser(String userId, String name, String password) throws SQLException {
        return SQLUtil.execute("INSERT INTO users VALUES(?, ?, ?)" , userId,name,password);


    }



}
