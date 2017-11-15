package com.sendroids.framedemo.repository;

import com.sendroids.framedemo.entity.Role;
import com.sendroids.framedemo.entity.Users;

import java.util.List;

import org.springframework.dao.DataAccessException;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public interface UsersRepository extends Repository<Users,Long>,JpaRepository<Users,Long>,JpaSpecificationExecutor<Users> {

    Users findByUsername(String username) throws DataAccessException,UsernameNotFoundException;

    @Transactional
    @Modifying
    @Query(value = "select * from users where 1=1 and state=?1 and id>?2 order by id asc limit ?3",nativeQuery = true)
    List<Users> findAllByState(int state, int page, int size);

    @Query(value = "select count(*) from users where 1=1 and state=?1 ",nativeQuery = true)
    int getCount(int state);

    @Query(value = "select * from role where 1=1 and id=?1 ",nativeQuery = true)
    List<Role> findUserRole(long id);

    @Transactional
    @Modifying
    @Query(value = "insert into `user_roles`(`user_id`,`role_id`) values (?1,?2); ",nativeQuery = true)
    void saveUserRole(long uid,long rid);
}
