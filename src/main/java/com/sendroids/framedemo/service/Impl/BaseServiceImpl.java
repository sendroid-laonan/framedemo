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
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.delete(id);
    }

    @Override
    public void updateUser(String phone, Long id) {

    }

    @Override
    public Page<User> findAllUser(Integer page, Integer size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.DESC, "id");
        return userRepository.findAll(pageable);
    }

    @Override
    public User findUserById(long id) throws DataAccessException {
        return null;
    }
}
