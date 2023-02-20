package com.makos.spring.services;

import com.makos.spring.models.Book;
import com.makos.spring.models.Person;
import com.makos.spring.repositories.BooksRepository;
import com.makos.spring.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;
    private final PeopleRepository peopleRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository, PeopleRepository peopleRepository) {
        this.booksRepository = booksRepository;
        this.peopleRepository = peopleRepository;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    public Book findById(int bookId) {
        return booksRepository.findById(bookId)
                .orElse(null);
    }

    @Transactional
    public void update(int id, Book book) {
        book.setId(id);
        booksRepository.save(book);
    }

    @Transactional
    public void deleteById(int id) {
        booksRepository.deleteById(id);
    }

    @Transactional
    public void updateOwner(int bookId, int personId) {
        Optional<Book> optionalBook = booksRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setOwner(
                    peopleRepository.findById(personId)
                            .orElse(null)
            );
        }
    }

    @Transactional
    public void release(int bookId) {
        Optional<Book> optionalBook = booksRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setOwner(null);
        }
    }

    public List<Book> findByOwner(Person owner) {
        return booksRepository.findByOwner(owner);
    }
}
