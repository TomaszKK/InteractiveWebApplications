package pl.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.model.Artist;
import pl.model.User;
import pl.repository.ArtistRepository;
import pl.repository.PoemRepository;
import pl.model.Poem;
import pl.repository.UserRepository;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/poem")
public class PoemController {
    private PoemRepository poemRepository;
    private UserRepository userRepository;
    private ArtistRepository artistRepository;

    @Autowired
    public PoemController(PoemRepository poemRepository, UserRepository userRepository, ArtistRepository artistRepository) {
        this.poemRepository = poemRepository;
        this.artistRepository = artistRepository;
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/getArtistPoems")
    @PreAuthorize("hasRole('ARTIST')")
    public List<Poem> findArtistPoems(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(()-> new RuntimeException("Error: User not found"));
        Artist artist = artistRepository.findByUser(user);
        return artist.getPoems();
    }

    @GetMapping()
    public List<Poem> findAllPoems() {
        return poemRepository.findAll();
    }


    @PostMapping("/addPoem")
    @PreAuthorize("hasRole('ARTIST')")
    public ResponseEntity<Poem> addPoem(@Valid @RequestBody Poem poem, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(()-> new RuntimeException("Error: User not found"));
        Artist artist = artistRepository.findByUser(user);

        poem.setArtist(artist);

        //artistRepository.save(artist);
        poemRepository.save(poem);
        return new ResponseEntity<Poem>(poem, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Poem> deleteAll() {
        poemRepository.deleteAll();
        return new ResponseEntity<Poem>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Poem> deletePoem(@PathVariable("id") long id) {
        Poem poem = poemRepository.findById(id);
        if (poem == null) {
            System.out.println("Poem not found");
            return new ResponseEntity<Poem>(HttpStatus.NOT_FOUND);
        }
        poemRepository.deleteById(id);
        return new ResponseEntity<Poem>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Poem> updatePoem(@PathVariable("id") long id, @RequestBody Poem poem) {
        poem.setId(id);
        poemRepository.save(poem);
        return new ResponseEntity<Poem>(poem, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Poem> updateAll(@RequestBody List<Poem> poems) {
        poemRepository.deleteAll();
        poemRepository.saveAll(poems);
        return new ResponseEntity<Poem>(HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Poem> updatePoemPartially(@PathVariable("id") long id, @RequestBody Poem poem) {
        Poem currentPoem = poemRepository.findById(id);
        if (currentPoem == null) {
            System.out.println("Poem not found");
            return new ResponseEntity<Poem>(HttpStatus.NOT_FOUND);
        }
        if (poem.getTitle() != null && !poem.getTitle().isEmpty()) {
            currentPoem.setTitle(poem.getTitle());
        }
        if (poem.getText() != null && !poem.getText().isEmpty()) {
            currentPoem.setText(poem.getText());
        }
        if (poem.getGenre() != null && !poem.getGenre().isEmpty()) {
            currentPoem.setGenre(poem.getGenre());
        }
        if (poem.getRating() != 0) {
            currentPoem.setRating(poem.getRating());
        }
        if (poem.getNumberOfRatings() != 0) {
            currentPoem.setNumberOfRatings(poem.getNumberOfRatings());
        }
        poemRepository.save(currentPoem);
        return new ResponseEntity<Poem>(currentPoem, HttpStatus.OK);
    }
}
