package co.simplon.everydaybetterbusiness.services;

import co.simplon.everydaybetterbusiness.entities.Activity;

import java.util.List;


public interface ActivityService {

    void save(Activity entity);

    List<Activity> findByUserId(Long id);

    Activity findById(Long id);

    void delete(Long id);

}
