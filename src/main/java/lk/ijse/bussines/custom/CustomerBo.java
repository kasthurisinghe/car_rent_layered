package lk.ijse.bussines.custom;

import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.tm.CustomerDtoTm;
import lk.ijse.dto.tm.CustomerRentalDtoTm;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface CustomerBo {
    Boolean saveCustomer(CustomerDto customerDto) throws SQLException;

    Boolean updateCustomer(CustomerDto customerDto) throws SQLException;

    boolean deleteCustomer(String cusId) throws SQLException;

    CustomerDto findCustomer(String cusId) throws SQLException;

    List<CustomerDtoTm> loadTableData() throws SQLException;

    List<CustomerRentalDtoTm> loadAllCustomerRentals(LocalDate today, String custId) throws SQLException;
}
