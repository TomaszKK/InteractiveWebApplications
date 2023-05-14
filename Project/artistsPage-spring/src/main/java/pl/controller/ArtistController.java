package pl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.repository.AccountRepository;
import pl.repository.ArtistRepository;
import pl.model.Artist;
import pl.model.Account;

import java.util.List;

@RestController
@RequestMapping("/artist")
public class ArtistController {
    private ArtistRepository artistRepository;
    private AccountRepository accountRepository;

    @Autowired
    public ArtistController(ArtistRepository artistRepository, AccountRepository accountRepository) {
        this.artistRepository = artistRepository;
        this.accountRepository = accountRepository;
    }

    @GetMapping
    public List<Artist> findAllArtists() {
        return artistRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Artist findArtist(@PathVariable("id") long id) {
        return artistRepository.findById(id);
    }

    @PostMapping
    public Artist addArtist(@RequestBody Artist artist) {
        artistRepository.save(artist);
        return artist;
    }

    @DeleteMapping
    public void deleteAll() {
        artistRepository.deleteAll();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteArtist(@PathVariable("id") long id) {
        artistRepository.deleteById(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Artist> updateArtist(@PathVariable("id") long id, @RequestBody Artist artist) {
        artist.setId(id);
        artistRepository.save(artist);
        return new ResponseEntity<Artist>(artist, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Artist> updateAll(@RequestBody List<Artist> artists) {
        artistRepository.deleteAll();
        artistRepository.saveAll(artists);
        return new ResponseEntity<Artist>(HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Artist> updateArtistPartially(@PathVariable("id") long id, @RequestBody Artist updatedArtist) {
        Artist currentartist= artistRepository.findById(id);
        if (currentartist == null) {
            System.out.println("Team not found");
            return ResponseEntity.notFound().build();
        }
        if (updatedArtist.getAge() != 0) {
            currentartist.setAge(updatedArtist.getAge());
        }
        if (updatedArtist.getName() != null) {
            currentartist.setName(updatedArtist.getName());
        }
        if (updatedArtist.getSecondName() != null) {
            currentartist.setSecondName(updatedArtist.getSecondName());
        }
        if (updatedArtist.getBio() != null) {
            currentartist.setBio(updatedArtist.getBio());
        }
        if (updatedArtist.getMediaLinks() != null) {
            currentartist.setMediaLinks(updatedArtist.getMediaLinks());
        }
        if (updatedArtist.getLocation() != null) {
            currentartist.setLocation(updatedArtist.getLocation());
        }
        if (updatedArtist.getType() != null) {
            currentartist.setType(updatedArtist.getType());
        }
        artistRepository.save(currentartist);
        return ResponseEntity.ok(currentartist);
    }
}
