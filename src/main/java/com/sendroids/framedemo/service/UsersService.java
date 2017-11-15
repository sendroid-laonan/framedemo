package com.sendroids.framedemo.service;

import com.sendroids.framedemo.entity.Role;
import com.sendroids.framedemo.entity.Users;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UsersService extends BaseService {

    void save(Users users);

    void delete(long id);

    Page<Users> findAllByPage(Users Users, int page, int size);

    List<Users> findAllByState(int page, int size);

    List<Role> getRoleById(long id);

    Users getUserById(long id);

    void update(Users users);

    int getCount();

    void updateUserRole(long uid,long rid);
}
