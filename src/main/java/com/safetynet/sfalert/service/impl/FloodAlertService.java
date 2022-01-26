package com.safetynet.sfalert.service.impl;

import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.sfalert.model.FireAndFloodAlert;
import com.safetynet.sfalert.model.FireStation;
import com.safetynet.sfalert.model.Json;
import com.safetynet.sfalert.model.Person;
import com.safetynet.sfalert.service.IFloodAlertService;
import com.safetynet.sfalert.util.AgeCalculator;

@Service
public class FloodAlertService implements IFloodAlertService {

  @Autowired
  Json json;
  
  @Override 
  public Map<String, List<FireAndFloodAlert>> floodListStation(List<String> station){
    Map<String, List<FireAndFloodAlert>> floodAlert = new HashMap<String, List<FireAndFloodAlert>>();
    List<FireStation> fireStations = json.getFirestations();
    List<String> addresses = new ArrayList<String>();
    for(String s : station) {
      for(FireStation f : fireStations) {
        if(f.getStation().equals(s)) {
          addresses.add(f.getAddress());
        }
      }
    }
    List<Person> persons = json.getPersons();
    for(String a : addresses) {
      List<FireAndFloodAlert> floodAlerts = new ArrayList<FireAndFloodAlert>();
      for(Person p : persons) {
        if(p.getAddress().equals(a)) {
          Period etAge = AgeCalculator.ageCalculator(p.getMedicalRecord().getBirthdate());
          floodAlerts.add(new FireAndFloodAlert(p.getFirstName(),
                                        p.getLastName(),
                                        p.getPhone(),
                                        etAge.getYears(),
                                        p.getMedicalRecord().getMedications(),
                                        p.getMedicalRecord().getAllergies()));
        }
      }
      floodAlert.put(a, floodAlerts);
    }
    return floodAlert;
  }
  
  
}
