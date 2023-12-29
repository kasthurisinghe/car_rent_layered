package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.CustomerDao;

import lk.ijse.db.DbConnection;
import lk.ijse.entity.CustomerEntity;
import lk.ijse.entity.tm.CustomerEntityTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public Boolean saveCustomer(CustomerEntity customerEntity) throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();

        String sql="INSERT INTO customer VALUES (?,?,?,?,?,?)";

        PreparedStatement preparedStatement= connection.prepareStatement(sql);

        preparedStatement.setString(1,customerEntity.getCusId());
        preparedStatement.setString(2,customerEntity.getCusName());
        preparedStatement.setString(3,customerEntity.getCusAdd());
        preparedStatement.setString(4,customerEntity.getCusNic());
        preparedStatement.setString(5,customerEntity.getCusmobile());
        preparedStatement.setString(6,customerEntity.getGender());


        return preparedStatement.executeUpdate()>0;

    }

    @Override
    public Boolean updateCustomer(CustomerEntity customerEntity) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();

        String sql="UPDATE Customer SET Customer_name =?, address=?, NIC=?, Phone_Number=?,gender=? WHERE Customer_ID=?";

        PreparedStatement preparedStatement=connection.prepareStatement(sql);

        preparedStatement.setString(1, customerEntity.getCusName());
        preparedStatement.setString(2, customerEntity.getCusAdd());
        preparedStatement.setString(3, customerEntity.getCusNic());
        preparedStatement.setString(4, customerEntity.getCusmobile());
        preparedStatement.setString(5, customerEntity.getGender());
        preparedStatement.setString(6, customerEntity.getCusId());

        if (preparedStatement.executeUpdate()>0){
            return true;
        }return false;

    }

    @Override
    public boolean deleteCustomer(String cusId) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();

        String sql="DELETE FROM customer WHERE Customer_ID=?";

        PreparedStatement preparedStatement= connection.prepareStatement(sql);

        preparedStatement.setString(1,cusId);
        if (preparedStatement.executeUpdate()>0){
            return true;
        }return  false;
    }

    @Override
    public CustomerEntity findCustomer(String cusId) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();

        String sql="SELECT*FROM customer WHERE Customer_ID=?";


        PreparedStatement preparedStatement= connection.prepareStatement(sql);

        preparedStatement.setString(1,cusId);
        ResultSet resultSet=preparedStatement.executeQuery();

        if (resultSet.next()){

            return new CustomerEntity(
                resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );

        }return  null;
    }

    @Override
    public List<CustomerEntityTm> loadTable() throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();

        String sql="SELECT*FROM customer";
        PreparedStatement statement= connection.prepareStatement(sql);
        List <CustomerEntityTm>customerEntityTms=new ArrayList<>();
        ResultSet resultSet= statement.executeQuery();
        while (resultSet.next()){
            String custId= resultSet.getString(1);
            String custName= resultSet.getString(2);
            String custAdd= resultSet.getString(3);
            String custNic= resultSet.getString(4);
            String custMobile= resultSet.getString(5);
            String custGender= resultSet.getString(6);
            CustomerEntityTm customerEntityTm=new CustomerEntityTm(custId,custName,custAdd,custNic,custMobile,custGender);
            customerEntityTms.add(customerEntityTm);
        }
        return customerEntityTms;
    }
}
