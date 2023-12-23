package lk.ijse.bussines.custom;

import lk.ijse.dto.CustomerDto;

import java.sql.SQLException;

public interface CustomerBo {
    Boolean saveCustomer(CustomerDto customerDto) throws SQLException;

    Boolean updateCustomer(CustomerDto customerDto) throws SQLException;

    boolean deleteCustomer(String cusId) throws SQLException;

    CustomerDto findCustomer(String cusId) throws SQLException;
}
