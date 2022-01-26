package com.safetynet.sfalert.service.impl;

import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.sfalert.model.Json;
import com.safetynet.sfalert.model.Person;
import com.safetynet.sfalert.service.IPersonService;

@Service
public class PersonService implements IPersonService {

  @Autowired
  Json json;

  @Override 
  public Person savePerson(Person person){
    List<Person> persons = json.getPersons();
    for(Person p : persons) {
      if(p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName())) {
        return null;
      } 
    }
    persons.add(person);
    json.setPersons(persons);
    return person;
  }
  
  @Override 
  public Person updatePerson(Person person) {
    List<Person> persons = json.getPersons();
    for(Person p : persons) {
      if(p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName())) {
        p.setAddress(person.getAddress());
        p.setCity(person.getCity());
        p.setZip(person.getZip());
        p.setPhone(person.getPhone());
        p.setEmail(person.getEmail()); 
        json.setPersons(persons);
        return person;
      } 
    }
    return null;
  }
  
  @Override 
  public boolean deletePerson(String firstName, String lastName) {
    List<Person> persons = json.getPersons();
    ListIterator<Person> personsIter = persons.listIterator();
    while(personsIter.hasNext()) {
      Person p = personsIter.next();
      if(p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)) {
        personsIter.remove();
        json.setPersons(persons);
        return true;
      }
    }
    return false;
  }
}
