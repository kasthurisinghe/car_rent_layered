package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.AllOverDueDao;
import lk.ijse.dao.custom.AllRentalsDao;
import lk.ijse.db.DbConnection;
import lk.ijse.entity.tm.AllOverDueEntityTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AllOverDueDaoImpl implements AllOverDueDao {
    @Override
    public List<AllOverDueEntityTm> getAllOverDues() throws SQLException {
        List<AllOverDueEntityTm>allOverDueEntityTms=new ArrayList<>();
        Connection connection= DbConnection.getInstance().getConnection();
        String sqlOver="SELECT*FROM booking_details";

        PreparedStatement  statement= connection.prepareStatement(sqlOver);

        ResultSet resultSet= statement.executeQuery();
        while (resultSet.next()){
            AllOverDueEntityTm allOverDueEntityTm=new AllOverDueEntityTm(
                    resultSet.getString(3),
                    resultSet.getString(1),
                    resultSet.getDate(5).toLocalDate(),
                    resultSet.getInt(2),
                    resultSet.getInt(7),
                    resultSet.getBoolean(8)
                    );
            allOverDueEntityTms.add(allOverDueEntityTm);
        }
        return allOverDueEntityTms;
    }
}
