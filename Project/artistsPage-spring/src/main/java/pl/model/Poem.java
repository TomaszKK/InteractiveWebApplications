package pl.model;

<<<<<<< Updated upstream
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

=======
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
>>>>>>> Stashed changes

@Entity
public class Poem {
    @Id
    @GeneratedValue
    private long id;
    private String title;
    private String text;
    private String genre;
    private Date CreationDate;
    private double rating;
    private int numberOfRatings;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToMany(mappedBy = "likedPoems", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonBackReference
    private List<Visitor> visitors;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text){
        this.text = text;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre){
        this.genre = genre;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating){
        this.rating = rating;
    }

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(int numberOfRatings){
        this.numberOfRatings = numberOfRatings;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Date getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(Date CreationDate) {
        this.CreationDate = CreationDate;
    }

    public List<Visitor> getVisitors() {
        return visitors;
    }

    public void setVisitors(List<Visitor> visitors) {
        this.visitors = visitors;
    }

    public void addVisitor(Visitor visitor) {
        this.visitors.add(visitor);
    }

    public void removeVisitor(Visitor visitor) {
        this.visitors.remove(visitor);
    }


}
