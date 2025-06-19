package org.example.todogithubactionsbackend.service;

import org.example.todogithubactionsbackend.model.ToDo;
import org.example.todogithubactionsbackend.repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {
    private final ToDoRepository todoRepository;

    public ToDoService(ToDoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<ToDo> findAll() {
        return todoRepository.findAll();
    }

    public ToDo save(ToDo todo) {
        return todoRepository.save(todo);
    }

    public Optional<ToDo> update(String id, ToDo updatedTodo) {
        return todoRepository.findById(id).map(existing -> {
            existing.setTitle(updatedTodo.getTitle());
            existing.setCompleted(updatedTodo.isCompleted());
            return todoRepository.save(existing);
        });
    }

    public void delete(String id) {
        todoRepository.deleteById(id);
    }
}

