package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.ReturnDao;
import lk.ijse.dao.SQLUtil;
import lk.ijse.entity.Return;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReturnDaoImpl implements ReturnDao {


    @Override
    public List<Return> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Returns");
        ArrayList<Return> returnArrayList = new ArrayList<>();

        while (resultSet.next()){
            returnArrayList.add(new Return(
                    resultSet.getString("returnId"),
                    resultSet.getDouble("afterRideMilage"),
                    resultSet.getDouble("lateReturnCharge"),
                    resultSet.getDouble("TotalCost"),
                    resultSet.getString("customerld"),
                    resultSet.getString("reservationId")
            ));
        }
        return  returnArrayList;
    }

    @Override
    public boolean save(Return customer) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Return customer) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String customerld) throws SQLException {
        return false;
    }

    @Override
    public Return searchById(String customerld) throws SQLException {
        return null;
    }

    @Override
    public Return searchByName(String name) throws SQLException {
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException {
        return null;
    }

    @Override
    public List<Return> getAllDesc() throws SQLException {
        return null;
    }

    @Override
    public List<String> getNameDetails() throws SQLException {
        return null;
    }


    @Override
    public  String getCurrentId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT returnId FROM Returns ORDER BY returnId DESC LIMIT 1");
        if(resultSet.next()) {
            String returnId = resultSet.getString(1);
            return returnId;
        }
        return null;
    }
}
