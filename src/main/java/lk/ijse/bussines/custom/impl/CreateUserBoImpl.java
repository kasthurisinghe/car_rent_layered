package lk.ijse.bussines.custom.impl;

import lk.ijse.bussines.custom.CreateUserBo;
import lk.ijse.dao.DaoFactory;
import lk.ijse.dao.DaoType;
import lk.ijse.dao.custom.CreateUserDao;
import lk.ijse.dto.CreateUserdto;
import lk.ijse.entity.CreateUserEntity;

import java.sql.SQLException;


public class CreateUserBoImpl implements CreateUserBo {

    CreateUserDao createUserDaoImpl= DaoFactory.getDao(DaoType.ADMINUSER);

    @Override
    public CreateUserdto findUser(String id) throws SQLException {
        CreateUserEntity createUserEntity=createUserDaoImpl.findUser(id);
        return new CreateUserdto(
                createUserEntity.getId(),
                createUserEntity.getName(),
                createUserEntity.getAddr(),
                createUserEntity.getMobile(),
                createUserEntity.getPass(),
                createUserEntity.getGender()
        );
    }

    @Override
    public boolean saveAdminUser(CreateUserdto createUserDto) throws SQLException {
        CreateUserEntity createUserEntity=new CreateUserEntity(
            createUserDto.getId(),
                createUserDto.getName(),
                createUserDto.getAddr(),
                createUserDto.getMobile(),
                createUserDto.getPass(),
                createUserDto.getGender()
        );
        return createUserDaoImpl.saveAdmin(createUserEntity);
    }
}
