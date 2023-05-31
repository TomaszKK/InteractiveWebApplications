package pl.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.model.Poem;
import pl.model.Visitor;
import pl.repository.PoemRepository;
import pl.repository.VisitorRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/visitor")
public class VisitorController {
    private VisitorRepository visitorRepository;
    private PoemRepository poemRepository;

    @Autowired
    public VisitorController(VisitorRepository visitorRepository, PoemRepository poemRepository) {
        this.visitorRepository = visitorRepository;
        this.poemRepository = poemRepository;
    }

    @GetMapping
    public List<Visitor> findAllVisitors() {
        return visitorRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Visitor findVisitor(@PathVariable("id") long id) {
        return visitorRepository.findById(id);
    }

    @GetMapping(value = "/{id}/poems")
    public List<Poem> findVisitorPoems(@PathVariable("id") long id) {
        Visitor visitor = visitorRepository.findById(id);
        if (visitor == null) {
            System.out.println("Visitor not found");
            return null;
        }
        return visitor.getLikedPoems();
    }

    @GetMapping(value = "/{id}/poems/{poemId}")
    public Poem findVisitorPoem(@PathVariable("id") long id, @PathVariable("poemId") long poemId) {
        Visitor visitor = visitorRepository.findById(id);
        if (visitor == null) {
            System.out.println("Visitor not found");
            return null;
        }
        Poem poem = poemRepository.findById(poemId);
        if (poem == null) {
            System.out.println("Poem not found");
            return null;
        }
        return poem;
    }


    @PostMapping
    public ResponseEntity<Visitor> addVisitor(@RequestBody Visitor visitor) {
        visitorRepository.save(visitor);
        return ResponseEntity.status(HttpStatus.CREATED).body(visitor);
    }

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

    @DeleteMapping(value = "/{id}/poems/{poemId}")
    public ResponseEntity<Visitor> deleteVisitorPoem(@PathVariable("id") long id, @PathVariable("poemId") long poemId) {
        Visitor visitor = visitorRepository.findById(id);
        if (visitor == null) {
            System.out.println("Visitor not found");
            return new ResponseEntity<Visitor>(HttpStatus.NOT_FOUND);
        }
        Poem poem = poemRepository.findById(poemId);
        if (poem == null) {
            System.out.println("Poem not found");
            return new ResponseEntity<Visitor>(HttpStatus.NOT_FOUND);
        }
        visitor.getLikedPoems().remove(poem);
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

    @PutMapping(value = "/{id}/poems/{poemId}")
    public ResponseEntity<Visitor> addPoemToVisitor(@PathVariable("id") long id, @PathVariable("poemId") long poemId) {
        Visitor visitor = visitorRepository.findById(id);
        Poem poem = poemRepository.findById(poemId);
        if (visitor == null) {
            System.out.println("Visitor not found");
            return new ResponseEntity<Visitor>(HttpStatus.NOT_FOUND);
        }
        if (poem == null) {
            System.out.println("Poem not found");
            return new ResponseEntity<Visitor>(HttpStatus.NOT_FOUND);
        }
        visitor.addLikedPoem(poem);
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
        return new ResponseEntity<Visitor>(visitor1, HttpStatus.NO_CONTENT);
    }
}
