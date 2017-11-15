package com.sendroids.framedemo.service.Impl;

import com.sendroids.framedemo.entity.Users;
import com.sendroids.framedemo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public  class UserDetailServiceImpl implements UserDetailsService{

    @Autowired
    private UsersRepository usersRepository;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
            Users users = usersRepository.findByUsername(s);
            if(users ==null){
                throw  new UsernameNotFoundException("用户名不存在");
            }
            System.out.println("s:"+s);
            System.out.println("username:"+ users.getUsername()+";password:"+ users.getPassword());
            return users;
    }



}
