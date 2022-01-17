package com.safetynet.sfalert.service;

import java.util.ArrayList;
import java.util.List;

import com.safetynet.sfalert.model.Entity;
import com.safetynet.sfalert.repository.EntityProxy;

public class EmailService {

  EntityProxy entityProxy = new EntityProxy();
  
  public List<String> getEmailList(String city){
    List<Entity> entities = entityProxy.getEntities();
    List<String> emails = new ArrayList<String>();
    for(Entity eT : entities) {
      if(eT.getPerson().getCity().equals(city)) {
        emails.add(eT.getPerson().getEmail());
      }
    }
    return emails;
  }
}
