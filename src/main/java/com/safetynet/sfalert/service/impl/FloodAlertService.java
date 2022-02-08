package com.safetynet.sfalert.service.impl;

import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.sfalert.dto.FireAndFloodAlertDto;
import com.safetynet.sfalert.model.FireStation;
import com.safetynet.sfalert.model.Json;
import com.safetynet.sfalert.model.Person;
import com.safetynet.sfalert.service.IFloodAlertService;
import com.safetynet.sfalert.util.AgeCalculator;

@Service
public class FloodAlertService implements IFloodAlertService {

  @Autowired
  private Json json;
  
  private static Logger logger = LoggerFactory.getLogger(FloodAlertService.class);
  
  @Override 
  public Map<String, List<FireAndFloodAlertDto>> floodListStation(List<String> station){
    Map<String, List<FireAndFloodAlertDto>> floodAlert = new HashMap<String, List<FireAndFloodAlertDto>>();
    List<FireStation> fireStations = json.getFirestations();
    List<String> addresses = new ArrayList<String>();
    for(String s : station) {
      for(FireStation f : fireStations) {
        if(f.getStation().equals(s)) {
          addresses.add(f.getAddress());
        }
      }
    }
    if(addresses.isEmpty()) {
      logger.error("Invalid station(s): " + station);
      return null;
    }
    List<Person> persons = json.getPersons();
    logger.debug("Searching people served by " + station + " and calculationg their ages");
    for(String a : addresses) {
      List<FireAndFloodAlertDto> floodAlerts = new ArrayList<FireAndFloodAlertDto>();
      for(Person p : persons) {
        if(p.getAddress().equals(a)) {
          Period etAge = AgeCalculator.ageCalculator(p.getMedicalRecord().getBirthdate());
          floodAlerts.add(new FireAndFloodAlertDto(p.getFirstName(),
                                        p.getLastName(),
                                        p.getPhone(),
                                        etAge.getYears(),
                                        p.getMedicalRecord().getMedications(),
                                        p.getMedicalRecord().getAllergies()));
        }
      }
      floodAlert.put(a, floodAlerts);
    }
    logger.info(floodAlert.size() + " adresses served by station(s) " + station);
    return floodAlert;
  }
  
  
}
