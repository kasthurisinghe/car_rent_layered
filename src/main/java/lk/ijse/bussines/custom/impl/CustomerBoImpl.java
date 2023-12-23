package lk.ijse.bussines.custom.impl;

import lk.ijse.bussines.custom.CustomerBo;
import lk.ijse.dao.DaoFactory;
import lk.ijse.dao.DaoType;
import lk.ijse.dao.custom.CustomerDao;
import lk.ijse.dto.CustomerDto;
import lk.ijse.entity.CustomerEntity;

import java.sql.SQLException;

public class CustomerBoImpl implements CustomerBo {
    CustomerDao customerDaoImpl= DaoFactory.getDao(DaoType.CUSTOMERDAO);
    @Override
    public Boolean saveCustomer(CustomerDto customerDto) throws SQLException {
        CustomerEntity customerEntity=new CustomerEntity(
                customerDto.getCusId(),
                customerDto.getCusName(),
                customerDto.getCusAdd(),
                customerDto.getCusNic(),
                customerDto.getCusmobile(),
                customerDto.getGender()
        );
        return customerDaoImpl.saveCustomer(customerEntity);
    }

    @Override
    public Boolean updateCustomer(CustomerDto customerDto) throws SQLException {
        CustomerEntity customerEntity=new CustomerEntity(
                customerDto.getCusId(),
                customerDto.getCusName(),
                customerDto.getCusAdd(),
                customerDto.getCusNic(),
                customerDto.getCusmobile(),
                customerDto.getGender()
        );
        return customerDaoImpl.updateCustomer(customerEntity);
    }

    @Override
    public boolean deleteCustomer(String cusId) throws SQLException {
        return customerDaoImpl.deleteCustomer(cusId);
    }

    @Override
    public CustomerDto findCustomer(String cusId) throws SQLException {
        CustomerEntity customerEntity=customerDaoImpl.findCustomer(cusId);
        return new CustomerDto(
                customerEntity.getCusId(),
                customerEntity.getCusName(),
                customerEntity.getCusAdd(),
                customerEntity.getCusNic(),
                customerEntity.getCusmobile(),
                customerEntity.getGender()

        );
    }
}
