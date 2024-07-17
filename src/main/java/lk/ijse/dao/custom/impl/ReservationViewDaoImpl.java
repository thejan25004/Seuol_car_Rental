package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.ReservationViewDao;
import lk.ijse.entity.RedDetailsView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationViewDaoImpl implements ReservationViewDao {
    public List<RedDetailsView> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM ReservationDetails");
        ArrayList<RedDetailsView> emList = new ArrayList<>();

        while (resultSet.next()){
            emList.add(new RedDetailsView(
                    resultSet.getString("reservationId"),
                    resultSet.getString("vehicleId")
            ));

        }
        return emList;
    }

    @Override
    public boolean save(RedDetailsView customer) throws SQLException {
        return false;
    }

    @Override
    public boolean update(RedDetailsView customer) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String customerld) throws SQLException {
        return false;
    }

    @Override
    public RedDetailsView searchById(String customerld) throws SQLException {
        return null;
    }

    @Override
    public RedDetailsView searchByName(String name) throws SQLException {
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException {
        return null;
    }

    @Override
    public List<RedDetailsView> getAllDesc() throws SQLException {
        return null;
    }

    @Override
    public List<String> getNameDetails() throws SQLException {
        return null;
    }
}
