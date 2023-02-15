package com.makos.spring.controllers;

import com.makos.spring.dao.BookDAO;
import com.makos.spring.dao.PersonDAO;
import com.makos.spring.models.Book;
import com.makos.spring.models.Person;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());

        return "books/index";
    }

    @PostMapping
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/new";
        }
        bookDAO.save(book);
        return "redirect: /books";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @GetMapping("/{id}")
    public String show(@ModelAttribute("person") Person person,
                       @PathVariable("id") int bookId,
                       Model model) {
        model.addAttribute("book", bookDAO.show(bookId));
        model.addAttribute("people", personDAO.index());
        model.addAttribute("person", personDAO.showBorrowedPerson(bookId));
        return "books/show";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {

        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}/borrow")
    public String updateBorrow(@PathVariable("id") int bookId,
                               @RequestParam("id") int personId) {
        bookDAO.updateBorrow(bookId, personId);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}/release")
    public String updateRelease(@PathVariable("id") int bookId) {
        bookDAO.updateRelease(bookId);
        return "redirect:/books";
    }

}
