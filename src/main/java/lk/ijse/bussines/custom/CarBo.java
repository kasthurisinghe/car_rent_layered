package lk.ijse.bussines.custom;

import lk.ijse.dto.CarDto;
import lk.ijse.entity.CarEntity;

import java.sql.SQLException;

public interface CarBo {

    public Boolean deleteCar(String id) throws SQLException;

    CarDto chekcCar(String id) throws SQLException;

    Boolean saveCar(CarDto carDto) throws SQLException;

    Boolean updateCar(CarDto carDto) throws SQLException;
}
