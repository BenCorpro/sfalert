package com.safetynet.sfalert.service;

import java.text.ParseException;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import com.safetynet.sfalert.model.Entity;
import com.safetynet.sfalert.model.FireAlert;
import com.safetynet.sfalert.repository.EntityProxy;
import com.safetynet.sfalert.util.AgeCalculator;

public class FireAlertService {
  
  EntityProxy entityProxy = new EntityProxy();
  
  public List<FireAlert> fireListAddress(String address) throws ParseException{
    List<Entity> entities = entityProxy.getEntities();
    List<FireAlert> fireAlerts = new ArrayList<FireAlert>();
    for(Entity eT : entities) {
      if(eT.getPerson().getAddress().equals(address)) {
        Period etAge = AgeCalculator.ageCalculator(eT.getMedicalRecord().getBirthdate());
        fireAlerts.add(new FireAlert(eT.getPerson().getFirstName(), 
                                     eT.getPerson().getLastName(), 
                                     eT.getPerson().getPhone(), 
                                     etAge, 
                                     eT.getMedicalRecord().getMedications(),
                                     eT.getMedicalRecord().getAllergies()));
      }
    }
    return fireAlerts;
  }
}
