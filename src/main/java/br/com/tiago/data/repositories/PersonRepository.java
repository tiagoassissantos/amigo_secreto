package br.com.tiago.data.repositories;


import br.com.tiago.data.BaseRepository;
import br.com.tiago.data.entities.Person;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends BaseRepository<Person, Long> {

}
