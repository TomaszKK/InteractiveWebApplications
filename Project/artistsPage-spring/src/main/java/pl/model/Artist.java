package pl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

@Entity
public class Artist {
    @Id
    @GeneratedValue
    private long id;
    private String name, secondName, bio, mediaLinks, location, type;
    private int age;
    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Poem> poems;
<<<<<<< Updated upstream
    /*
    @OneToOne(cascade = CascadeType.ALL)
    private Account account;
*/
=======

    /*
    @OneToOne(cascade = CascadeType.ALL)
    private Account account;
     */

>>>>>>> Stashed changes
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age=age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName=secondName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

     public String getMediaLinks() {
        return mediaLinks;
    }

    public void setMediaLinks(String mediaLinks) {
        this.mediaLinks = mediaLinks;
    }

     public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

     public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Poem> getPoems() {
        return poems;
    }

    public void setPoems(List<Poem> poems) {
        this.poems = poems;
    }
<<<<<<< Updated upstream

    public void addPoem(Poem poem) {
        poem.setArtist(this);
        poems.add(poem);
    }

    public void removePoem(Poem poem) {
        poems.remove(poem);
        poem.setArtist(null);
    }

    public void getPoem(Poem poem) {
        poems.get(poems.indexOf(poem));
    }

    public void setPoem(Poem poem) {
        poems.set(poems.indexOf(poem), poem);
    }
=======
>>>>>>> Stashed changes
/*
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
 */
}
