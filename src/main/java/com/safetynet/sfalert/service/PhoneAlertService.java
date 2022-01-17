package com.safetynet.sfalert.service;

import java.util.ArrayList;
import java.util.List;

import com.safetynet.sfalert.model.Entity;
import com.safetynet.sfalert.repository.EntityProxy;

public class PhoneAlertService {
  
  EntityProxy entityProxy = new EntityProxy();
  
  public List<String> phoneNumberList(String station){
    List<Entity> entities = entityProxy.getEntities();
    List<String> phoneNumbers = new ArrayList<String>();
    for(Entity eT : entities) {
      if(eT.getFireStation().getStation().equals(station)) {
        phoneNumbers.add(eT.getPerson().getPhone());
      }
    }
    return phoneNumbers;
  }
}
