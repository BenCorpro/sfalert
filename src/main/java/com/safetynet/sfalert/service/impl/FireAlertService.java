package com.safetynet.sfalert.service.impl;

import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.sfalert.dto.FireAndFloodAlertDto;
import com.safetynet.sfalert.model.Json;
import com.safetynet.sfalert.model.Person;
import com.safetynet.sfalert.service.IFireAlertService;
import com.safetynet.sfalert.util.AgeCalculator;

@Service
public class FireAlertService implements IFireAlertService {
  
  @Autowired
  private Json json;
  
  @Override 
  public Map<String, List<FireAndFloodAlertDto>> fireListAddress(String address){
    Map<String, List<FireAndFloodAlertDto>> fireAlert = new HashMap<String, List<FireAndFloodAlertDto>>();
    List<Person> persons = json.getPersons();
    String station = null;
    List<FireAndFloodAlertDto> fireAlerts = new ArrayList<FireAndFloodAlertDto>();
    for(Person p : persons) {
      if(p.getAddress().equals(address)) {
        station = p.getFireStation().getStation();
        Period etAge = AgeCalculator.ageCalculator(p.getMedicalRecord().getBirthdate());
        fireAlerts.add(new FireAndFloodAlertDto(p.getFirstName(), 
                                     p.getLastName(), 
                                     p.getPhone(), 
                                     etAge.getYears(), 
                                     p.getMedicalRecord().getMedications(),
                                     p.getMedicalRecord().getAllergies()));
      }
    }
    if(fireAlerts.isEmpty()) {
      return null;
    }
    fireAlert.put("station: " + station, fireAlerts);
    return fireAlert;
  }
}
