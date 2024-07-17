package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.ReturnViewBo;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.ReturnDao;
import lk.ijse.dao.custom.ReturnViewDao;
import lk.ijse.dto.ReturnDTO;
import lk.ijse.entity.Return;
import lk.ijse.entity.ReturnDetailsView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReturnViewBoImpl  implements ReturnViewBo {

    ReturnDao returnDao = (ReturnDao) DAOFactory.getDAO(DAOFactory.DAOTypes.RETURN);
    ReturnViewDao returnViewDao = (ReturnViewDao) DAOFactory.getDAO(DAOFactory.DAOTypes.RETURNVIEW);

    public List<ReturnDetailsView> getAllReturnView() throws SQLException {
        return  returnViewDao.getAll();
    }

    public List<ReturnDTO> getAllReturns() throws SQLException, ClassNotFoundException {
        ArrayList <ReturnDTO> returns = new ArrayList<>();
        List <Return> all = returnDao.getAll();

        for (Return r : all){
           returns.add(new ReturnDTO(r.getReturnId(),r.getAfterRideMilage(),r.getLateReturnCharge() ,r.getTotalCost() ,r.getCustomerld(),r.getReservationId()));
        }
        return returns;
    }
}
