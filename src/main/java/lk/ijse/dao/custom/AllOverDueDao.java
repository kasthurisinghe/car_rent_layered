package lk.ijse.dao.custom;

import lk.ijse.entity.tm.AllOverDueEntityTm;

import java.sql.SQLException;
import java.util.List;

public interface AllOverDueDao {
    List<AllOverDueEntityTm> getAllOverDues() throws SQLException;
}
