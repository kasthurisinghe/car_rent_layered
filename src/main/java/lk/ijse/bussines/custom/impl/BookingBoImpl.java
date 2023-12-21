package lk.ijse.bussines.custom.impl;

import lk.ijse.bussines.custom.BookingBo;
import lk.ijse.dao.DaoFactory;
import lk.ijse.dao.DaoType;
import lk.ijse.dao.custom.BookingDao;
import lk.ijse.dto.BookingDto;
import lk.ijse.entity.BookingEntity;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BookingBoImpl implements BookingBo {
    BookingDao bookingDaoImpl= DaoFactory.getDao(DaoType.BOOKINGDAO);
    @Override
    public boolean saveBooking(BookingDto bookingDto) throws SQLException {

//        long daysDiff = ChronoUnit.DAYS.between(startDate, endDate); to get the difference between LocalDays

        boolean isReturned=false;
        Integer rate=bookingDto.getRate();
        Integer duration= (int) ChronoUnit.DAYS.between(bookingDto.getStartDat(),bookingDto.getEndDat());
        Integer total= bookingDto.getRate()*duration;

        BookingEntity bookingEntity=new BookingEntity(
                bookingDto.getCarId(),
                rate,
                bookingDto.getCustId(),
                bookingDto.getStartDat(),
                bookingDto.getEndDat(),
                bookingDto.getBookingId(),
                total,
                isReturned
        );

        return bookingDaoImpl.saveBooking(bookingEntity);
    }

    @Override
    public BookingDto searchBooking(String bookingId) throws SQLException {
        BookingEntity bookingEntity= bookingDaoImpl.searchBooking(bookingId);

        String bookiId=bookingEntity.getBookingId();
        String carId=bookingEntity.getCarId();
        Integer rate= bookingEntity.getRate();
        String custId=bookingEntity.getCustId();
        LocalDate startDate=bookingEntity.getStartDat();
        LocalDate endDate=bookingEntity.getEndDat();
        Boolean isReturned=bookingEntity.getIsReturned();

        return new BookingDto(bookingId,carId,rate,custId,startDate,endDate,isReturned);
    }
}
