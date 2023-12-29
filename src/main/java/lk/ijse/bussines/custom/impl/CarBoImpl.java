package lk.ijse.bussines.custom.impl;

import lk.ijse.bussines.custom.CarBo;
import lk.ijse.dao.DaoFactory;
import lk.ijse.dao.DaoType;
import lk.ijse.dao.custom.CarDao;
import lk.ijse.dto.CarDto;
import lk.ijse.dto.tm.BookingDtoTm;
import lk.ijse.dto.tm.CarDtoTm;
import lk.ijse.entity.CarEntity;
import lk.ijse.entity.tm.CarEntityTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarBoImpl implements CarBo {
    CarDao carDaoImpl= DaoFactory.getDao(DaoType.CARDAO);

    @Override
    public Boolean deleteCar(String id) throws SQLException {
        return carDaoImpl.deleteCar(id);
    }

    @Override
    public CarDto chekcCar(String id) throws SQLException {
         CarEntity carEntity =carDaoImpl.checkCar(id);
        if (carEntity!=null) {
            return new CarDto(
                    carEntity.getRegNo(),
                    carEntity.getIdNo(),
                    carEntity.getModle(),
                    carEntity.getBarnd(),
                    carEntity.getColour(),
                    carEntity.getType()
            );
        }return null;
    }

    @Override
    public Boolean saveCar(CarDto carDto) throws SQLException {
        CarEntity carEntity=new CarEntity(
                carDto.getRegNo(),
                carDto.getId(),
                carDto.getModle(),
                carDto.getBrand(),
                carDto.getColour(),
                carDto.getType()
        );
        return carDaoImpl.saveCar(carEntity);
    }

    @Override
    public Boolean updateCar(CarDto carDto) throws SQLException {
        CarEntity carEntity=new CarEntity(
                carDto.getRegNo(),
                carDto.getId(),
                carDto.getModle(),
                carDto.getBrand(),
                carDto.getColour(),
                carDto.getType()
                );
        Boolean isUpdated=carDaoImpl.carUpdate(carEntity);
        return isUpdated;
    }

    @Override
    public List<CarDtoTm> getTableData() throws SQLException {
        List<CarEntityTm> carEntityTms=carDaoImpl.getTableData();

        List<CarDtoTm>carDtoTms=new ArrayList<>();

        if (carEntityTms!=null){
            for (CarEntityTm carEntityTm: carEntityTms){
                CarDtoTm carDtoTm=new CarDtoTm(
                        carEntityTm.getRegNo(),
                        carEntityTm.getId(),
                        carEntityTm.getModle(),
                        carEntityTm.getBrand(),
                        carEntityTm.getColour(),
                        carEntityTm.getType()
                );
                carDtoTms.add(carDtoTm);
            }
            return  carDtoTms;
        }
        return null;
    }
}
