package com.example.todolist.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T, ID> {
    T save(T entity);
    List<T> findAll();
    Optional<T> findById(ID id);
    void deleteById(ID id);
}