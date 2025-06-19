package org.example.todogithubactionsbackend.repository;

import org.example.todogithubactionsbackend.model.ToDo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToDoRepository extends MongoRepository<ToDo, String> {
}
