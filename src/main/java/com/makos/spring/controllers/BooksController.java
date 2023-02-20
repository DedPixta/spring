package com.makos.spring.controllers;

import com.makos.spring.models.Book;
import com.makos.spring.models.Person;
import com.makos.spring.services.BooksService;
import com.makos.spring.services.PeopleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BooksService booksService;
    private final PeopleService peopleService;

    @Autowired
    public BooksController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }

    @GetMapping
    public String index(Model model,
                        @RequestParam(value = "page", defaultValue = "0") Integer page,
                        @RequestParam(value = "size", defaultValue = "3") Integer size,
                        @RequestParam(value = "sort_by_year", defaultValue = "false") boolean sortByYear
    ) {
        Page<Book> pageFromDB = booksService.findAll(page, size, sortByYear);
        model.addAttribute("sort_by_year", sortByYear);
        model.addAttribute("size", size);
        model.addAttribute("pages", pageFromDB.getTotalPages());
        model.addAttribute("books", pageFromDB.getContent());
        return "books/index";
    }

    @PostMapping
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/new";
        }
        booksService.save(book);
        return "redirect: /books";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int bookId,
                       Model model) {
        Book book = booksService.findById(bookId);
        model.addAttribute("book", book);
        Person owner = book.getOwner();
        if (Objects.isNull(owner)) {
            model.addAttribute("people", peopleService.findAll());
        } else {
            model.addAttribute("person", owner);
        }
        return "books/show";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {

        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        booksService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", booksService.findById(id));
        return "books/edit";
    }

    @PatchMapping("/{id}/assign")
    public String setBookOwner(@PathVariable("id") int bookId,
                               @RequestParam("id") int personId) {
        booksService.updateOwner(bookId, personId);
        return "redirect:/books/{id}";
    }

    @PatchMapping("/{id}/release")
    public String releaseBook(@PathVariable("id") int bookId) {
        booksService.release(bookId);
        return "redirect:/books/{id}";
    }

    @GetMapping("/search")
    public String search() {
        return "books/search";
    }

    @PostMapping("/search")
    public String searchStartWith(Model model, @RequestParam("start_with") String startWith) {
        model.addAttribute("books", booksService.findAllStartWith(startWith));
        return "books/search";
    }

}
