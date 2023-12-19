package lk.ijse.dao;

import lk.ijse.bussines.BoType;
import lk.ijse.dao.custom.Userdao;
import lk.ijse.dao.custom.impl.CreateUserDaoImpl;

public class DaoFactory {
        public  static <T>T  getDao(DaoType type){
            switch (type){
                case ADMINUSER :
                    return (T) new CreateUserDaoImpl();
                default:
                    return null;
            }
        }

}
