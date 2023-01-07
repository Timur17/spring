package ru.otus.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.domain.Book;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.ioservice.ConsoleIOService;

import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;
    private final ConsoleIOService consoleIOService;

    public BookController(BookService bookService, ConsoleIOService consoleIOService) {
        this.bookService = bookService;
        this.consoleIOService = consoleIOService;
    }

    @GetMapping("/")
    public String listPage(Model model) {
        List<Book> books = bookService.getAll();
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") long id, Model model) {
        System.out.println("Test1::: ");
        var book = bookService.getById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("book", book);
        return "edit";
    }

    @PostMapping("/edit")
    public String editPerson(Book book) {
        System.out.println("Test2::: " + book);
        Book bookSaved = bookService.getById(book.getId()).orElseThrow(NotFoundException::new);
        bookService.updateById(book.getTitle(), bookSaved.getId());
        return "redirect:/";
    }

//    @PostMapping("/edit")
//    public String editPerson(@RequestParam("id") long id, String title) {
//        Book bookSaved = bookService.getById(id).orElseThrow(NotFoundException::new);
//        bookService.updateById(title, bookSaved.getId());
//        return "redirect:/";
//    }

    @GetMapping("/add")
    public String addPage() {
        return "add";
    }

    @PostMapping("/add")
    public String addBook(@RequestParam("title") String title) {
        System.out.println("Test2::: " + title);
        Book book = bookService.insert(title, title, title);
        if (book == null) {
            consoleIOService.outputString("Store already has book - " + book);
        } else {
            consoleIOService.outputString("Book - " + book + " was added with id: " + book.getId());
        }
        return "redirect:/";
    }

}
