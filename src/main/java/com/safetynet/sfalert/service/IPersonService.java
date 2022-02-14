package com.safetynet.sfalert.service;

import java.util.List;

import com.safetynet.sfalert.model.Person;


/**
 * The Interface IPersonService, groups the service methods requiring person entity.
 */
public interface IPersonService {

  /**
   * Gets the list of email of all person's living in the city.
   *
   * @param city the city
   * @return the list of email of all person's living in the city
   */
  public List<String> getEmailList(String city);
  
  /**
   * Save a new person.
   *
   * @param person the person
   * @return the person saved, if successful, null if not 
   */
  public Person savePerson(Person person);

  /**
   * Update a person.
   *
   * @param person the person
   * @return the person updated, if successful, null if not
   */
  public Person updatePerson(Person person);

  /**
   * Delete person.
   *
   * @param firstName the person's first name
   * @param lastName the person's last name
   * @return true, if successful
   */
  public boolean deletePerson(String firstName, String lastName);

  
}