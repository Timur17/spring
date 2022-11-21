package ru.otus.spring.controller;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Controller;
import ru.otus.spring.service.CommentService;

@ShellComponent
@Controller
public class ShellCommentController {

    private final CommentService commentService;

    public ShellCommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @ShellMethod(value = "Count comments", key = {"countComments", "cc"})
    public void count() {
        commentService.count();
    }

    @ShellMethod(value = "Insert comment", key = {"insertComment", "ic"})
    public void insert(String comment, long bookId) {
        commentService.insert(comment, bookId);
    }

    @ShellMethod(value = "Update comment by id", key = {"updateComment", "uc"})
    public void updateById(String comment, long id) {
        commentService.updateById(comment, id);
    }

    @ShellMethod(value = "Delete comment  by id", key = {"deleteComment", "dc"})
    public void deleteById(int id) {
        commentService.deleteById(id);
    }

    @ShellMethod(value = "Show all comments", key = {"getAllComments", "gac"})
    public void ShowAllCommentsByBookIdAll(long id) {
        commentService.showAllByBookId(id);
    }

}
