package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.ReservationDao;
import lk.ijse.dao.SQLUtil;
import lk.ijse.entity.Reservation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDaoImpl  implements ReservationDao {



    @Override
    public  Reservation searchById(String reservationId) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Reservations WHERE reservationId  = ?" , reservationId + "");
        if (resultSet.next()){
            Reservation reservation = new Reservation(
                    reservationId + "",
                    resultSet.getDate("reservationDate"),
                    resultSet.getDate("returnDate"),
                    resultSet.getString("customerld")
            );
            return reservation;
        }
        return null;
    }

    @Override
    public Reservation searchByName(String name) throws SQLException {
        return null;
    }

    @Override
    public List<Reservation> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Reservations");
        ArrayList<Reservation> reservationList = new ArrayList<>();

        while (resultSet.next()){
            reservationList.add(new Reservation(
                    resultSet.getString("reservationId"),
                    resultSet.getDate("reservationDate"),
                    resultSet.getDate("returnDate"),
                    resultSet.getString("customerld")
            ));
        }
        return  reservationList;

    }

    @Override
    public boolean save(Reservation customer) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Reservation customer) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String customerld) throws SQLException {
        return false;
    }

    @Override
    public  List<String> getIds() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT reservationId FROM Reservations");
        ArrayList<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString("reservationId"));
        }
        return idList;
    }

    @Override
    public List<Reservation> getAllDesc() throws SQLException {
        return null;
    }

    @Override
    public List<String> getNameDetails() throws SQLException {
        return null;
    }



    @Override
    public  String getCurrentId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT reservationId FROM Reservations ORDER BY reservationId DESC LIMIT 1");
        if(resultSet.next()) {
            String reservationId = resultSet.getString(1);
            return reservationId;
        }
        return null;
    }

}
