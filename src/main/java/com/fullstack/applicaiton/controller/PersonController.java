package com.fullstack.applicaiton.controller;

import com.fullstack.applicaiton.Exception.ResourceNotFoundException;
import com.fullstack.applicaiton.entity.Person;
import com.fullstack.applicaiton.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class PersonController {
    @Autowired
    private PersonRepository personRepository; // to make
    @GetMapping("/persons")
    public List<Person> getAllPersons(){ //by getALl we are expecting to get the list of all the persons
        return personRepository.findAll();
    }
    @PostMapping("/persons")
    public Person addPerson(@RequestBody Person person){
        return personRepository.save(person);
    }
    @GetMapping("/persons/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Integer id) throws ResourceNotFoundException{
        Person response = personRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Person not exists with id:"+id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/persons/{id}")
    public ResponseEntity<Person> updatePersonById(@PathVariable Integer id, @RequestBody Person personDetails) throws ResourceNotFoundException{
        Person existingPerson = personRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Person not exists with id:"+id));
        existingPerson.setFirstName(personDetails.getFirstName());
        existingPerson.setLastName(personDetails.getLastName());
        existingPerson.setEmail(personDetails.getEmail());
        Person updatedPerson = personRepository.save(existingPerson);
        return new ResponseEntity<>(updatedPerson,HttpStatus.OK);
    }
    @DeleteMapping("/persons/{id}")
    public String deletePersonById(@PathVariable Integer id)   {

        personRepository.deleteById(id);
        return "Person record deleted with id :"+id;
    }
}
