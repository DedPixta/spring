package com.makos.spring.repository;

import com.makos.spring.model.Measurement;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends ListCrudRepository<Measurement, Integer> {
    int countByRaining(Boolean isRainy);
}
