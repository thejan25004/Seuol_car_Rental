package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.ReservationViewBo;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.ReservationDao;
import lk.ijse.dao.custom.ReservationViewDao;
import lk.ijse.dto.ReservationDTO;
import lk.ijse.entity.RedDetailsView;
import lk.ijse.entity.Reservation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationViewBoImpl implements ReservationViewBo {


   ReservationViewDao reservationViewDao = (ReservationViewDao) DAOFactory.getDAO(DAOFactory.DAOTypes.RESERVATIONVIEW);

    ReservationDao reservationDao = (ReservationDao) DAOFactory.getDAO(DAOFactory.DAOTypes.RESERVATION);
    public List<RedDetailsView> getAll() throws SQLException {
        return  reservationViewDao.getAll();
    }

    public List<ReservationDTO> getAllReservations() throws SQLException, ClassNotFoundException {
        ArrayList <ReservationDTO> reservations = new ArrayList<>();
        List <Reservation> all = reservationDao.getAll();
          for (Reservation r : all){
              reservations.add(new ReservationDTO(r.getReservationId(),r.getReservationDate(),r.getReturnDate(),r.getCustomerld()));
          }
          return reservations;
    }

}
