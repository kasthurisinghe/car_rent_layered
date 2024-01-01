package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.AllRentalsDao;
import lk.ijse.db.DbConnection;
import lk.ijse.entity.tm.AllRentalsEntityTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AllRentalDaoImpl implements AllRentalsDao {
    @Override
    public List<AllRentalsEntityTm> loadAllRentalTable() throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();

        String sql="SELECT*FROM booking_details";
        PreparedStatement statement=connection.prepareStatement(sql);

        List<AllRentalsEntityTm>rentalsEntityTms=new ArrayList<>();
        ResultSet resultSet= statement.executeQuery();

        while (resultSet.next()){
            String custId=resultSet.getString(3);
            String vehiId=resultSet.getString(1);
            LocalDate startDate= resultSet.getDate(4).toLocalDate();
            LocalDate endDate= resultSet.getDate(5).toLocalDate();
            Integer rate=resultSet.getInt(2);
            Integer amount=resultSet.getInt(7);
            Boolean returned=resultSet.getBoolean(8);

            AllRentalsEntityTm allRentalsEntityTm=new AllRentalsEntityTm(custId,vehiId,startDate,endDate,rate,amount,returned);

            rentalsEntityTms.add(allRentalsEntityTm);
        }
        return rentalsEntityTms;
    }
}
