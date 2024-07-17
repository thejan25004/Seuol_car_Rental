package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.QueryDao;
import lk.ijse.entity.ReturnDetailsView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryDaoImpl implements QueryDao {
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
}
