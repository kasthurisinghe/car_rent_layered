package lk.ijse.dao.custom;

import lk.ijse.dto.CustomerDto;
import lk.ijse.entity.CustomerEntity;
import lk.ijse.entity.tm.CustomerEntityTm;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDao {
    Boolean saveCustomer(CustomerEntity customerEntity ) throws SQLException;

    Boolean updateCustomer(CustomerEntity customerEntity) throws SQLException;

    boolean deleteCustomer(String cusId) throws SQLException;

    CustomerEntity findCustomer(String cusId) throws SQLException;

    List<CustomerEntityTm> loadTable() throws SQLException;
}
