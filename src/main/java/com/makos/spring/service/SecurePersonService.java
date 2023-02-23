package com.makos.spring.service;

import com.makos.spring.model.SecurePerson;
import com.makos.spring.repository.SecurePersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SecurePersonService {

    private final SecurePersonRepository personRepository;

    public SecurePersonService(SecurePersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Optional<SecurePerson> findByUsername(String name) {
        return personRepository.findByName(name);
    }

    @Transactional
    public void register(SecurePerson person) {
        personRepository.save(person);
    }
}
