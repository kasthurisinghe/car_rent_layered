package lk.ijse.bussines.custom.impl;

import lk.ijse.bussines.custom.AllRental;
import lk.ijse.dao.DaoFactory;
import lk.ijse.dao.DaoType;
import lk.ijse.dao.custom.AllRentalsDao;
import lk.ijse.dto.tm.AllRentalsDtoTm;
import lk.ijse.entity.tm.AllRentalsEntityTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AllRentalBoImpl implements AllRental {
    AllRentalsDao allRentalsDaoImpl= DaoFactory.getDao(DaoType.ALLRENTALS);
    @Override
    public List<AllRentalsDtoTm> loadAllrentalDetails() throws SQLException {
        List<AllRentalsEntityTm> rentalsEntityTms=  allRentalsDaoImpl.loadAllRentalTable();

        if (rentalsEntityTms!=null) {
            List<AllRentalsDtoTm>allRentalsDtoTms =new ArrayList<>();
            for (AllRentalsEntityTm allRentalsEntityTm: rentalsEntityTms){
                String returned="No";
                if(allRentalsEntityTm.getReturned()){
                    returned="yes";
                }
                AllRentalsDtoTm  allRentalsDtoTm=new AllRentalsDtoTm(
                        allRentalsEntityTm.getCustId(),
                        allRentalsEntityTm.getVehiId(),
                        allRentalsEntityTm.getStartDate(),
                        allRentalsEntityTm.getEndDate(),
                        allRentalsEntityTm.getRate(),
                        allRentalsEntityTm.getAmount(),
                        returned
                );
                allRentalsDtoTms.add(allRentalsDtoTm);
            }
            return allRentalsDtoTms;
        }
        else return null;
    }
}
