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
        return jdbcTemplate.query("SELECT * FROM spring.person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.queryForStream("SELECT * FROM spring.person WHERE id=?", new BeanPropertyRowMapper<>(Person.class), new Object[]{id})
                .findAny()
                .orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO spring.person(full_name, birth_year) VALUES (?, ?)",
                person.getFullName(),
                person.getBirthYear());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE spring.person SET full_name=?, birth_year=? WHERE id=?",
                updatedPerson.getFullName(),
                updatedPerson.getBirthYear(),
                id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM spring.person WHERE id=?", id);
    }

    public Person showBorrowedPerson(int bookId) {
        return jdbcTemplate.queryForStream("SELECT * FROM spring.person AS p JOIN spring.person_books pb ON p.id=pb.person_id WHERE pb.book_id=?", new BeanPropertyRowMapper<>(Person.class), new Object[]{bookId} )
                .findAny()
                .orElse(null);
    }
}
