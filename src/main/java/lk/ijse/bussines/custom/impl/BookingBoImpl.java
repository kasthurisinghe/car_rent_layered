package lk.ijse.bussines.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.bussines.custom.BookingBo;
import lk.ijse.dao.DaoFactory;
import lk.ijse.dao.DaoType;
import lk.ijse.dao.custom.BookingDao;
import lk.ijse.dto.BookingDto;
import lk.ijse.dto.tm.BookingDtoTm;
import lk.ijse.entity.BookingEntity;
import lk.ijse.entity.tm.BookingEntityTm;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class BookingBoImpl implements BookingBo {
    BookingDao bookingDaoImpl= DaoFactory.getDao(DaoType.BOOKINGDAO);
    Boolean isOverDue;
    @Override
    public boolean saveBooking(BookingDto bookingDto) throws SQLException {

//        long daysDiff = ChronoUnit.DAYS.between(startDate, endDate); to get the difference between LocalDays

        BookingEntity bookingEntity=new BookingEntity(
                bookingDto.getCarId(),
                bookingDto.getRate(),
                bookingDto.getCustId(),
                bookingDto.getStartDat(),
                bookingDto.getEndDat(),
                bookingDto.getBookingId(),
                getTotal(bookingDto),
                bookingDto.getIsReturned()
        );
        Boolean isEnabled=bookingDaoImpl.checkBookings(bookingEntity);

        if (isEnabled) {
            return bookingDaoImpl.saveBooking(bookingEntity);
        }return false;
    }

    private Integer getTotal(BookingDto bookingDto) {

        try {
            Integer duration= (int) ChronoUnit.DAYS.between(bookingDto.getEndDat(),bookingDto.getStartDat());

            Integer total= bookingDto.getRate()*duration;
            isOverDue=true;
            return total;
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"check the start date and end date").show();
        }
        return null;
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

    @Override
    public Boolean updateBooking(BookingDto bookingDto) throws SQLException {
        BookingEntity bookingEntity=new BookingEntity(
                bookingDto.getCarId(),
                bookingDto.getRate(),
                bookingDto.getCustId(),
                bookingDto.getStartDat(),
                bookingDto.getEndDat(),
                bookingDto.getBookingId(),
                getTotal(bookingDto),
                bookingDto.getIsReturned()
        );
        return bookingDaoImpl.updateBooking(bookingEntity);
    }

    @Override
    public Boolean deleteBooking(String bId) throws SQLException {
        return bookingDaoImpl.deleteBooking(bId);
    }

    @Override
    public List<BookingDtoTm> getTableData() throws SQLException {
        List<BookingEntityTm> bookingslistEntityTm=  bookingDaoImpl.loadTable();

        List<BookingDtoTm>bookingsListDto=new ArrayList<>();

        if (bookingslistEntityTm!=null){
            for (BookingEntityTm bookingEntity:bookingslistEntityTm){

                String bookId=bookingEntity.getBookingId();
                String carId=bookingEntity.getCarId();
                Integer rate=bookingEntity.getRate();
                String custId=bookingEntity.getCustId();
                LocalDate endDate=bookingEntity.getEndDat();
                LocalDate startDate=bookingEntity.getStartDat();
                Integer total=(int) (bookingEntity.getRate() * (ChronoUnit.DAYS.between(bookingEntity.getStartDat(), bookingEntity.getEndDat())));

                BookingDtoTm bookingDto=new BookingDtoTm(bookId,carId,custId,endDate,startDate,rate,total);
                bookingsListDto.add(bookingDto);
            }

            return bookingsListDto;
        }
        return null;
    }
}
