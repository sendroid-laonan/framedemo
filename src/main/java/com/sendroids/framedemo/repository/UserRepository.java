package com.sendroids.framedemo.repository;

import com.sendroids.framedemo.entity.User;
import com.sendroids.framedemo.entity.BaseEntity;
import java.util.Collection;
import org.springframework.dao.DataAccessException;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserRepository extends Repository<User,Long>,JpaRepository<User,Long>,JpaSpecificationExecutor<User> {

    User findByUsername(String username) throws DataAccessException,UsernameNotFoundException;

}
