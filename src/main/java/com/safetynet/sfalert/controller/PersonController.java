package com.safetynet.sfalert.controller;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.safetynet.sfalert.model.Person;
import com.safetynet.sfalert.service.IPersonService;


@RestController
public class PersonController {

  @Autowired
  private IPersonService personService;
  
  
  @GetMapping("/communityEmail")
  public ResponseEntity<List<String>> eMail(@RequestParam("city") String city){
    List<String> eMails = personService.getEmailList(city);
    if(Objects.isNull(eMails)) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(eMails);
    }
  }
  
  
  @PostMapping("/person")
  public ResponseEntity<Person> addPerson(@RequestBody Person person){
    Person addedPerson = personService.savePerson(person);
    if(Objects.isNull(addedPerson)) {
      return ResponseEntity.noContent().build();
    } else {
      URI location = ServletUriComponentsBuilder.fromCurrentRequest()
          .path("/{firstName}&{lastName}")
          .buildAndExpand(addedPerson.getFirstName(), addedPerson.getLastName())
          .toUri();
      return ResponseEntity.created(location).build(); 
    }    
  }
  
  @PutMapping("/person")
  public ResponseEntity<Person> updatePerson(@RequestBody Person person) {
    Person updatedPerson = personService.updatePerson(person);
    if(Objects.isNull(updatedPerson)) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(updatedPerson);
    }
  }
  
  @DeleteMapping("/person")
  public ResponseEntity<Boolean> deletePerson(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
    boolean personDeleted = personService.deletePerson(firstName, lastName);   
    if(!personDeleted) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok().body(personDeleted);
    }
  }
  
  
}
