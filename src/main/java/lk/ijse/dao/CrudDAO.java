package lk.ijse.dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO <T> extends SuperDao{
    public List<T> getAll () throws SQLException, ClassNotFoundException;


    public  boolean save (T customer) throws SQLException ;



    public  boolean update(T customer) throws SQLException ;


    public  boolean delete(String customerld) throws SQLException ;


    public  T searchById(String customerld) throws SQLException ;


    public  T searchByName(String name) throws SQLException ;


    public List<String> getIds() throws SQLException ;


    public List<T> getAllDesc() throws SQLException ;


    public List<String> getNameDetails() throws SQLException ;
}
