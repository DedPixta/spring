package com.makos.spring.dao;

import com.makos.spring.models.Book;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM spring.book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.queryForStream("SELECT * FROM spring.book WHERE id=?", new BeanPropertyRowMapper<>(Book.class), new Object[]{id})
                .findAny()
                .orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO spring.book(name, author, year) VALUES (?, ?, ?)",
                book.getName(),
                book.getAuthor(),
                book.getYear());
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE spring.book SET name=?, author=?, year=? WHERE id=?",
                updatedBook.getName(),
                updatedBook.getAuthor(),
                updatedBook.getYear(),
                id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM spring.book WHERE id=?", id);
    }

    public List<Book> showBorrowedBy(int id) {
        return jdbcTemplate.query("SELECT * FROM spring.book AS b JOIN spring.person_books AS pb ON b.id = pb.book_id WHERE pb.person_id=?", new BeanPropertyRowMapper<>(Book.class), id);
    }

    public void updateBorrow(int bookId, int personId) {
        jdbcTemplate.update("INSERT INTO spring.person_books(person_id, book_id) VALUES (?, ?)", personId, bookId);
    }

    public void updateRelease(int bookId) {
        jdbcTemplate.update("DELETE FROM spring.person_books WHERE book_id=?", bookId);
    }
}
