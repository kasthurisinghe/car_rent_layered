package lk.ijse.bussines.custom;

import lk.ijse.dao.custom.CreateUserDao;
import lk.ijse.dto.CreateUserdto;
import lk.ijse.entity.CreateUserEntity;

import java.sql.SQLException;

public interface CreateUserBo {
    CreateUserdto findUser(String id) throws SQLException;

    boolean saveAdminUser(CreateUserdto createUserDto) throws SQLException;
}
