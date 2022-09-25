package com.verta.repository;

import com.verta.domain.Agent;

import java.util.List;
import java.util.Optional;


// Данный интерфейс - вершина иерархии для взаимодействия с таблицами
public interface CRUDRepository<K, T> {

    int DEFAULT_FIND_ALL_LIMIT = 10;

    int DEFAULT_FIND_ALL_OFFSET = 0;

    // Операции READ
    T findById(K id);

    Optional<T> findOne(K id);

    List<T> findAll();

    List<T> findAll(int limit, int offset);

    // Операции CREATE

    T create (T object);

    // Операции UPDATE

    T update (T object);

    // Операции DELETE

    K delete (K id);

}