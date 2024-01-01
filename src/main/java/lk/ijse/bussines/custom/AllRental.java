package lk.ijse.bussines.custom;

import lk.ijse.dto.tm.AllRentalsDtoTm;

import java.sql.SQLException;
import java.util.List;

public interface AllRental {
    List<AllRentalsDtoTm> loadAllrentalDetails() throws SQLException;
}
