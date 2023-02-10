package com.makos.spring.dao;

import com.makos.spring.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(1, "Ivan"));
        people.add(new Person(2, "Mariya"));
        people.add(new Person(3, "Igor"));
        people.add(new Person(4, "Andrii"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream()
                .filter(person -> person.getId() == id)
                .findAny()
                .orElse(null);
    }
}
