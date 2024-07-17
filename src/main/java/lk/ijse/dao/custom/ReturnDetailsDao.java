package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.ReturnDetails;

import java.sql.SQLException;

public interface ReturnDetailsDao  extends CrudDAO<ReturnDetails> {
  //  public  boolean save(ReturnDetails returnDetails) throws SQLException ;

    public  boolean saveReturn(ReturnDetails returnDetails) throws SQLException ;

    public  boolean updateMillage(ReturnDetails returnDetails) throws SQLException ;



}
