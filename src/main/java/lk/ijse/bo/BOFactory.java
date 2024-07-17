package lk.ijse.bo;

import lk.ijse.bo.custom.ReservationViewBo;
import lk.ijse.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER , CHECK , SUPPLIER, DASHBOARD , EMAIL ,EMPLOYEE ,LOGIN , USERSETTING ,VEHICLE ,REGISTERATION ,RESERVATION ,RESERVATIONVIEW , RETURN ,RETURNVIEW


    }

    public static SuperBo getBo (BOTypes types){
        switch (types){
            case CUSTOMER :
                return new CustomerBoImpl();
            case CHECK:
                return new CheckIdAndPasswordBOImpl();
            case SUPPLIER:
                return  new SupplierBoImpl();
            case DASHBOARD:
                return new DashboardBoImpl();
            case EMAIL:
                return new EmailBoImpl();
            case EMPLOYEE:
                return new EmployeeBoImpl();
            case LOGIN:
                return new LogInBoImpl();
            case USERSETTING:
                return new UserSettingBoImpl();
            case VEHICLE:
                return new VehicleBoImpl();
            case REGISTERATION:
                return new RegisterationBoImpl();
            case RESERVATION:
                return new ReservationBoImpl();
            case RESERVATIONVIEW:
                return new ReservationViewBoImpl() ;
            case RETURN:
                return new ReturnBoImpl();
            case RETURNVIEW:
                return new ReturnViewBoImpl();
            default:
                return null;
        }
    }



}
