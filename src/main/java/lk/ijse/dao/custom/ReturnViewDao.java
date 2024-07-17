package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.ReturnDetailsView;

import java.sql.SQLException;
import java.util.List;

public interface ReturnViewDao extends CrudDAO<ReturnDetailsView> {

    public List<ReturnDetailsView> getAll() throws SQLException ;

}
