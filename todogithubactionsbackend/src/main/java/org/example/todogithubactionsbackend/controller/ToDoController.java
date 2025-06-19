package org.example.todogithubactionsbackend.controller;

import org.example.todogithubactionsbackend.model.ToDo;
import org.example.todogithubactionsbackend.service.ToDoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "*")
public class ToDoController {
    private final ToDoService todoService;

    public ToDoController(ToDoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<ToDo> getAllTodos() {
        return todoService.findAll();
    }

    @PostMapping
    public ToDo createTodo(@RequestBody ToDo todo) {
        return todoService.save(todo);
    }

    @PutMapping("/{id}")
    public Optional<ToDo> updateTodo(@PathVariable String id, @RequestBody ToDo todo) {
        return todoService.update(id, todo);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable String id) {
        todoService.delete(id);
    }
    
}
