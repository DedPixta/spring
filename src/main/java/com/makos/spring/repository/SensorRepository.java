package com.makos.spring.repository;

import com.makos.spring.model.Sensor;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SensorRepository extends ListCrudRepository<Sensor, Integer> {

    Optional<Sensor> findByName(String name);
}
