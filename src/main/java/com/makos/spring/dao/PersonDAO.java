package com.makos.spring.dao;

import com.makos.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.queryForStream("SELECT * FROM person WHERE id=?",new BeanPropertyRowMapper<>(Person.class), new Object[]{id})
                .findAny()
                .orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person(name, age, email) VALUES (?, ?, ?)",
                person.getName(),
                person.getAge(),
                person.getEmail());
    }

    public void update(int id, Person updatedPerson) {
       jdbcTemplate.update("UPDATE person SET name=?, age=?, email=? WHERE id=?",
               updatedPerson.getName(),
               updatedPerson.getAge(),
               updatedPerson.getEmail(),
               id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }
}