package com.sendroids.framedemo.service.Impl;

import com.sendroids.framedemo.entity.User;
import com.sendroids.framedemo.repository.UserRepository;
import com.sendroids.framedemo.service.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class BaseServiceImpl implements BaseService{

    @Autowired
    UserRepository userRepository;

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
    public User findUserById(long id) throws DataAccessException {
        return userRepository.findOne(id);
    }
}
