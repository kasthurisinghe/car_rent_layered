package lk.ijse.dao.custom;
import lk.ijse.entity.BookingEntity;
import lk.ijse.entity.tm.BookingEntityTm;

import java.sql.SQLException;
import java.util.List;

public interface BookingDao {
    boolean saveBooking(BookingEntity bookingEntity) throws SQLException;

    BookingEntity searchBooking(String bookingId) throws SQLException;

    Boolean updateBooking(BookingEntity bookingEntity) throws SQLException;

    Boolean deleteBooking(String bId) throws SQLException;
    List<BookingEntityTm> loadTable() throws SQLException;
}
