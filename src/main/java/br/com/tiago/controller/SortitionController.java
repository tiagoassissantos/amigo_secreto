package br.com.tiago.controller;


import br.com.tiago.data.entities.Person;
import br.com.tiago.data.repositories.PersonRepository;
import br.com.tiago.vo.PersonVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping( value = "/sortition" )
public class SortitionController {

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping( value = "/", method = RequestMethod.GET )
    public List<PersonVo> execute() {

        List<Person> persons = personRepository.findAll();

        if ( persons.size() < 2 ) {
            return transformInPersonVo(persons);
        }

        raffle( persons );

        return transformInPersonVo( persons );
    }


    protected List<Person> raffle( List<Person> persons ) {
        List<Person> raffled = new ArrayList<Person>();

        Queue<Person> aSortear = montaListaASortear( new ArrayList<Person>(persons) );

        for (Person person : persons) {
            Person sorteado = aSortear.poll();

            if ( sorteado.getId().equals( person.getId() ) ) {
                System.out.println("Uma pessoa não pode tirar ela mesma");
                aSortear.offer( sorteado );
                sorteado = aSortear.poll();
            }

            person.setFriend( sorteado );

            personRepository.save( person );
        }

        for ( Person person : persons ) {
            if ( person.getId().equals(person.getFriend().getId()) ) {
                System.out.println("-------- Deve ser sorteado novamente");
                persons = raffle( persons );
            }
        }

        return persons;
    }

    protected Queue<Person> montaListaASortear( List<Person> persons ) {
        Collections.shuffle( persons );

        Queue<Person> aSortear = new LinkedList<Person>();

        for (Person person : persons) {
            aSortear.add(person);
        }

        return aSortear;
    }

    protected List<PersonVo> transformInPersonVo( List<Person> persons ) {
        List<PersonVo> personsVo = new ArrayList<PersonVo>();

        for (Person person : persons) {
            PersonVo personVo = new PersonVo();
            personVo.setId(person.getId());
            personVo.setName(person.getName());
            personVo.setEmail(person.getEmail());

            if ( person.getFriend() != null ) {
                personVo.setFriendId( person.getFriend().getId() );
                personVo.setFriendName( person.getFriend().getName() );
                personVo.setFriendEmail( person.getFriend().getEmail() );
            }

            personsVo.add( personVo );
        }

        return personsVo;
    }
}
