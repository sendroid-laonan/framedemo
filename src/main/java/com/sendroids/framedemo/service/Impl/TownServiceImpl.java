package com.sendroids.framedemo.service.Impl;

import com.sendroids.framedemo.entity.Town;
import com.sendroids.framedemo.entity.User;
import com.sendroids.framedemo.repository.TownRepository;
import com.sendroids.framedemo.repository.UserRepository;
import com.sendroids.framedemo.service.TownService;
import com.sendroids.framedemo.service.UserService;
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
public class TownServiceImpl extends BaseServiceImpl implements TownService{

    @Autowired
    private TownRepository townRepository;

    @Override
    public void save(Town town) {
        townRepository.save(town);
    }

    @Override
    public void delete(long id){
        townRepository.delete(id);
    }

    @Override
    public Page<Town> findAllByPage(Town town, int page, int size) {
        Specification specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root,
                                         CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };

        Pageable pageable = new PageRequest(page, size);

        return townRepository.findAll(specification,pageable);
    }

    @Override
    public void update(Town town) {
        townRepository.saveAndFlush(town);
    }

    @Override
    public List<Town> findAllByState(int page,int size) {
        List<Town> list = townRepository.findAllByState((page-1)*size,size);
        return list;
    }

    @Override
    public int getCount() {
        int Count = townRepository.getCount();
        return Count;
    }
}
