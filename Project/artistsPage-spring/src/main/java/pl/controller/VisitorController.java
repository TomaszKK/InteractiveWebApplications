package pl.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.model.Poem;
import pl.model.Visitor;
import pl.repository.PoemRepository;
import pl.repository.VisitorRepository;

import java.util.List;

@RestController
@RequestMapping("/visitor")
public class VisitorController {
    private VisitorRepository visitorRepository;
    private PoemRepository poemRepository;

    public VisitorController(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    @GetMapping
    public List<Visitor> findAllVisitors() {
        return visitorRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Visitor findVisitor(@PathVariable("id") long id) {
        return visitorRepository.findById(id);
    }

    @GetMapping(value = "/{id}/likedPoems")
    public List<Poem> findLikedPoems(@PathVariable("id") long id) {
        return visitorRepository.findById(id).getLikedPoems();
    }

    @GetMapping(value = "/{id}/likedPoems/{poemId}")
    public Poem findLikedPoem(@PathVariable("id") long id, @PathVariable("poemId") long poemId) {
        return visitorRepository.findById(id).getLikedPoems().stream().filter(poem -> poem.getId() == poemId).findFirst().orElse(null);
    }

    @PostMapping
    public ResponseEntity<Visitor> addVisitor(@RequestBody Visitor visitor) {
        visitorRepository.save(visitor);
        return ResponseEntity.status(HttpStatus.CREATED).body(visitor);
    }
/*
    @PostMapping(value = "/{id}/likedPoems")
    public ResponseEntity<Visitor> addLikedPoem(@PathVariable("id") long id, @RequestBody Poem poem) {
        Visitor visitor = visitorRepository.findById(id);
        if (visitor == null) {
            System.out.println("Visitor not found");
            return new ResponseEntity<Visitor>(HttpStatus.NOT_FOUND);
        }
        visitor.getLikedPoems().add(poem);
        visitorRepository.save(visitor);
        return ResponseEntity.status(HttpStatus.CREATED).body(visitor);
    }
 */

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Visitor> deleteVisitor(@PathVariable("id") long id) {
        Visitor visitor = visitorRepository.findById(id);
        if (visitor == null) {
            System.out.println("Visitor not found");
            return new ResponseEntity<Visitor>(HttpStatus.NOT_FOUND);
        }
        visitorRepository.delete(visitor);
        return new ResponseEntity<Visitor>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}/likedPoems/{poemId}")
    public ResponseEntity<Visitor> deleteLikedPoem(@PathVariable("id") long id, @PathVariable("poemId") long poemId) {
        Visitor visitor = visitorRepository.findById(id);
        if (visitor == null) {
            System.out.println("Visitor not found");
            return new ResponseEntity<Visitor>(HttpStatus.NOT_FOUND);
        }
        visitor.getLikedPoems().removeIf(poem -> poem.getId() == poemId);
        visitorRepository.save(visitor);
        return new ResponseEntity<Visitor>(HttpStatus.NO_CONTENT);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Visitor> updateVisitor(@PathVariable("id") long id, @RequestBody Visitor visitor) {
        Visitor visitor1 = visitorRepository.findById(id);
        if (visitor1 == null) {
            System.out.println("Visitor not found");
            return new ResponseEntity<Visitor>(HttpStatus.NOT_FOUND);
        }
        visitor1.setName(visitor.getName());
        visitor1.setSurname(visitor.getSurname());
        visitorRepository.save(visitor1);
        return new ResponseEntity<Visitor>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{id}/likedPoems/{poemId}")
    public ResponseEntity<Visitor> updateLikedPoem(@PathVariable("id") long id, @PathVariable("poemId") long poemId, @RequestBody Poem poem) {
        Visitor visitor = visitorRepository.findById(id);
        if (visitor == null) {
            System.out.println("Visitor not found");
            return new ResponseEntity<Visitor>(HttpStatus.NOT_FOUND);
        }
        visitor.getLikedPoems().removeIf(poem1 -> poem1.getId() == poemId);
        visitor.getLikedPoems().add(poem);
        visitorRepository.save(visitor);
        return new ResponseEntity<Visitor>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Visitor> patchVisitor(@PathVariable("id") long id, @RequestBody Visitor visitor) {
        Visitor visitor1 = visitorRepository.findById(id);
        if (visitor1 == null) {
            System.out.println("Visitor not found");
            return new ResponseEntity<Visitor>(HttpStatus.NOT_FOUND);
        }
        if (visitor.getName() != null) {
            visitor1.setName(visitor.getName());
        }
        if (visitor.getSurname() != null) {
            visitor1.setSurname(visitor.getSurname());
        }
        visitorRepository.save(visitor1);
        return new ResponseEntity<Visitor>(HttpStatus.NO_CONTENT);
    }
}
