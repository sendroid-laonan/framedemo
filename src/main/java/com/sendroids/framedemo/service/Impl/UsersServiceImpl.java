package com.sendroids.framedemo.service.Impl;

import com.sendroids.framedemo.entity.Role;
import com.sendroids.framedemo.entity.Users;
import com.sendroids.framedemo.repository.UsersRepository;
import com.sendroids.framedemo.service.UsersService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UsersServiceImpl extends BaseServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public void save(Users users) {
        usersRepository.save(users);
    }

    @Override
    public void delete(long id){
        usersRepository.delete(id);
    }

    @Override
    public Page<Users> findAllByPage(Users users, int page, int size) {
        Specification specification = new Specification<Users>() {
            @Override
            public Predicate toPredicate(Root<Users> root,
                                         CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!StringUtils.isBlank(users.getPhone())) {
                    predicates.add(criteriaBuilder.like(root.get("phone").as(String.class), "%" + users.getPhone() + "%"));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };

        Pageable pageable = new PageRequest(page, size);

        return usersRepository.findAll(specification,pageable);
    }

    @Override
    public void update(Users users) {
        usersRepository.saveAndFlush(users);
    }

    @Override
    public List<Users> findAllByState(int page, int size) {
        List<Users> list = usersRepository.findAllByState(1,(page-1)*size,size);
        return list;
    }

    @Override
    public int getCount() {
        int Count = usersRepository.getCount(1);
        return Count;
    }

    @Override
    public Users getUserById(long id) {
        Users users = usersRepository.findById(id);
        return users;
    }

    @Override
    public List<Role> getRoleById(long id) {
        List<Role> role = usersRepository.findUserRole(id);
        return role;
    }

    @Override
    public void updateUserRole(long uid, long rid) {
        usersRepository.saveUserRole(uid,rid);
    }
}
