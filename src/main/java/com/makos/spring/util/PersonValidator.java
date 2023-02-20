package com.makos.spring.util;

import com.makos.spring.models.Person;
import com.makos.spring.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
        if(peopleService.getPersonByFullName(person.getFullName()).isPresent()) {
            errors.rejectValue("fullName", "Person with this Fullname and Lastname already exists");
        }
    }
}
