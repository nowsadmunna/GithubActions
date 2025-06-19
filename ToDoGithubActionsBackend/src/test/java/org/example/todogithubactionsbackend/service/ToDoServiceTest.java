package org.example.todogithubactionsbackend.service;

import org.example.todogithubactionsbackend.model.ToDo;
import org.example.todogithubactionsbackend.repository.ToDoRepository;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ToDoServiceTest {

    @Test
    public void testFindAll() {
        ToDoRepository repository = mock(ToDoRepository.class);
        when(repository.findAll()).thenReturn(Arrays.asList(new ToDo("Task1", false)));

        ToDoService service = new ToDoService(repository);
        List<ToDo> todos = service.findAll();

        assertEquals(1, todos.size());
        assertEquals("Task1", todos.get(0).getTitle());
    }
}