package com.makos.spring.service;

import com.makos.spring.model.SecurePerson;
import com.makos.spring.repository.SecurePersonRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SecurePersonService {

    private final SecurePersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    public SecurePersonService(SecurePersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<SecurePerson> findByUsername(String name) {
        return personRepository.findByName(name);
    }

    @Transactional
    public void register(SecurePerson person) {
        String encodedPassword = passwordEncoder.encode(person.getPass());
        person.setPass(encodedPassword);
        personRepository.save(person);
    }
}
