package lk.ijse.dao.custom;

import lk.ijse.entity.BookingEntity;

import java.sql.SQLException;

public interface BookingDao {
    boolean saveBooking(BookingEntity bookingEntity) throws SQLException;
}
