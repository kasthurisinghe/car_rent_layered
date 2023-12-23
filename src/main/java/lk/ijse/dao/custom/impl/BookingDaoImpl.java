package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.BookingDao;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.BookingDto;
import lk.ijse.entity.BookingEntity;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;

public class BookingDaoImpl implements BookingDao {
    @Override
    public boolean saveBooking(BookingEntity bookingEntity) throws SQLException {
        String sql="INSERT INTO booking_details VALUES (?,?,?,?,?,?,?,?)";

        Connection connection=DbConnection.getInstance().getConnection();
        PreparedStatement pstm=connection.prepareStatement(sql);

        pstm.setString(1,bookingEntity.getCarId());
        pstm.setInt(2,bookingEntity.getRate());
        pstm.setString(3,bookingEntity.getCustId());
        pstm.setDate(4, Date.valueOf(bookingEntity.getStartDat()));
        pstm.setDate(5, Date.valueOf(bookingEntity.getEndDat()));
        pstm.setString(6,bookingEntity.getBookingId());
        pstm.setInt(7,bookingEntity.getTotal());
        pstm.setBoolean(8,bookingEntity.getIsReturned());

        return pstm.executeUpdate()>0;
    }

    @Override
    public BookingEntity searchBooking(String bookingId) throws SQLException {
        String sql="SELECT * FROM booking_details WHERE booking_id=?";

        Connection connection=DbConnection.getInstance().getConnection();

        PreparedStatement pstm=connection.prepareStatement(sql);

        pstm.setString(1,bookingId);

        ResultSet resultSet= pstm.executeQuery();

        if (resultSet.next()){
            String carId=resultSet.getString(1);
            Integer rate=resultSet.getInt(2);
            String custId=resultSet.getString(3);

            Date sDate=resultSet.getDate(4);
            LocalDate startDate=LocalDate.of(sDate.getYear(), sDate.getMonth(), sDate.getDay());

            Date eDate=resultSet.getDate(5);
            LocalDate endDate=LocalDate.of(eDate.getYear(), eDate.getMonth(), eDate.getDay());
            String bookId= resultSet.getString(6);

            Integer total= resultSet.getInt(7);
            Boolean isReturned=resultSet.getBoolean(8);

            return new BookingEntity(carId,rate,custId,startDate,endDate,bookId,total,isReturned);
        }
        return null;
    }

    @Override
    public Boolean updateBooking(BookingEntity bookingEntity) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="UPDATE booking_details SET car_id=?, rate =?, customer_id=?, start_date=?, end_date=?,total_price=?, is_returned=? WHERE booking_id=?";
         PreparedStatement preparedStatement=connection.prepareStatement(sql);

         preparedStatement.setString(1,bookingEntity.getCarId());
        preparedStatement.setInt(2,bookingEntity.getRate());
        preparedStatement.setString(3,bookingEntity.getCustId());
        preparedStatement.setDate(4, Date.valueOf(bookingEntity.getStartDat()));
        preparedStatement.setDate(5, Date.valueOf(bookingEntity.getEndDat()));
        preparedStatement.setInt(6,bookingEntity.getTotal());
        preparedStatement.setBoolean(7,bookingEntity.getIsReturned());
        preparedStatement.setString(8, bookingEntity.getBookingId());

        if (preparedStatement.executeUpdate()>0){
            return true;
        }
        return false;

    }

    @Override
    public Boolean deleteBooking(String bId) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="DELETE FROM booking_details WHERE booking_id=?";

        PreparedStatement preparedStatement=connection.prepareStatement(sql);

        preparedStatement.setString(1,bId);

        if (preparedStatement.executeUpdate()>0){
            return true;
        }return false;
    }
}
