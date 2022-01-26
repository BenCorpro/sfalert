package com.safetynet.sfalert.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.sfalert.model.Json;
import com.safetynet.sfalert.model.Person;
import com.safetynet.sfalert.service.IEmailService;

@Service
public class EmailService implements IEmailService {

  @Autowired
  Json json;
  
  @Override 
  public List<String> getEmailList(String city){
    List<Person> persons = json.getPersons();
    List<String> emails = new ArrayList<String>();
    for(Person p : persons) {
      if(p.getCity().equals(city)) {
        emails.add(p.getEmail());
      }
    }
    return emails;
  }
}
