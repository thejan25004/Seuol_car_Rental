package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Reservation;

import java.sql.SQLException;

public interface ReservationDao extends CrudDAO<Reservation> {



//    public  Reservation searchById(String reservationId) throws SQLException ;
//
//
//    public ArrayList<Reservation> getAll() throws SQLException ;
//
//
//    public  List<String> getIds() throws SQLException ;


    public  String getCurrentId() throws SQLException ;




}
