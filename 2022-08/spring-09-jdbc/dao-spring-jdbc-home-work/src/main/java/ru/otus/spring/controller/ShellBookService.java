package ru.otus.spring.controller;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Controller;
import ru.otus.spring.dao.Book;
import ru.otus.spring.dao.BookDaoJdbc;

import java.util.List;

@ShellComponent
@Controller
public class ShellBookService implements BookService {

    private final String COMMAND_HELP = "help";
    private final String COMMAND_COUNT = "count";
    private final String COMMAND_INSERT = "insert";
    private final String COMMAND_UPDATE = "update";
    private final String COMMAND_DELETE = "delete";
    private final String COMMAND_GET_ALL = "getAll";
    private final String COMMAND_GET = "get";
    private final BookDaoJdbc bookDaoJdbc;

    public ShellBookService(BookDaoJdbc bookDaoJdbc) {
        this.bookDaoJdbc = bookDaoJdbc;
    }


    @ShellMethod(value = "Help", key = {"help"})
    public void help() {
        List<String> commands = List.of(COMMAND_HELP, COMMAND_COUNT, COMMAND_INSERT,
                COMMAND_UPDATE, COMMAND_DELETE, COMMAND_GET_ALL, COMMAND_GET);
        System.out.println("Available commands: ");
        commands.stream().forEach(command -> System.out.println(command));;
    }

    @Override
    @ShellMethod(value = "Count books", key = {"count"})
    public int count() {
        int count = bookDaoJdbc.count();
        System.out.println("Amount books: " + count);
        return count;
    }

    @Override
    @ShellMethod(value = "Insert book", key = {"insert"})
    public int insert(String title, String author, String genre) {
        Book book = new Book(title, author, genre);
        return bookDaoJdbc.insert(book);
    }

    @Override
    @ShellMethod(value = "Update book by id", key = {"update"})
    public void update(String title, String author, String genre, int id) {
        Book book = new Book(title, author, genre);
        bookDaoJdbc.updateById(book, id);
    }

    @Override
    @ShellMethod(value = "Delete book", key = {"delete"})
    public void deleteById(int id) {
        bookDaoJdbc.deleteById(id);
    }

    @Override
    @ShellMethod(value = "Get all books", key = {"getAll"})
    public void getAll() {
        System.out.println("All books: " + bookDaoJdbc.getAll());
    }

    @Override
    @ShellMethod(value = "Get book by id", key = {"get"})
    public void getById(int id) {
        System.out.println("All books: " + bookDaoJdbc.getById(id));
    }
}
