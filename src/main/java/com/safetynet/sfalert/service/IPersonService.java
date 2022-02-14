package com.safetynet.sfalert.service;

import java.util.List;

import com.safetynet.sfalert.model.Person;


public interface IPersonService {

  public List<String> getEmailList(String city);
  
  public Person savePerson(Person person);

  public Person updatePerson(Person person);

  public boolean deletePerson(String firstName, String lastName);

  
}