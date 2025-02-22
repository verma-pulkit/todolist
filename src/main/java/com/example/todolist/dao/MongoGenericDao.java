package com.example.todolist.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public class MongoGenericDao<T, ID> implements GenericDao<T, ID> {

    private final MongoRepository<T, ID> repository;

    public MongoGenericDao(MongoRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }
}
