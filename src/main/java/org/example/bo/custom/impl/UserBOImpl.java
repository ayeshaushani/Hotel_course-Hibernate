package org.example.bo.custom.impl;
import org.example.bo.custom.UserBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.UserDAO;
import org.example.dto.UserDTO;
import org.example.entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO{
    UserDAO userDao = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);


    @Override
    public boolean saveUser(UserDTO userDTO) throws IOException {
        return userDao.save( new User(userDTO.getId(),userDTO.getUsername(),userDTO.getPassword(),userDTO.getRole()));
    }

    @Override
    public List<UserDTO> getAllUser() throws IOException {

        List<UserDTO> userDTOS = new ArrayList<>();

        // Get all users, ensure users is not null
        List<User> users = userDao.getAll();

        if (users == null) {
            users = new ArrayList<>();  // Default to an empty list if null
        }

        // Iterate over users and convert them to DTOs
        for (User user : users) {
            UserDTO userDTO = new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getRole());
            userDTOS.add(userDTO);
        }

        return userDTOS;
    }

    @Override
    public boolean updateUser(User entity) throws IOException {
        return userDao.update(new User(entity.getId(),entity.getUsername(),entity.getPassword(),entity.getRole()));
    }

    @Override
    public boolean deleteUser(int id) throws IOException {
        return userDao.delete(String.valueOf(Math.toIntExact(id)));
    }

    @Override
    public List<User> SearchUID(int uid) throws IOException {

        return userDao.SearchUID(uid);

    }

}
