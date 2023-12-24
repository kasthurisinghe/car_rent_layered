package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.ReturnDao;
import lk.ijse.db.DbConnection;
import lk.ijse.entity.ReturnEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class RetornDaoImpl implements ReturnDao {
    @Override
    public ReturnEntity findRental(String bookId) throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();
//            connection.setAutoCommit(false);
//        see transactions with mvc 110 ;
//            UPDATE booking_details SET rate= 20  WHERE booking_id = '212';

            String sql ="SELECT*FROM booking_details WHERE booking_id=?";
            PreparedStatement preparedStatement= connection.prepareStatement(sql);

            preparedStatement.setString(1,bookId);

            ResultSet resultSet=preparedStatement.executeQuery();
            if (resultSet.next()){
                String carId=resultSet.getString(1);
                String custId=resultSet.getString(3);
                LocalDate endD= resultSet.getDate(5).toLocalDate();
                String booId=resultSet.getString(6);
                Integer toPrice=resultSet.getInt(7);
                Boolean returned=resultSet.getBoolean(8);

                if (!returned) {
                    String sqlCust="SELECT*FROM customer WHERE Customer_ID=?";
                    PreparedStatement pstmCust=connection.prepareStatement(sqlCust);
                    pstmCust.setString(1,custId);
                    ResultSet resultSetCus= pstmCust.executeQuery();

                    if (resultSetCus.next()){
                        String custName= resultSet.getString(2);

                        String sqlCar="SELECT*FROM car_details where ID_no=?";
                        PreparedStatement pstmCar=connection.prepareStatement(sqlCar);
                        pstmCar.setString(1,carId);
                        ResultSet resultSetCar=pstmCar.executeQuery();

                        if (resultSetCar.next()){
                            String vehiRegNo= resultSetCar.getString(1);

                            return new ReturnEntity(
                                    booId,endD,custId,custName,vehiRegNo,toPrice
                            );
                        }else {
                            System.out.println("car case");
                        }
                    }else {
                        System.out.println("customr case");
                    }
                }else {
                    return null;
                }
            } return null;
    }

    @Override
    public Boolean acceptReturn(String booId, Boolean isReturned) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="UPDATE booking_details SET is_returned = ? WHERE booking_id=?";
        PreparedStatement preparedStatement =connection.prepareStatement(sql);

        preparedStatement.setBoolean(1, (isReturned));
        preparedStatement.setString(2,booId);
        if (preparedStatement.executeUpdate()>0){
            return true;
        }return false;
    }
}
