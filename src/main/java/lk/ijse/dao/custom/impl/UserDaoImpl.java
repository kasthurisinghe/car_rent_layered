package lk.ijse.dao.custom.impl;

import lk.ijse.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl {
    public static String findAdminUser(String userID) throws SQLException {
        String sql="SELECT * FROM admin_user WHERE id =?";

        Connection connection= DbConnection.getInstance().getConnection();
        PreparedStatement pstm=connection.prepareStatement(sql);


        pstm.setString(1,userID);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            return resultSet.getString(5);
        }return null;
    }
}
