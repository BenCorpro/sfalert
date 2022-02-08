package com.safetynet.sfalert.service.impl;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.sfalert.model.Json;
import com.safetynet.sfalert.model.Person;
import com.safetynet.sfalert.dto.PersonInfoDto;
import com.safetynet.sfalert.service.IPersonInfoService;
import com.safetynet.sfalert.util.AgeCalculator;

@Service
public class PersonInfoService implements IPersonInfoService {

  @Autowired
  private Json json;
  
  private static Logger logger = LoggerFactory.getLogger(PersonInfoService.class);
  
  @Override 
  public List<PersonInfoDto> getPersonInfo(String firstName, String lastName){
    List<Person> persons = json.getPersons();
    List<PersonInfoDto> personInfo = new ArrayList<PersonInfoDto>();
    logger.debug("Searching for " + lastName + " and calculating age");
    for(Person p : persons) {
      if(p.getLastName().equals(lastName)) {
        Period etAge = AgeCalculator.ageCalculator(p.getMedicalRecord().getBirthdate());
        personInfo.add(new PersonInfoDto(p.getFirstName(),
                                    p.getLastName(),
                                    p.getAddress(),
                                    etAge.getYears(),
                                    p.getEmail(),
                                    p.getMedicalRecord().getMedications(),
                                    p.getMedicalRecord().getAllergies()));
      }
    }
    if(personInfo.isEmpty()) {
      logger.error("Invalid name: " + lastName);
      return null;
    }
    logger.info("Informations found for " + lastName);
    return personInfo;
  }
}
