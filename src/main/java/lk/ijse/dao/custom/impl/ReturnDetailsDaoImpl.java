package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.ReturnDetailsDao;
import lk.ijse.dao.SQLUtil;
import lk.ijse.entity.ReturnDetails;

import java.sql.SQLException;
import java.util.List;

public class ReturnDetailsDaoImpl implements ReturnDetailsDao {
    @Override
    public List<ReturnDetails> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public  boolean save(ReturnDetails returnDetails) throws SQLException {
        return SQLUtil.execute("INSERT INTO returnDetails VALUES (?,?)" , returnDetails.getReturnId(),returnDetails.getVehicleId());

    }

    @Override
    public boolean update(ReturnDetails customer) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String customerld) throws SQLException {
        return false;
    }

    @Override
    public ReturnDetails searchById(String customerld) throws SQLException {
        return null;
    }

    @Override
    public ReturnDetails searchByName(String name) throws SQLException {
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException {
        return null;
    }

    @Override
    public List<ReturnDetails> getAllDesc() throws SQLException {
        return null;
    }

    @Override
    public List<String> getNameDetails() throws SQLException {
        return null;
    }

    @Override
    public boolean saveReturn(ReturnDetails returnDetails) throws SQLException {
        return SQLUtil.execute("INSERT INTO Returns VALUES(?, ?, ?, ?, ?, ?)" , returnDetails.getReturnId(),returnDetails.getAfterRideMilage(),returnDetails.getReturnCharge(),returnDetails.getTotalCost(),returnDetails.getCustomerId(),returnDetails.getReservationId());

    }

    @Override
    public boolean updateMillage(ReturnDetails returnDetails) throws SQLException {
               return SQLUtil.execute("UPDATE Vehicles SET currentMilage = ? WHERE vehicleId = ?", returnDetails.getAfterRideMilage(), returnDetails.getVehicleId());
    }
}
