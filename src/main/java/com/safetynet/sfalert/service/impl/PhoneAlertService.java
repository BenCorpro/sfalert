package com.safetynet.sfalert.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.sfalert.model.Json;
import com.safetynet.sfalert.model.Person;
import com.safetynet.sfalert.service.IPhoneAlertService;

@Service
public class PhoneAlertService implements IPhoneAlertService {
  
  @Autowired
  private Json json;
  
  @Override 
  public List<String> phoneNumberList(String station){
    List<Person> persons = json.getPersons();
    List<String> phoneNumbers = new ArrayList<String>();
    for(Person p : persons) {
      if(p.getFireStation().getStation().equals(station)) {
        phoneNumbers.add(p.getPhone());
      }
    }
    if(phoneNumbers.isEmpty()) {
      return null;
    }
    return phoneNumbers;
  }
}
