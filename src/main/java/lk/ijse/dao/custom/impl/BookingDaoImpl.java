package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.BookingDao;
import lk.ijse.db.DbConnection;
import lk.ijse.entity.BookingEntity;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
        pstm.setBoolean(8,bookingEntity.getIsRented());

        return pstm.executeUpdate()>0;
    }
}
