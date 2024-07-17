package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.ReturnViewDao;
import lk.ijse.entity.ReturnDetailsView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReturnViewDaoImpl implements ReturnViewDao {

    @Override
    public List<ReturnDetailsView> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM returnDetails");

        List<ReturnDetailsView> emList = new ArrayList<>();

        while (resultSet.next()){
            emList.add(new ReturnDetailsView(
                    resultSet.getString("returnId"),
                    resultSet.getString("vehicleId")
            ));

        }
        return emList;
    }

    @Override
    public boolean save(ReturnDetailsView customer) throws SQLException {
        return false;
    }

    @Override
    public boolean update(ReturnDetailsView customer) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String customerld) throws SQLException {
        return false;
    }

    @Override
    public ReturnDetailsView searchById(String customerld) throws SQLException {
        return null;
    }

    @Override
    public ReturnDetailsView searchByName(String name) throws SQLException {
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException {
        return null;
    }

    @Override
    public List<ReturnDetailsView> getAllDesc() throws SQLException {
        return null;
    }

    @Override
    public List<String> getNameDetails() throws SQLException {
        return null;
    }
}
