package lk.ijse.bussines.custom.impl;

import lk.ijse.bussines.custom.AllOverDueBo;
import lk.ijse.dao.DaoFactory;
import lk.ijse.dao.DaoType;
import lk.ijse.dao.custom.AllOverDueDao;
import lk.ijse.dto.tm.AllOverDueDtoTm;
import lk.ijse.entity.tm.AllOverDueEntityTm;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class AllOverDueBoImpl implements AllOverDueBo {
    AllOverDueDao allOverDueDaoImpl= DaoFactory.getDao(DaoType.ALLOVERDUE);
    @Override
    public List<AllOverDueDtoTm> getAllOverDueTable(LocalDate today) throws SQLException {
        List<AllOverDueEntityTm>allOverDueEntityTms=allOverDueDaoImpl.getAllOverDues();
        List<AllOverDueDtoTm>allOverDueDtoTms=new ArrayList<>();
        
        for (AllOverDueEntityTm allOverDueEntityTm: allOverDueEntityTms){
            Integer chargeForOverDue=getCharge(today,allOverDueEntityTm.getAmount(),allOverDueEntityTm.getEndDate());
            boolean isReturned=allOverDueEntityTm.getReturned();
            if (!checkOverDue(today,allOverDueEntityTm.getEndDate()) && !isReturned) {
                AllOverDueDtoTm allOverDueDtoTm=new AllOverDueDtoTm(
                        allOverDueEntityTm.getCustId(),
                        allOverDueEntityTm.getVehicleId(),
                        allOverDueEntityTm.getEndDate(),
                        allOverDueEntityTm.getRate(),
                        chargeForOverDue
                );
                allOverDueDtoTms.add(allOverDueDtoTm);
            }
        }return allOverDueDtoTms;
    }

    private boolean checkOverDue(LocalDate today, LocalDate endDate) {
        Long duration=ChronoUnit.DAYS.between(today, endDate);
        if (!(duration <0)){
            return true;
        }
        return false;
    }

    private Integer getCharge(LocalDate today, Integer amount, LocalDate dueDate) {
        Integer duration= (int) ChronoUnit.DAYS.between(dueDate, today);
        return (duration*1200)+amount;
    }
}
