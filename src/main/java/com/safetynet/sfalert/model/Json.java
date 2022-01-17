package com.safetynet.sfalert.model;

import java.util.List;

public class Json {
  
  private List<Person> persons;
  private List<FireStation> firestations;
  private List<MedicalRecord> medicalrecords;
  
  
  public List<Person> getPersons() {
    return persons;
  }
  public void setPersons(List<Person> persons) {
    this.persons = persons;
  }
  public List<FireStation> getFirestations() {
    return firestations;
  }
  public void setFireStations(List<FireStation> fireStations) {
    this.firestations = fireStations;
  }
  public List<MedicalRecord> getMedicalrecords() {
    return medicalrecords;
  }
  public void setMedicalRecords(List<MedicalRecord> medicalRecords) {
    this.medicalrecords = medicalRecords;
  }
  
  
}
