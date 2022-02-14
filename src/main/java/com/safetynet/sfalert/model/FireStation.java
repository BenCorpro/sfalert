package com.safetynet.sfalert.model;

import java.util.List;


public class FireStation {
  
  private String address;
  private String station;
  private List<Person> persons;
  
  
  public FireStation() {

  }
  
  public FireStation(String address, String station) {
    this.address = address;
    this.station = station;
  }
  
  public FireStation(String address, String station, List<Person> persons) {
    this.address = address;
    this.station = station;
    this.persons = persons;
  }

  
  public String getAddress() {
    return address;
  }
  
  public void setAddress(String address) {
    this.address = address;
  }
  
  public String getStation() {
    return station;
  }
  
  public void setStation(String station) {
    this.station = station;
  }
  
  public List<Person> getPersons() {
    return persons;
  }

  public void setPersons(List<Person> persons) {
    this.persons = persons;
  }
  
  
  @Override
  public String toString() {
    return "FireStation [address=" + address + ", station=" + station + "]";
  }
  
  
}
