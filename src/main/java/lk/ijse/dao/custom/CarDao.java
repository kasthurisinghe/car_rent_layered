package lk.ijse.dao.custom;

import lk.ijse.dto.CarDto;
import lk.ijse.entity.CarEntity;

import java.sql.SQLException;

public interface CarDao {
    Boolean deleteCar(String id) throws SQLException;

    CarEntity checkCar(String id) throws SQLException;

    Boolean saveCar(CarEntity carEntity) throws SQLException;

    Boolean carUpdate(CarEntity carEntity) throws SQLException;
}
