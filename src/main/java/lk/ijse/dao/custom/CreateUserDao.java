package lk.ijse.dao.custom;

import lk.ijse.entity.CreateUserEntity;

import javax.swing.text.html.parser.Entity;
import java.sql.SQLException;

public interface CreateUserDao {
    CreateUserEntity findUser(String id) throws SQLException;

    boolean saveAdmin(CreateUserEntity createUserEntity) throws SQLException;

    boolean deleteUser(String userId) throws SQLException;
}
