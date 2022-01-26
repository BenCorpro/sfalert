package com.safetynet.sfalert.service;

import com.safetynet.sfalert.model.Person;

public interface IPersonService {

  public Person savePerson(Person person);

  public Person updatePerson(Person person);

  public boolean deletePerson(String firstName, String lastName);

}