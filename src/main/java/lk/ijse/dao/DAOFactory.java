package lk.ijse.dao;

import lk.ijse.dao.custom.impl.*;

public class DAOFactory {

    private static  DAOFactory daoFactory ;

    private DAOFactory(){}

    public static  DAOFactory getDaoFactory(){
        return  (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER , EMPLOYEE , RESERVATION , RESERVATIONDETAILS , RETURN , RETURNDETAILS , SUPPLIER , USER , VEHICLE , RESERVATIONVIEW , RETURNVIEW

    }

    public static SuperDao getDAO(DAOTypes types){
        switch (types){
            case CUSTOMER :
                 return new CustomerDaoImpl();
            case EMPLOYEE:
                return new EmployeeDaoImpl();
            case RESERVATION:
                return new ReservationDaoImpl();
            case RESERVATIONDETAILS:
                return new ReservationDetailsDaoImpl();
            case RETURN:
                return new ReturnDaoImpl();
            case RETURNDETAILS:
                return new ReturnDetailsDaoImpl();
            case SUPPLIER:
                return new SupplierDaoImpl();
            case USER:
                return new UserDaoImpl();
            case VEHICLE:
                return new VehicleDaoImpl();
            case RESERVATIONVIEW:
                return new ReservationViewDaoImpl();
            case RETURNVIEW:
                return new ReturnViewDaoImpl();
            default:
                return null ;
        }
    }
}
