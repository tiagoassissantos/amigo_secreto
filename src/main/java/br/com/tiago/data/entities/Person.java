package br.com.tiago.data.entities;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table( name = "person" )
public class Person {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    @Column(name = "creation_time", nullable = false)
    private Calendar creationTime;

    @Column(name = "modification_time", nullable = false)
    private Calendar modificationTime;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToOne( fetch = FetchType.LAZY )
    private Person friend;

    public Person() {
        creationTime = Calendar.getInstance();
        modificationTime = Calendar.getInstance();
    }

    public Person( Long id, String name, String email ) {
        creationTime = Calendar.getInstance();
        modificationTime = Calendar.getInstance();
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Calendar creationTime) {
        this.creationTime = creationTime;
    }

    public Calendar getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(Calendar modificationTime) {
        this.modificationTime = modificationTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Person getFriend() {
        return friend;
    }

    public void setFriend(Person friend) {
        this.friend = friend;
    }
}
