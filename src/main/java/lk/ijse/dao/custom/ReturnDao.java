package lk.ijse.dao.custom;

import lk.ijse.entity.ReturnEntity;

import java.sql.SQLException;
import java.time.LocalDate;

public interface ReturnDao {
    ReturnEntity findRental(String bookId) throws SQLException;

    Boolean acceptReturn(String booId, Boolean isReturned) throws SQLException;
}
