package pl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.repository.PoemRepository;
import pl.model.Poem;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/poem")
public class PoemController {
    private PoemRepository poemRepository;

    @Autowired
    public PoemController(PoemRepository poemRepository) {
        this.poemRepository = poemRepository;
    }

    @GetMapping(value = "/{id}")
    public Poem findPoem(@PathVariable("id") long id) {
        return poemRepository.findById(id);
    }

    @GetMapping()
    public List<Poem> findAllPoems() {
        return poemRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Poem> addPoem(@RequestBody Poem poem) {
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
        if (poem.getTitle() != null) {
            currentPoem.setTitle(poem.getTitle());
        }
        if (poem.getText() != null) {
            currentPoem.setText(poem.getText());
        }
        if (poem.getGenre() != null) {
            currentPoem.setGenre(poem.getGenre());
        }
        if(poem.getRating() != 0) {
            currentPoem.setRating(poem.getRating());
        }
        if(poem.getNumberOfRatings() != 0) {
            currentPoem.setNumberOfRatings(poem.getNumberOfRatings());
        }
        if(poem.getArtist() != null) {
            currentPoem.setArtist(poem.getArtist());
        }
        poemRepository.save(currentPoem);
        return new ResponseEntity<Poem>(currentPoem, HttpStatus.OK);
    }
}
