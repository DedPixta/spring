package com.makos.spring.repositories;

import com.makos.spring.models.Book;
import com.makos.spring.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    List<Book> findByOwner(Person owner);
}
