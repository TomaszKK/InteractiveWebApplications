package pl.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;


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

}
