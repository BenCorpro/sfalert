package com.safetynet.sfalert.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.sfalert.model.Json;
import com.safetynet.sfalert.model.Person;
import com.safetynet.sfalert.service.IPersonService;


@Service
public class PersonService implements IPersonService {

  @Autowired
  private Json json;
  
  private static Logger logger = LoggerFactory.getLogger(PersonService.class);

  
  @Override 
  public List<String> getEmailList(String city){
    List<Person> persons = json.getPersons();
    List<String> emails = new ArrayList<String>();
    logger.debug("Searching emails of people living in " + city);
    for(Person p : persons) {
      if(p.getCity().equals(city)) {
        emails.add(p.getEmail());
      }
    }
    if(emails.isEmpty()) {
      logger.error("Invalid city: " + city);
      return null;
    }
    logger.info(emails.size() + " emails found in the city of " + city);
    return emails;
  }
  
  
  @Override 
  public Person savePerson(Person person){
    List<Person> persons = json.getPersons();
    logger.debug("Looking for " + person.getFirstName() + " " + person.getLastName() + " in data");
    for(Person p : persons) {
      if(p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName())) {
        logger.error(person.getFirstName() + " " + person.getLastName() + " already in data");
        return null;
      } 
    }
    persons.add(person);
    json.setPersons(persons);
    logger.info("New person " + person.getFirstName() + " " + person.getLastName() + " created");
    return person;
  }
  
  @Override 
  public Person updatePerson(Person person) {
    List<Person> persons = json.getPersons();
    logger.debug("Looking for " + person.getFirstName() + " " + person.getLastName() + " in data");
    for(Person p : persons) {
      if(p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName())) {
        p.setAddress(person.getAddress());
        p.setCity(person.getCity());
        p.setZip(person.getZip());
        p.setPhone(person.getPhone());
        p.setEmail(person.getEmail()); 
        json.setPersons(persons);
        logger.info(person.getFirstName() + " " + person.getLastName() + " updated");
        return person;
      } 
    }
    logger.error(person.getFirstName() + " " + person.getLastName() + " not found in data");
    return null;
  }
  
  @Override 
  public boolean deletePerson(String firstName, String lastName) {
    List<Person> persons = json.getPersons();
    ListIterator<Person> personsIter = persons.listIterator();
    logger.debug("Looking for " + firstName + " " + lastName + " in data");
    while(personsIter.hasNext()) {
      Person p = personsIter.next();
      if(p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)) {
        personsIter.remove();
        json.setPersons(persons);
        logger.info(firstName + " " + lastName + " deleted");
        return true;
      }
    }
    logger.error(firstName + " " + lastName + " not found in data");
    return false;
  }
  
  
}
