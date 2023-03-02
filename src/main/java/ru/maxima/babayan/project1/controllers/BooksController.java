package ru.maxima.babayan.project1.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.maxima.babayan.project1.dao.BookDAO;
import ru.maxima.babayan.project1.dao.PersonDAO;
import ru.maxima.babayan.project1.models.Book;
import ru.maxima.babayan.project1.models.Person;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.personDAO=personDAO;
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "/books/index";
    }

    @GetMapping("/new")
    public String newBookFormOpen(@ModelAttribute("book") Book book) {
        return "/books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "books/new";
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.show(id));
        return "/books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "books/edit";
        bookDAO.update(id, book);
        return "redirect:/books";
    }

//    @GetMapping("/{id}")
//    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person ) {
//        model.addAttribute("book", bookDAO.show(id));
//        List<Book> books = personDAO.hasOwner(id);
//        if (books.size() != 0) {
//            model.addAttribute("people", personDAO.index());
//            return "/books/show-free";
//        } else {
//                model.addAttribute("person", personDAO.indexBook(id));
//                return "/books/show-hold";
//                }
//    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person ) {
        model.addAttribute("book", bookDAO.show(id));
        List<Book> books = personDAO.hasOwner(id);
        boolean isFree = true;
        if (books.size() != 0) {
            model.addAttribute("isFree", isFree);
            model.addAttribute("people", personDAO.index());
        } else {
            model.addAttribute("isFree", !isFree);
            model.addAttribute("person", personDAO.indexBook(id));
        }
        return "/books/show";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @PutMapping("/{id}")
    public String unlink(@PathVariable("id") int id) {
        bookDAO.unlink(id);
        return "redirect:/books/{id}";
    }

    @PostMapping("/{id}")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        bookDAO.assign(id, person);
        return "redirect:/books/{id}";
    }

}
