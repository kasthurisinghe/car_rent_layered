package lk.ijse.bussines.custom;

import java.sql.SQLException;

public interface UserLoginBo {

    String findAdmin(String userID) throws SQLException;
}
