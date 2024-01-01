package lk.ijse.bussines.custom;

import lk.ijse.dto.tm.AllOverDueDtoTm;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface AllOverDueBo {
    List<AllOverDueDtoTm> getAllOverDueTable(LocalDate value) throws SQLException;
}
