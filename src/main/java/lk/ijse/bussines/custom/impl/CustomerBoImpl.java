package lk.ijse.bussines.custom.impl;

import lk.ijse.bussines.custom.CustomerBo;
import lk.ijse.dao.DaoFactory;
import lk.ijse.dao.DaoType;
import lk.ijse.dao.custom.CustomerDao;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.tm.CustomerDtoTm;
import lk.ijse.entity.CustomerEntity;
import lk.ijse.entity.tm.CustomerEntityTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    @Override
    public List<CustomerDtoTm> loadTableData() throws SQLException {
        List<CustomerEntityTm>customerEntityTms=customerDaoImpl.loadTable();

        List<CustomerDtoTm>customerDtoTms =new ArrayList<>();
        for (CustomerEntityTm customerEntityTm :customerEntityTms){
            String custId=customerEntityTm.getCustId();
            String custName=customerEntityTm.getCustName();
            String custAdd=customerEntityTm.getCustAddress();
            String custMobile=customerEntityTm.getCustMobile();
            String custNic=customerEntityTm.getCustNic();
            String custGender=customerEntityTm.getCustGender();

            CustomerDtoTm customerDtoTm=new CustomerDtoTm(custId,custName,custAdd,custNic,custMobile,custGender);
            customerDtoTms.add(customerDtoTm);
        }return  customerDtoTms;
    }
}
