package pl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.model.Poem;
import pl.repository.AccountRepository;
import pl.repository.ArtistRepository;
import pl.model.Artist;
import pl.model.Account;

import java.util.List;
import java.util.Optional;

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

    @GetMapping(value = "/{id}/poems")
    public List<Poem> findPoems(@PathVariable("id") long id) {
        return artistRepository.findById(id).getPoems();
    }

    @GetMapping(value = "/{id}/poems/{poemId}")
    public Poem findPoem(@PathVariable("id") long id, @PathVariable("poemId") long poemId) {
        return artistRepository.findById(id).getPoems().stream().filter(poem -> poem.getId() == poemId).findFirst().get();
    }

    @PostMapping
    public Artist addArtist(@RequestBody Artist artist) {
        artistRepository.save(artist);
        return artist;
    }

    @PostMapping(value = "/{id}/poems")
    public ResponseEntity<Poem> addPoem(@PathVariable("id") long id, @RequestBody Poem poem) {
        Artist artist = artistRepository.findById(id);
        if (artist == null) {
            System.out.println("Artist not found");
            return new ResponseEntity<Poem>(HttpStatus.NOT_FOUND);
        }
        artist.addPoem(poem);
        artistRepository.save(artist);
        return new ResponseEntity<Poem>(poem, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity deleteAll() {
        artistRepository.deleteAll();
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteArtist(@PathVariable("id") long id) {
        Artist artist = artistRepository.findById(id);
        if (artist == null) {
            System.out.println("Artist not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        artistRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}/poems/{poemId}")
    public ResponseEntity<Void> deletePoem(@PathVariable("id") long id, @PathVariable("poemId") long poemId) {
        Artist artist = artistRepository.findById(id);
        if (artist == null) {
            System.out.println("Artist not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Optional<Poem> poemOptional = artist.getPoems().stream().filter(p -> p.getId() == poemId).findFirst();
        if (poemOptional.isEmpty()) {
            System.out.println("Poem not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Poem poem = poemOptional.get();
        artist.removePoem(poem); // Use the removePoem method from the Artist entity

        artistRepository.save(artist);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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

    @PatchMapping(value = "/{id}/poems/{poemId}")
    public ResponseEntity<Poem> updatePoemPartially(@PathVariable("id") long id, @PathVariable("poemId") long poemId, @RequestBody Poem updatedPoem) {
        Artist artist = artistRepository.findById(id);
        if (artist == null) {
            System.out.println("Artist not found");
            return ResponseEntity.notFound().build();
        }
        Poem poem = artist.getPoems().stream().filter(p -> p.getId() == poemId).findFirst().get();
        if (updatedPoem.getTitle() != null) {
            poem.setTitle(updatedPoem.getTitle());
        }
        if (updatedPoem.getGenre() != null) {
            poem.setGenre(updatedPoem.getGenre());
        }
        if (updatedPoem.getText() != null) {
            poem.setText(updatedPoem.getText());
        }
        if (updatedPoem.getCreationDate() != null) {
            poem.setCreationDate(updatedPoem.getCreationDate());
        }
        artistRepository.save(artist);
        return ResponseEntity.ok(poem);
    }
}
