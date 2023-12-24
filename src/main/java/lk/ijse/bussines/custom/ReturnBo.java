package lk.ijse.bussines.custom;

import lk.ijse.dto.ReturnDto;

import java.sql.SQLException;
import java.time.LocalDate;

public interface ReturnBo {
    ReturnDto findRental(String bookId , LocalDate duedate) throws SQLException;

    Boolean acceptReturn(String booId,Boolean isReturned) throws SQLException;
}
