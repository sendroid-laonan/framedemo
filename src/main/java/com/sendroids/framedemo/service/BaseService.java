package com.sendroids.framedemo.service;

import org.springframework.dao.DataAccessException;
import com.sendroids.framedemo.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BaseService{

    // User

    void saveUser(User user);

    void deleteUser(long id);

    void updateUser(String phone,Long id);

    Page<User> findAllUser(Integer page, Integer size);

    public User findUserById(long id) throws DataAccessException;


}
