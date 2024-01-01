package lk.ijse.dao.custom;

import lk.ijse.entity.tm.AllRentalsEntityTm;

import java.sql.SQLException;
import java.util.List;

public interface AllRentalsDao {
    List<AllRentalsEntityTm> loadAllRentalTable() throws SQLException;
}
