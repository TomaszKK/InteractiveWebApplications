package pl.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Visitor {
    @Id
    @GeneratedValue
    private long id;
    private String name, surname;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonManagedReference
    private List<Poem> likedPoems;

    public Visitor() {
    }

    public Visitor(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

     public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Poem> getLikedPoems() {
        return likedPoems;
    }

    public void setLikedPoems(List<Poem> likedPoems) {
        this.likedPoems = (List<Poem>) likedPoems;
    }

    public void addLikedPoems(Poem likedPoems) {
        this.likedPoems.add(likedPoems);
    }

    public void removeLikedPoems(Poem likedPoems) {
        this.likedPoems.remove(likedPoems);
    }

}
