package com.sendroids.framedemo.service;

import com.sendroids.framedemo.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService extends BaseService {

    void save(User user);

    void delete(long id);

    Page<User> findAllByPage(User User, int page, int size);

    List<User> findAllByState();

    void update(User user);

}
