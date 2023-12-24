package lk.ijse.bussines.custom.impl;

import lk.ijse.bussines.custom.ReturnBo;
import lk.ijse.dao.DaoFactory;
import lk.ijse.dao.DaoType;
import lk.ijse.dao.custom.ReturnDao;
import lk.ijse.dto.ReturnDto;
import lk.ijse.entity.ReturnEntity;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ReturnBoImpl implements ReturnBo {

     ReturnDao returnDaoImpl= DaoFactory.getDao(DaoType.RETURNDAO);

    Integer total;
    @Override
    public ReturnDto findRental(String bookId, LocalDate today) throws SQLException {

        ReturnEntity returnEntity=returnDaoImpl.findRental(bookId);
        int duration=getDuration(today,returnEntity.getEndDate());
         Integer panelty=0;
         String isOverDue;

        if (duration<0){
            total=returnEntity.getCharge();
            isOverDue="No";
        }else {
            System.out.println(duration);
            panelty=duration*1200;
            isOverDue="Yes";
            total=(returnEntity.getCharge())+panelty;
        }
        return new ReturnDto(
                returnEntity.getBookingID(),
                returnEntity.getCustId(),
                isOverDue,
                returnEntity.getCustName(),
                returnEntity.getVehiRegnNo(),
                panelty,
                total
        );

    }

    @Override
    public Boolean acceptReturn(String booId,Boolean isReturned) throws SQLException {
        return returnDaoImpl.acceptReturn(booId,isReturned);
    }

    private int getDuration(LocalDate today, LocalDate endDate) {
        return  (int) ChronoUnit.DAYS.between(endDate,today);
    }
}
