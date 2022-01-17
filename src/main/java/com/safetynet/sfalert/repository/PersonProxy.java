package com.safetynet.sfalert.repository;

import java.util.ArrayList;
import java.util.List;

import com.safetynet.sfalert.model.Json;
import com.safetynet.sfalert.model.Person;

public class PersonProxy {
  JsonProxy jsonProxy = new JsonProxy();
  
  public List<Person> getPersons() {
    Json dataJson = jsonProxy.retrieveData();
    return dataJson.getPersons();
  }
  
  public Person getPerson(String firstName, String lastName) {
    Json dataJson = jsonProxy.retrieveData();
    List<Person> persons = dataJson.getPersons();
    Person person = null;
    for(Person p : persons) {
      if(p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)) {
        person = p;
      }
    }
    return person;
  }
  
  public List<Person> getPersons(String address) {
    Json dataJson = jsonProxy.retrieveData();
    List<Person> persons = dataJson.getPersons();
    List<Person> result = new ArrayList<Person>();
    for(Person p : persons) {
      if(p.getAddress().equals(address)) {
        result.add(p);
      }
    }
    return result;
  }

}
