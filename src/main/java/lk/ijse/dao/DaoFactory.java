package lk.ijse.dao;

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
