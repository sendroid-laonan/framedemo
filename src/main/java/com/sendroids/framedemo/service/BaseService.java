package com.sendroids.framedemo.service;

import org.springframework.dao.DataAccessException;
import com.sendroids.framedemo.entity.Users;
import org.springframework.data.domain.Page;

public interface BaseService{

    // Users

    void save(Object obj);

    void delete(long id);

    void update();

    Page<Object> findAllByPage(Integer page, Integer size);

    public Users findUserById(long id) throws DataAccessException;


}
