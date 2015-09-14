package br.com.tiago.controller;

import br.com.tiago.data.entities.Person;
import br.com.tiago.data.repositories.PersonRepository;
import br.com.tiago.vo.PersonVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping( value = "/person" )
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping( value = "/", method = RequestMethod.POST )
    public Person save( @ModelAttribute("person") Person person) {
        personRepository.save( person );

        return person;
    }

    @RequestMapping( value = "/", method = RequestMethod.GET )
    public @ResponseBody List<PersonVo> list() {
        List<Person> persons = personRepository.findAll();

        return transformInPersonVo( persons );
    }

    protected List<PersonVo> transformInPersonVo( List<Person> persons ) {
        List<PersonVo> personsVo = new ArrayList<PersonVo>();

        for (Person person : persons) {
            PersonVo personVo = new PersonVo();
            personVo.setId(person.getId());
            personVo.setName(person.getName());
            personVo.setEmail(person.getEmail());

            personsVo.add( personVo );
        }

        return personsVo;
    }

}
