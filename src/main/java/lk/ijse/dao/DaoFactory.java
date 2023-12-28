package lk.ijse.dao;

import lk.ijse.bussines.BoType;
import lk.ijse.bussines.custom.impl.BookingBoImpl;
import lk.ijse.dao.custom.Userdao;
import lk.ijse.dao.custom.impl.*;

public class DaoFactory {
        public  static <T>T  getDao(DaoType type){
            switch (type){
                case ADMINUSER :
                    return (T) new CreateUserDaoImpl();
                case BOOKINGDAO:
                    return (T) new BookingDaoImpl();
                case CUSTOMERDAO:
                    return (T) new CustomerDaoImpl();

                case RETURNDAO:
                    return (T) new RetornDaoImpl();
                case CARDAO:
                    return (T) new CarDaoImpl();
                default:
                    return null;
            }
        }

}
