package com.safetynet.sfalert.service.impl;

import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.sfalert.model.FireAndFloodAlert;
import com.safetynet.sfalert.model.Json;
import com.safetynet.sfalert.model.Person;
import com.safetynet.sfalert.service.IFireAlertService;
import com.safetynet.sfalert.util.AgeCalculator;

@Service
public class FireAlertService implements IFireAlertService {
  
  @Autowired
  Json json;
  
  @Override 
  public Map<String, List<FireAndFloodAlert>> fireListAddress(String address){
    Map<String, List<FireAndFloodAlert>> fireAlert = new HashMap<String, List<FireAndFloodAlert>>();
    List<Person> persons = json.getPersons();
    String station = null;
    List<FireAndFloodAlert> fireAlerts = new ArrayList<FireAndFloodAlert>();
    for(Person p : persons) {
      if(p.getAddress().equals(address)) {
        station = p.getFireStation().getStation();
        Period etAge = AgeCalculator.ageCalculator(p.getMedicalRecord().getBirthdate());
        fireAlerts.add(new FireAndFloodAlert(p.getFirstName(), 
                                     p.getLastName(), 
                                     p.getPhone(), 
                                     etAge.getYears(), 
                                     p.getMedicalRecord().getMedications(),
                                     p.getMedicalRecord().getAllergies()));
      }
    }
    fireAlert.put("station: " + station, fireAlerts);
    return fireAlert;
  }
}
