package com.sendroids.framedemo.service.Impl;

import com.sendroids.framedemo.entity.Users;
import com.sendroids.framedemo.repository.UsersRepository;
import com.sendroids.framedemo.service.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;

public class BaseServiceImpl implements BaseService{

    @Autowired
    UsersRepository usersRepository;

    @Override
    public void save(Object obj) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void update() {

    }

    @Override
    public Page<Object> findAllByPage(Integer page, Integer size) {
        return null;
    }

    @Override
    public Users findUserById(long id) throws DataAccessException {
        return usersRepository.findOne(id);
    }
}
