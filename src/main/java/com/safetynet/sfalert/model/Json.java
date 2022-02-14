package com.safetynet.sfalert.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.safetynet.sfalert.util.CustomJsonSerializer;


/**
 * The Json Class, represents the data structure.
 */
@Component
public class Json {

  /** The list of persons. */
  private List<Person> persons;
  
  /** The list of firestations. */
  private List<FireStation> firestations;
  
  /** The list of medicalrecords. */
  private List<MedicalRecord> medicalrecords;
  
  /** The json data file. */
  @Value("${data.path}")
  private File file;

  
  /**
   * Retrieve data from json file, instantiate entities, and link them.
   */
  @PostConstruct
  public void retrieveData() {
    Json jsonFile = null;
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      jsonFile = objectMapper.readValue(file, Json.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    for (Person p : jsonFile.getPersons()) {
      for (MedicalRecord m : jsonFile.getMedicalrecords()) {
        if (p.getFirstName().equals(m.getFirstName())
            && p.getLastName().equals(m.getLastName())) {
          p.setMedicalRecord(m);
        }
      }
    }
    for (FireStation f : jsonFile.getFirestations()) {
      List<Person> persons = new ArrayList<Person>();
      for (Person p : jsonFile.getPersons()) {
        if (f.getAddress().equals(p.getAddress())) {
          persons.add(p); 
        }  
      }
      f.setPersons(persons);
    }
    this.persons = jsonFile.getPersons();
    this.medicalrecords = jsonFile.getMedicalrecords();
    this.firestations = jsonFile.getFirestations();
  }

  /**
   * Save data to json file, with the original structure.
   */
  @PreDestroy
  public void saveData(){
    Json jsonFile = new Json();
    jsonFile.setPersons(this.persons);
    jsonFile.setMedicalRecords(this.medicalrecords);
    jsonFile.setFireStations(this.firestations);
    try {
      ObjectMapper mapper = new ObjectMapper();
      SimpleModule module = new SimpleModule("CustomJsonSerializer",
          new Version(1, 0, 0, null, null, null));
      module.addSerializer(Json.class, new CustomJsonSerializer());
      mapper.registerModule(module);
      mapper.writeValue(file, jsonFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  
  /**
   * Gets the persons.
   *
   * @return the list of persons
   */
  public List<Person> getPersons() {
    return persons;
  }

  /**
   * Sets the persons.
   *
   * @param persons the new list of persons
   */
  public void setPersons(List<Person> persons) {
    this.persons = persons;
  }

  /**
   * Gets the firestations.
   *
   * @return the list of firestations
   */
  public List<FireStation> getFirestations() {
    return firestations;
  }

  /**
   * Sets the fire stations.
   *
   * @param fireStations the new list of fire stations
   */
  public void setFireStations(List<FireStation> fireStations) {
    this.firestations = fireStations;
  }

  /**
   * Gets the medicalrecords.
   *
   * @return the list of medicalrecords
   */
  public List<MedicalRecord> getMedicalrecords() {
    return medicalrecords;
  }

  /**
   * Sets the medical records.
   *
   * @param medicalRecords the new list of medical records
   */
  public void setMedicalRecords(List<MedicalRecord> medicalRecords) {
    this.medicalrecords = medicalRecords;
  }

  
}
