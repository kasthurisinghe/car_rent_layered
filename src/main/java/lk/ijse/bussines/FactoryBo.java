package lk.ijse.bussines;


import lk.ijse.bussines.custom.impl.BookingBoImpl;
import lk.ijse.bussines.custom.impl.CreateUserBoImpl;
import lk.ijse.bussines.custom.impl.CustomerBoImpl;
import lk.ijse.bussines.custom.impl.LoginBoImpl;
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
            default :
                return null;
        }
    }
}
