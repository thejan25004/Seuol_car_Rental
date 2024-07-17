package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBo;
import lk.ijse.dto.ReservationDTO;
import lk.ijse.entity.RedDetailsView;

import java.sql.SQLException;
import java.util.List;

public interface ReservationViewBo extends SuperBo {
    public List<RedDetailsView> getAll() throws SQLException ;
    public List<ReservationDTO> getAllReservations() throws SQLException, ClassNotFoundException ;
}
