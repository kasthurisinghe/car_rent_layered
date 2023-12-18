package lk.ijse.dao.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.dao.custom.CreateUserDao;
import lk.ijse.db.DbConnection;
import lk.ijse.entity.CreateUserEntity;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateUserDaoImpl implements CreateUserDao {

    @Override
    public CreateUserEntity findUser(String id) throws SQLException {
        String sql="SELECT *FROM admin_user WHERE id= ?";

        Connection connection=DbConnection.getInstance().getConnection();
        PreparedStatement pstm=connection.prepareStatement(sql);

        pstm.setString(1,id);
        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()){
            String userId=resultSet.getString(1);
            String userName=resultSet.getString(2);
            String userAdd=resultSet.getString(3);
            String userMobile=resultSet.getString(4);
            String userPass=resultSet.getString(5);
            String userGender=resultSet.getString(6);

            return new CreateUserEntity(userId,userName,userAdd,userMobile,userPass,userGender);
        }
        new Alert(Alert.AlertType.ERROR, "No user found!").show();
        return null;
    }

    @Override
    public boolean saveAdmin(CreateUserEntity createUserEntity) throws SQLException {
        String sql = "INSERT INTO admin_user VALUES(?,?,?,?,?,?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, createUserEntity.getId());
        pstm.setString(2, createUserEntity.getName());
        pstm.setString(3, createUserEntity.getAddr());
        pstm.setString(4, createUserEntity.getMobile());
        pstm.setString(5, createUserEntity.getPass());
        pstm.setString(6, createUserEntity.getGender());

        return pstm.executeUpdate() > 0;
    }
}