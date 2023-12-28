package lk.ijse.bussines.custom;

import lk.ijse.dto.BookingDto;
import lk.ijse.dto.tm.BookingDtoTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface BookingBo {
    boolean saveBooking(BookingDto bookingDto) throws SQLException;

    BookingDto searchBooking(String bookingId) throws SQLException;

    Boolean updateBooking(BookingDto bookingDto) throws SQLException;

    Boolean deleteBooking(String bId) throws SQLException;

    List<BookingDtoTm> getTableData() throws SQLException;
}
