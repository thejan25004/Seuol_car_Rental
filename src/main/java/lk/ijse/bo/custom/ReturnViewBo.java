package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBo;
import lk.ijse.dto.ReturnDTO;
import lk.ijse.entity.ReturnDetailsView;

import java.sql.SQLException;
import java.util.List;

public interface ReturnViewBo  extends SuperBo {
    public List<ReturnDetailsView> getAllReturnView() throws SQLException ;

    public List<ReturnDTO> getAllReturns() throws SQLException, ClassNotFoundException ;
}
