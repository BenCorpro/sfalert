package com.safetynet.sfalert.model;

import java.util.List;


/**
 * The FireStation Class, entity.
 */
public class FireStation {
  
  /** address covered. */
  private String address;
  
  /** station number. */
  private String station;
  
  /** list of persons covered by the station address. */
  private List<Person> persons;
  
  
  /**
   * Empty constructor of a new fire station.
   */
  public FireStation() {

  }
  
  /**
   * Entity constructor of a new fire station.
   *
   * @param address the address covered
   * @param station the station number
   */
  public FireStation(String address, String station) {
    this.address = address;
    this.station = station;
  }
  
  /**
   * constructor of a new fire station, with link to list of persons covered.
   *
   * @param address covered
   * @param station number
   * @param persons the persons's list
   */
  public FireStation(String address, String station, List<Person> persons) {
    this.address = address;
    this.station = station;
    this.persons = persons;
  }

  
  /**
   * Gets the address.
   *
   * @return the address
   */
  public String getAddress() {
    return address;
  }
  
  /**
   * Sets the address.
   *
   * @param address the new address
   */
  public void setAddress(String address) {
    this.address = address;
  }
  
  /**
   * Gets the station.
   *
   * @return station the station number
   */
  public String getStation() {
    return station;
  }
  
  /**
   * Sets the station.
   *
   * @param station the new station number
   */
  public void setStation(String station) {
    this.station = station;
  }
  
  /**
   * Gets the persons.
   *
   * @return the list of persons covered
   */
  public List<Person> getPersons() {
    return persons;
  }

  /**
   * Sets the persons.
   *
   * @param persons the new persons's list
   */
  public void setPersons(List<Person> persons) {
    this.persons = persons;
  }
  
  
  /**
   * To string.
   *
   * @return string
   */
  @Override
  public String toString() {
    return "FireStation [address=" + address + ", station=" + station + "]";
  }
  
  
}
