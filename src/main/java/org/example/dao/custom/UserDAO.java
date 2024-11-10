package org.example.dao.custom;
import org.example.dao.CrudDAO;
import org.example.entity.User;
import java.util.List;
import java.io.IOException;

public interface UserDAO extends CrudDAO<User> {
    User getUserByUsername(String username);

    List<User> getaAll() throws IOException;

    boolean delete(int entity) throws IOException;

    List<User> SearchUID(int uid) throws IOException;

}
