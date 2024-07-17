package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Return;

import java.sql.SQLException;

public interface ReturnDao extends CrudDAO<Return> {
    public  String getCurrentId() throws SQLException ;


  //  public ArrayList<Return> getAll() throws SQLException ;

}
