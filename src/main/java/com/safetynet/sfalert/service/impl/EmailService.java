package com.safetynet.sfalert.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.sfalert.model.Json;
import com.safetynet.sfalert.model.Person;
import com.safetynet.sfalert.service.IEmailService;

@Service
public class EmailService implements IEmailService {

  @Autowired
  private Json json;
  
  private static Logger logger = LoggerFactory.getLogger(EmailService.class);
  
  @Override 
  public List<String> getEmailList(String city){
    List<Person> persons = json.getPersons();
    List<String> emails = new ArrayList<String>();
    logger.debug("Searching emails of people living in " + city);
    for(Person p : persons) {
      if(p.getCity().equals(city)) {
        emails.add(p.getEmail());
      }
    }
    if(emails.isEmpty()) {
      logger.error("Invalid city: " + city);
      return null;
    }
    logger.info(emails.size() + " emails found in the city of " + city);
    return emails;
  }
}
