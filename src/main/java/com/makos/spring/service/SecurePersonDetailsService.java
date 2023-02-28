package com.makos.spring.service;

import com.makos.spring.model.SecurePerson;
import com.makos.spring.repository.SecurePersonRepository;
import com.makos.spring.security.SecurePersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurePersonDetailsService implements UserDetailsService {

    private final SecurePersonRepository securePersonRepository;

    @Autowired
    public SecurePersonDetailsService(SecurePersonRepository securePersonRepository) {
        this.securePersonRepository = securePersonRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SecurePerson> person = securePersonRepository.findByName(username);

        if(person.isEmpty()) {
            throw new UsernameNotFoundException("User not found!");
        }

        return new SecurePersonDetails(person.get());
    }
}
