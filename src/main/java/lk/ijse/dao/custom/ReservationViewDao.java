package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.RedDetailsView;

import java.sql.SQLException;
import java.util.List;

public interface ReservationViewDao extends CrudDAO<RedDetailsView> {
    public List<RedDetailsView> getAll() throws SQLException ;

}
