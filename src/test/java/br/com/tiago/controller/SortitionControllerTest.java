package br.com.tiago.controller;

import br.com.tiago.data.entities.Person;
import br.com.tiago.data.repositories.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class SortitionControllerTest {
    @Spy
    @InjectMocks
    private SortitionController sortitionController = new SortitionController();

    @Mock
    private PersonRepository personRepository;

    @Before
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        when( personRepository.save( any( Person.class ) ) ).thenReturn( new Person() );
    }

    @Test
    public void deve_retornar_lista_com_amigos_preenchidos() throws Exception {
        List<Person> persons = new ArrayList<Person>();
        persons.add( new Person(1l, "A", "a@b.c") );
        persons.add( new Person(2l, "A", "a@b.c") );
        persons.add( new Person(3l, "A", "a@b.c") );

        List<Person> sorteados = sortitionController.raffle( persons );

        assertNotNull( sorteados.get( 0 ).getFriend() );
    }

    @Test
    public void deve_retornar_lista_com_amigos_preenchidos_sendo_que_uma_pessoa_nao_pode_ser_seu_proprio_amigo() throws Exception {
        List<Person> persons = new ArrayList<Person>();
        persons.add( new Person(1l, "A", "a@b.c") );
        persons.add( new Person(2l, "A", "a@b.c") );
        persons.add( new Person(3l, "A", "a@b.c") );
        persons.add( new Person(4l, "A", "a@b.c") );
        persons.add( new Person(5l, "A", "a@b.c") );
        persons.add( new Person(6l, "A", "a@b.c") );
        persons.add( new Person(7l, "A", "a@b.c") );
        persons.add( new Person(8l, "A", "a@b.c") );
        persons.add( new Person(9l, "A", "a@b.c") );
        persons.add( new Person(10l, "A", "a@b.c") );
        persons.add( new Person(11l, "A", "a@b.c") );
        persons.add( new Person(12l, "A", "a@b.c") );
        persons.add( new Person(13l, "A", "a@b.c") );
        persons.add( new Person(14l, "A", "a@b.c") );

        List<Person> sorteados = sortitionController.raffle( persons );

        for (Person sorteado : sorteados) {
//            System.out.println("sorteado.getId() = " + sorteado.getId());
//            System.out.println("sorteado.getFriend().getId() = " + sorteado.getFriend().getId() + "\n");
            assertNotEquals(sorteado.getName(), sorteado.getFriend());
        }
    }

}