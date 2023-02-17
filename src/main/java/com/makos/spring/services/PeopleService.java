package com.makos.spring.services;

import com.makos.spring.models.Person;
import com.makos.spring.repositories.PeopleRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepositry peopleRepositry;

    @Autowired
    public PeopleService(PeopleRepositry peopleRepositry) {
        this.peopleRepositry = peopleRepositry;
    }

    public List<Person> findAll() {
        return peopleRepositry.findAll();
    }

    public Person findOne(int id) {
        return peopleRepositry
                .findById(id)
                .orElse(null);
    }

    @Transactional
    public void save(Person person) {
        peopleRepositry.save(person);
    }

    @Transactional
    public void update(int id, Person updatePerson) {
        updatePerson.setId(id);
        peopleRepositry.save(updatePerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepositry.deleteById(id);
    }
}
