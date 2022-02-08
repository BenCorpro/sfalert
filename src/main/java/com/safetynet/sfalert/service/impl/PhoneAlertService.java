package com.safetynet.sfalert.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.sfalert.model.FireStation;
import com.safetynet.sfalert.model.Json;
import com.safetynet.sfalert.model.Person;
import com.safetynet.sfalert.service.IPhoneAlertService;

@Service
public class PhoneAlertService implements IPhoneAlertService {
  
  @Autowired
  private Json json;
  
  private static Logger logger = LoggerFactory.getLogger(PhoneAlertService.class);
  
  @Override 
  public List<String> phoneNumberList(String station){
    List<FireStation> fireStations = json.getFirestations();
    List<Person> persons = new ArrayList<Person>();
    List<String> phoneNumbers = new ArrayList<String>();
    logger.debug("Searching phone numbers served by station " + station);
    for(FireStation f : fireStations) {
      if(f.getStation().equals(station)) {
        persons.addAll(f.getPersons());
      }
    }
    for(Person p : persons) {
        phoneNumbers.add(p.getPhone());
    }
    if(phoneNumbers.isEmpty()) {
      logger.error("Invalid station: " + station);
      return null;
    }
    logger.info(phoneNumbers.size() + " phone numbers served by station " + station);
    return phoneNumbers;
  }
}
