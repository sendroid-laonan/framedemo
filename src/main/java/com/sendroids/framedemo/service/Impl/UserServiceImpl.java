package com.sendroids.framedemo.service.Impl;

import com.sendroids.framedemo.entity.Role;
import com.sendroids.framedemo.entity.User;
import com.sendroids.framedemo.repository.UserRepository;
import com.sendroids.framedemo.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(long id){
        userRepository.delete(id);
    }

    @Override
    public Page<User> findAllByPage(User user, int page, int size) {
        Specification specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root,
                                         CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!StringUtils.isBlank(user.getPhone())) {
                    predicates.add(criteriaBuilder.like(root.get("phone").as(String.class), "%" + user.getPhone() + "%"));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };

        Pageable pageable = new PageRequest(page, size);

        return userRepository.findAll(specification,pageable);
    }

    @Override
    public void update(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> findAllByState(int page,int size) {
        List<User> list = userRepository.findAllByState(1,(page-1)*size,size);
        return list;
    }

    @Override
    public int getCount() {
        int Count = userRepository.getCount(1);
        return Count;
    }

    @Override
    public User getUserById(long id) {
        User user = userRepository.getOne(id);
        return user;
    }

    @Override
    public List<Role> getRoleById(long id) {
        List<Role> role = userRepository.findUserRole(id);
        return role;
    }

    @Override
    public void updateUserRole(long uid, long rid) {
        userRepository.saveUserRole(uid,rid);
    }
}
