package com.safetynet.sfalert.repository;

import java.util.ArrayList;
import java.util.List;

import com.safetynet.sfalert.model.Entity;
import com.safetynet.sfalert.model.Json;
import com.safetynet.sfalert.model.Person;

public class EntityProxy {

  JsonProxy jsonProxy = new JsonProxy();
  
  public List<Entity> getEntities(){
    Json dataJson = jsonProxy.retrieveData();
    FireStationProxy fireStationProxy = new FireStationProxy();
    MedicalRecordProxy medicalRecordProxy = new MedicalRecordProxy();
    List<Entity> entities = new ArrayList<Entity>();
    for(Person p : dataJson.getPersons()) {
      entities.add(new Entity(p, fireStationProxy.getFirestationFromAddress(p.getAddress()), medicalRecordProxy.getMedicalrecord(p.getFirstName(), p.getLastName())));
    };
    return entities;
  }
  
}
