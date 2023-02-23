package com.makos.spring.util;

import com.makos.spring.model.SecurePerson;
import com.makos.spring.service.SecurePersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {

    private final SecurePersonService personService;

    @Autowired
    public PersonValidator(SecurePersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SecurePerson.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SecurePerson securePerson = (SecurePerson) target;

        Optional<SecurePerson> userFromDB = personService.findByUsername(securePerson.getName());
        if (userFromDB.isPresent()) {
            errors.rejectValue("name", "", "Person with this username already exists");
        }
    }
}
