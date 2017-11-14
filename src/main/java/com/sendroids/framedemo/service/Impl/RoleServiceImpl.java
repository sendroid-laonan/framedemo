package com.sendroids.framedemo.service.Impl;

import com.sendroids.framedemo.entity.Role;
import com.sendroids.framedemo.repository.RoleRepository;
import com.sendroids.framedemo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleServiceImpl extends BaseServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role getRoleById(long id) {
        System.out.println(id);
        Role role = roleRepository.getOne(id);
        return role;
    }

}
