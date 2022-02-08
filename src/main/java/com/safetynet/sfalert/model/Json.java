package com.safetynet.sfalert.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.safetynet.sfalert.util.CustomJsonSerializer;

@Component
public class Json {

  private List<Person> persons;
  private List<FireStation> firestations;
  private List<MedicalRecord> medicalrecords;

  @PostConstruct
  public void retrieveData() {
    Json jsonFile = null;
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      File file = new File("./src/main/resources/data.json");
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


  @PreDestroy
  public void saveData(){
    Json jsonFile = new Json();
    jsonFile.setPersons(this.persons);
    jsonFile.setMedicalRecords(this.medicalrecords);
    jsonFile.setFireStations(this.firestations);
    try {
      ObjectMapper mapper = new ObjectMapper();
      File file = new File("/home/tez1kat/Documents/data.json");
      SimpleModule module = new SimpleModule("CustomJsonSerializer",
          new Version(1, 0, 0, null, null, null));
      module.addSerializer(Json.class, new CustomJsonSerializer());
      mapper.registerModule(module);
      mapper.writeValue(file, jsonFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

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
