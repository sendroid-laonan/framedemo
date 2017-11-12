package com.sendroids.framedemo.service.Impl;

import com.sendroids.framedemo.entity.User;
import com.sendroids.framedemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public  class UserDetailServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
            User user = userRepository.findByUsername(s);
            if(user==null){
                throw  new UsernameNotFoundException("用户名不存在");
            }
            System.out.println("s:"+s);
            System.out.println("username:"+user.getUsername()+";password:"+user.getPassword());
            return user;
    }



}
