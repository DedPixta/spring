package com.makos.spring.repository;

import com.makos.spring.model.SecurePerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SecurePersonRepository extends JpaRepository<SecurePerson, Integer> {

    Optional<SecurePerson> findByName(String name);

}
