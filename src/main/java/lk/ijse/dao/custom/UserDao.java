package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.User;

import java.io.IOException;
import java.sql.SQLException;

public interface UserDao extends CrudDAO<User> {

//    public  boolean update(User user) throws SQLException ;


//    public  boolean delete(String userId) throws SQLException ;

    public String checkCreditianal(String userId, String pw) throws SQLException, IOException ;

    public boolean saveUser(String userId, String name, String password) throws SQLException ;











}
