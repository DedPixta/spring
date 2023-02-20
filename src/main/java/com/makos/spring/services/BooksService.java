package com.makos.spring.services;

import com.makos.spring.models.Book;
import com.makos.spring.models.Person;
import com.makos.spring.repositories.BooksRepository;
import com.makos.spring.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BooksService {

    public static final long TEN_DAYS = 864000000L;
    private final BooksRepository booksRepository;
    private final PeopleRepository peopleRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository, PeopleRepository peopleRepository) {
        this.booksRepository = booksRepository;
        this.peopleRepository = peopleRepository;
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
        booksRepository.findById(bookId)
                .ifPresent(book -> {
                    book.setTakenAt(new Date());
                    book.setOwner(
                            peopleRepository.findById(personId)
                                    .orElse(null)
                    );
                });
    }

    @Transactional
    public void release(int bookId) {
        booksRepository.findById(bookId)
                .ifPresent(book -> {
                    book.setTakenAt(null);
                    book.setOwner(null);
                });
    }

    public List<Book> findByOwner(Person owner) {
        List<Book> books = booksRepository.findByOwner(owner);
        Date currentTime = new Date();
        for (Book book : books) {
            Date takenAt = book.getTakenAt();
            long period = Math.abs(currentTime.getTime() - takenAt.getTime());
            if (period > TEN_DAYS) {
                book.setExpired(true);
            }
        }
        return books;
    }

    public Page<Book> findAll(Integer page, Integer size) {
        return booksRepository.findAll(PageRequest.of(page, size));
    }

    public Page<Book> findAll(Integer page, Integer size, boolean sortByYear) {
        if (sortByYear) {
            return booksRepository.findAll(PageRequest.of(page, size, Sort.by("year")));
        } else {
            return findAll(page, size);
        }
    }

    public List<Book> findAllStartWith(String startWith) {
        return booksRepository.findByTitleStartingWithIgnoreCase(startWith);
    }
}
