package lk.ijse.dao.custom;

import lk.ijse.dto.CarDto;
import lk.ijse.entity.CarEntity;
import lk.ijse.entity.tm.CarEntityTm;

import java.sql.SQLException;
import java.util.List;

public interface CarDao {
    Boolean deleteCar(String id) throws SQLException;

    CarEntity checkCar(String id) throws SQLException;

    Boolean saveCar(CarEntity carEntity) throws SQLException;

    Boolean carUpdate(CarEntity carEntity) throws SQLException;

    List<CarEntityTm> getTableData() throws SQLException;
}
