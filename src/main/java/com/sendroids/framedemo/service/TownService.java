package com.sendroids.framedemo.service;

import com.sendroids.framedemo.entity.Town;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TownService extends BaseService {

    void save(Town town);

    void delete(long id,String idstr);

    void deleteBatch(String idstr);

    Page<Town> findAllByPage(Town town, int page, int size);

    List<Town> findAllByState(int page, int size);

    Town getTownById(long id);

    void update(Town town);

    int getCount();

}
