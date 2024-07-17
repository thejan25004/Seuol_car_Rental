package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.ReservationDetailsDao;
import lk.ijse.dao.SQLUtil;
import lk.ijse.entity.ReservationDetails;

import java.sql.SQLException;
import java.util.List;

public class ReservationDetailsDaoImpl implements ReservationDetailsDao {
    @Override
    public List<ReservationDetails> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public  boolean save(ReservationDetails reservationDetails) throws SQLException {
        return SQLUtil.execute("INSERT INTO ReservationDetails VALUES (?,?)" , reservationDetails.getReservationId(),reservationDetails.getVehicleId());
    }

    @Override
    public boolean update(ReservationDetails customer) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String customerld) throws SQLException {
        return false;
    }

    @Override
    public ReservationDetails searchById(String customerld) throws SQLException {
        return null;
    }

    @Override
    public ReservationDetails searchByName(String name) throws SQLException {
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException {
        return null;
    }

    @Override
    public List<ReservationDetails> getAllDesc() throws SQLException {
        return null;
    }

    @Override
    public List<String> getNameDetails() throws SQLException {
        return null;
    }


    @Override
    public  boolean SaveReservation(ReservationDetails reservationDetails) throws SQLException {
        return SQLUtil.execute("INSERT INTO Reservations VALUES(?, ?, ?, ?)" , reservationDetails.getReservationId(),reservationDetails.getReservationDate(),reservationDetails.getReturnDate(),reservationDetails.getCustomerld());
    }


}
