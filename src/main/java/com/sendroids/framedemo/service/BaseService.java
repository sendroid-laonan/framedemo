package com.sendroids.framedemo.service;

import org.springframework.dao.DataAccessException;
import com.sendroids.framedemo.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BaseService{

    // User

    void save(Object obj);

    void delete(long id);

    void update();

    Page<Object> findAllByPage(Integer page, Integer size);

    public User findUserById(long id) throws DataAccessException;


}
