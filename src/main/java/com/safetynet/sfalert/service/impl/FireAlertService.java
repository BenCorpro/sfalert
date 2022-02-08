package com.safetynet.sfalert.service.impl;

import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.sfalert.dto.FireAndFloodAlertDto;
import com.safetynet.sfalert.model.FireStation;
import com.safetynet.sfalert.model.Json;
import com.safetynet.sfalert.model.Person;
import com.safetynet.sfalert.service.IFireAlertService;
import com.safetynet.sfalert.util.AgeCalculator;

@Service
public class FireAlertService implements IFireAlertService {
  
  @Autowired
  private Json json;
  
  private static Logger logger = LoggerFactory.getLogger(FireAlertService.class);
  
  @Override 
  public Map<String, List<FireAndFloodAlertDto>> fireListAddress(String address){
    Map<String, List<FireAndFloodAlertDto>> fireAlert = new HashMap<String, List<FireAndFloodAlertDto>>();
    List<Person> persons = json.getPersons();
    List<FireStation> fireStations = json.getFirestations();
    String station = null;
    List<FireAndFloodAlertDto> fireAlerts = new ArrayList<FireAndFloodAlertDto>();
    logger.debug("Searching people living at " + address + " and calculationg their ages");
    for(FireStation f : fireStations) {
      if(f.getAddress().equals(address)) {
        if(Objects.isNull(station)) {
          station = f.getStation();
        } else {
          station = station.concat(" & " + f.getStation());
        }
      }
    }
    for(Person p : persons) {
      if(p.getAddress().equals(address)) {
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
      logger.error("Invalid address: " + address);
      return null;
    }
    fireAlert.put("station " + station, fireAlerts);
    logger.info(fireAlerts.size() + " persons served at " + address + " by station " + station);
    return fireAlert;
  }
}
