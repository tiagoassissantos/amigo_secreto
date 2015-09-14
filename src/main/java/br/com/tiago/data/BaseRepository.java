package br.com.tiago.data;


import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.List;

public interface BaseRepository<T, ID extends Serializable> extends Repository<T, ID> {
    T save(T persisted);

    T findOne(Long id);

    List<T> findAll();

    void delete(T deleted);
}
