package com.helloworld.controller;

import com.helloworld.model.Address;
import com.helloworld.model.Student;
import com.helloworld.respository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/address")
public class AddressRESTController {
    private AddressRepository addressRepository;

    @Autowired
    public AddressRESTController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @GetMapping
    public List<Address> findAllAddresses() {
        return addressRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Address findAddress(@PathVariable("id") long id) {
        return addressRepository.findById(id);
    }

    @PostMapping
    public ResponseEntity<Address> addAddress(@RequestBody Address address) {
        addressRepository.save(address);
        return new ResponseEntity<Address>(address, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Address> deleteAll() {
        addressRepository.deleteAll();
        return new ResponseEntity<Address>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Address> deleteAddress(@PathVariable("id") long id) {
        Address address = addressRepository.findById(id);
        if (address == null) {
            System.out.println("Address not found");
            return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
        }
        addressRepository.deleteById(id);
        return new ResponseEntity<Address>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Address> updateAll(@RequestBody List<Address> addresses) {
        addressRepository.deleteAll();
        addressRepository.saveAll(addresses);
        return new ResponseEntity<Address>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Address> updatePartOfAddress(@RequestBody Map<String, Object> updates, @PathVariable("id") long id) {
        Address address = addressRepository.findById(id);
        if (address == null) {
            System.out.println("Address not found.");
            return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
        }
        partialUpdate(address, updates);
        return new ResponseEntity<Address>(HttpStatus.NO_CONTENT);
    }

    private void partialUpdate(Address address, Map<String, Object> updates) {
        if (updates.containsKey("city")) {
            address.setCity((String) updates.get("city"));
        }
        if (updates.containsKey("street")) {
            address.setStreet((String) updates.get("street"));
        }
        if (updates.containsKey("number")) {
            address.setNumber((String) updates.get("number"));
        }
        if (updates.containsKey("postalCode")) {
            address.setPostalCode((String) updates.get("postalCode"));
        }
        if (updates.containsKey("studentList")) {
            address.setStudentList((List<Student>) updates.get("studentList"));
        }


        addressRepository.save(address);
    }

}
