package pl.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;


@Entity
public class Poem {
    @Id
    @GeneratedValue
    private long id;
    private String title;
    @Column(columnDefinition = "VARCHAR(10000)")
    private String text;
    private String genre;
    private Date CreationDate;
    @Column(name = "is_public")
    private boolean isPublic;
    private double rating; //rating/numberOfRatings wystarczy
    private int numberOfRatings; //mozan zwieszac na frontend


    @ManyToOne
    @JoinColumn(name = "artist_id")
    @JsonBackReference
    private Artist artist;

    @ManyToMany(mappedBy = "likedPoems")
    @JsonIgnoreProperties("likedPoems")
    private List<Visitor> likedVisitors;

    public Poem() {
    }

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

    public String getArtistName() {
        return artist.getName();
    }
    public String getArtistSecondName() {
        return artist.getSecondName();
    }

    public Date getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(Date CreationDate) {
        this.CreationDate = CreationDate;
    }

    public List<Visitor> getLikedVisitors() {
        return likedVisitors;
    }

    public void setLikedVisitors(List<Visitor> likedVisitors) {
        this.likedVisitors = likedVisitors;
    }


    public boolean isPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }
}
