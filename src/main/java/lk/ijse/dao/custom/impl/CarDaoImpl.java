package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.CarDao;
import lk.ijse.db.DbConnection;
import lk.ijse.entity.CarEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarDaoImpl implements CarDao {
    @Override
    public Boolean deleteCar(String id) throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();

        String sql="SELECT*FROM car_details WHERE ID_no =?";

        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,id);

        if (preparedStatement.executeUpdate()>0){
            return true;
        }
        return false;

    }

    @Override
    public CarEntity checkCar(String id) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();

        String sql="SELECT*FROM car_details WHERE ID_no=?";

        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,id);

        ResultSet resultSet=preparedStatement.executeQuery();
        if(resultSet.next()){
            return new CarEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
        }return null;
    }

    @Override
    public Boolean saveCar(CarEntity carEntity) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();

        String sql="INSERT INTO car_details VALUES (?,?,?,?,?,?)";

        PreparedStatement preparedStatement=connection.prepareStatement(sql);

        preparedStatement.setString(1, carEntity.getRegNo());
        preparedStatement.setString(2, carEntity.getIdNo());
        preparedStatement.setString(3, carEntity.getModle());
        preparedStatement.setString(4, carEntity.getBarnd());
        preparedStatement.setString(5, carEntity.getColour());
        preparedStatement.setString(6, carEntity.getType());

        return preparedStatement.executeUpdate()>0;

    }

    @Override
    public Boolean carUpdate(CarEntity carEntity) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();

        String sql="UPDATE car_details SET registration_no=?, modle=?, brand=?, colour=?,type=? WHERE ID_no=?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);

        preparedStatement.setString(1, carEntity.getRegNo());

        preparedStatement.setString(2, carEntity.getModle());
        preparedStatement.setString(3, carEntity.getBarnd());
        preparedStatement.setString(4, carEntity.getColour());
        preparedStatement.setString(5, carEntity.getType());
        preparedStatement.setString(6, carEntity.getIdNo());

        return preparedStatement.executeUpdate()>0;

    }
}
