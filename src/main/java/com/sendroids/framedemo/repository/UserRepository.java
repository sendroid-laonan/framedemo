package com.sendroids.framedemo.repository;

import com.sendroids.framedemo.entity.User;
import com.sendroids.framedemo.entity.BaseEntity;
import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends Repository<User,Long>,JpaRepository<User,Long>,JpaSpecificationExecutor<User> {

    User findByUsername(String username) throws DataAccessException,UsernameNotFoundException;

    @Transactional
    @Modifying
    @Query(value = "select * from user where 1=1 and state=?1 and id>?2 order by id asc limit ?3",nativeQuery = true)
    List<User> findAllByState(int state,int page,int size);


    @Query(value = "select count(*) from user where 1=1 and state=?1 ",nativeQuery = true)
    int getCount(int state);
}
