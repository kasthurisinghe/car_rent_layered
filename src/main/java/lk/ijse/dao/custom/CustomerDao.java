package lk.ijse.dao.custom;

import lk.ijse.dto.CustomerDto;
import lk.ijse.entity.CustomerEntity;

import java.sql.SQLException;

public interface CustomerDao {
    Boolean saveCustomer(CustomerEntity customerEntity ) throws SQLException;

    Boolean updateCustomer(CustomerEntity customerEntity) throws SQLException;

    boolean deleteCustomer(String cusId) throws SQLException;

    CustomerEntity findCustomer(String cusId) throws SQLException;
}
