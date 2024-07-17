package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.ReservationDetails;

import java.sql.SQLException;

public interface ReservationDetailsDao  extends CrudDAO<ReservationDetails>   {
   // public  boolean save(ReservationDetails reservationDetails) throws SQLException ;

    public  boolean SaveReservation(ReservationDetails reservationDetails) throws SQLException ;


}
