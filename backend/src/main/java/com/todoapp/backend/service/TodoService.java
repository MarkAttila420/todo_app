package com.todoapp.backend.service;

import com.todoapp.backend.model.Todo;
import com.todoapp.backend.repository.TodoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public Todo createTodo(Todo todo){
        todoRepository.save(todo);
        return todo;
    }

    public List<Todo> getAllTodos(){
        return todoRepository.findAll();
    }

    public Optional<Todo> getTodoById(Long id){
        return todoRepository.findById(id);
    }

    @Transactional
    public Optional<Todo> updateTodo(Long id, Todo newTodo){
        Optional<Todo> old = todoRepository.findById(id);
        if(old.isPresent()){
            old.get().setCompleted(newTodo.getCompleted());
            old.get().setDescription(newTodo.getDescription());
            old.get().setTitle(newTodo.getTitle());
            return old;
        }
        return old;
    }

    @Transactional
    public void deleteTodo(Long id){
        todoRepository.deleteById(id);
    }

    @Transactional
    public Optional<Todo> switchCompleted(Long id){
        Optional<Todo> old = todoRepository.findById(id);
        if(old.isPresent()){
            old.get().setCompleted(!old.get().getCompleted());
            return old;
        }
        return old;
    }
}
