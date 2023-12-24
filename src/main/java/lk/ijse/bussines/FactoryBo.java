package lk.ijse.bussines;


import lk.ijse.bussines.custom.impl.*;
import lk.ijse.dao.custom.impl.CustomerDaoImpl;

public class FactoryBo {

    public static <T> T  getBo(BoType boType) {
        switch (boType){
            case ADMINUSER:
                return (T)new CreateUserBoImpl();

            case LOGINBO:
                return (T) new LoginBoImpl();

            case BOOKINGBO:
                return (T) new BookingBoImpl();

            case CUSTOMERBO:
                return (T) new CustomerBoImpl();

            case RETURNBO:
                return (T) new ReturnBoImpl();

            default :
                return null;
        }
    }
}
