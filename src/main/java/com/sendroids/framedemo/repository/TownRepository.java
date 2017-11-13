package com.sendroids.framedemo.repository;

import com.sendroids.framedemo.entity.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TownRepository extends Repository<Town,Long>,JpaRepository<Town,Long>,JpaSpecificationExecutor<Town> {

    @Transactional
    @Modifying
    @Query(value = "select * from town where 1=1 and id>?1 order by id asc limit ?2",nativeQuery = true)
    List<Town> findAllByState( int page, int size);


    @Query(value = "select count(*) from town where 1=1 ",nativeQuery = true)
    int getCount();
}
