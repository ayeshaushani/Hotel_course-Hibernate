package org.example.bo.custom;
import org.example.bo.SuperBO;
import org.example.dto.UserDTO;
import org.example.entity.User;

import java.io.IOException;
import java.util.List;

public interface UserBO extends SuperBO {

    public boolean saveUser(UserDTO userDTO) throws IOException;

    public List<UserDTO> getAllUser() throws IOException;

    public boolean updateUser(User entity) throws IOException ;


    public boolean deleteUser(int id) throws IOException;


    public List<User> SearchUID(int uid) throws IOException;

}
