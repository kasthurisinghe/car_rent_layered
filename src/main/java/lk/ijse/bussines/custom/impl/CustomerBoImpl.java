package lk.ijse.bussines.custom.impl;

import lk.ijse.bussines.custom.CustomerBo;
import lk.ijse.dao.DaoFactory;
import lk.ijse.dao.DaoType;
import lk.ijse.dao.custom.CustomerDao;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.tm.CustomerDtoTm;
import lk.ijse.dto.tm.CustomerRentalDtoTm;
import lk.ijse.entity.CustomerEntity;
import lk.ijse.entity.tm.CustomerEntityTm;
import lk.ijse.entity.tm.CustomerRentalEntityTm;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

    @Override
    public List<CustomerRentalDtoTm> loadAllCustomerRentals(LocalDate today, String custId) throws SQLException {

        List<CustomerRentalEntityTm>customerRentalEntityTms=customerDaoImpl.loadRentalTable(custId);

        List<CustomerRentalDtoTm>customerRentalDtoTms=new ArrayList<>();

        if (customerRentalEntityTms!=null) {
            for (CustomerRentalEntityTm customerRentalEntityTm: customerRentalEntityTms){
                String bookingId=customerRentalEntityTm.getBookingId();
                LocalDate stratDate=customerRentalEntityTm.getStartDate();
                LocalDate endDate=customerRentalEntityTm.getEndDate();
                Integer rate=customerRentalEntityTm.getRate();
                Integer total=customerRentalEntityTm.getTotal();
                String overDue="";

                String returned="No";
                long daysBetween = ChronoUnit.DAYS.between( customerRentalEntityTm.getEndDate(),today);
                if (customerRentalEntityTm.getReturned()){
                    returned="Yes";
                    overDue="Returned";
                }else {
                    if (daysBetween<0){
                        overDue="";
                    }else {
                        overDue="Yes";
                    }
                }

                CustomerRentalDtoTm customerRentalDtoTm=new CustomerRentalDtoTm(bookingId,stratDate,endDate,rate,overDue,total,returned);
                customerRentalDtoTms.add(customerRentalDtoTm);
            }
            return customerRentalDtoTms;
        }return null;
    }
}
