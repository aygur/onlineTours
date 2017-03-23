package com.naraikin.onlinetours.models.repository;

import com.naraikin.onlinetours.models.entities.TourE;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dmitrii on 21.03.17.
 */
public interface TourRepository extends CrudRepository<TourE, Integer>  {
    TourE findOne(Integer idtur);
    List<TourE> findAll();
    List<TourE> findByBookingAndDeleted(Short booking, Short deleted);
    TourE save(TourE tourE);
}
