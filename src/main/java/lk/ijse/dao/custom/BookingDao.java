package lk.ijse.dao.custom;

import lk.ijse.dto.BookingDto;
import lk.ijse.entity.BookingEntity;

import java.sql.SQLException;

public interface BookingDao {
    boolean saveBooking(BookingEntity bookingEntity) throws SQLException;

    BookingEntity searchBooking(String bookingId) throws SQLException;
}
