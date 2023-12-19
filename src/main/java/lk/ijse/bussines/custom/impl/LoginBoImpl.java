package lk.ijse.bussines.custom.impl;

import lk.ijse.bussines.BoType;
import lk.ijse.bussines.FactoryBo;
import lk.ijse.bussines.custom.UserLoginBo;
import lk.ijse.dao.DaoFactory;
import lk.ijse.dao.DaoType;
import lk.ijse.dao.custom.Userdao;
import lk.ijse.dao.custom.impl.UserDaoImpl;

import java.sql.SQLException;

public class LoginBoImpl implements UserLoginBo {
    Userdao userdaoImpl= DaoFactory.getDao(DaoType.LOGINDAO);
    @Override
    public String findAdmin(String userID) throws SQLException {
        return UserDaoImpl.findAdminUser(userID);
    }
}
