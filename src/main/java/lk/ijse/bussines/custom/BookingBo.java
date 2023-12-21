package lk.ijse.bussines.custom;

import lk.ijse.dto.BookingDto;

import java.sql.SQLException;

public interface BookingBo {
    boolean saveBooking(BookingDto bookingDto) throws SQLException;
}
