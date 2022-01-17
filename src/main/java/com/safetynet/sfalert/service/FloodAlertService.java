package com.safetynet.sfalert.service;

import java.text.ParseException;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import com.safetynet.sfalert.model.Entity;
import com.safetynet.sfalert.model.FireAlert;
import com.safetynet.sfalert.repository.EntityProxy;
import com.safetynet.sfalert.util.AgeCalculator;

public class FloodAlertService {

  EntityProxy entityProxy = new EntityProxy();
  
  public List<FireAlert> floodListStation(String station) throws ParseException{
    List<Entity> entities = entityProxy.getEntities();
    List<FireAlert> floodAlerts = new ArrayList<FireAlert>();
    for(Entity eT : entities ) {
      if(eT.getFireStation().getStation().equals(station)) {
        Period etAge = AgeCalculator.ageCalculator(eT.getMedicalRecord().getBirthdate());
        floodAlerts.add(new FireAlert(eT.getPerson().getFirstName(),
                                      eT.getPerson().getLastName(),
                                      eT.getPerson().getPhone(),
                                      etAge,
                                      eT.getMedicalRecord().getMedications(),
                                      eT.getMedicalRecord().getAllergies()));
      }
    }
    return floodAlerts;
  }
}
