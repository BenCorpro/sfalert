package com.safetynet.sfalert.service.impl;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;

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
  
  @Override 
  public List<PersonInfoDto> getPersonInfo(String firstName, String lastName){
    List<Person> persons = json.getPersons();
    List<PersonInfoDto> personInfo = new ArrayList<PersonInfoDto>();
    for(Person p : persons) {
      if(p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)) {
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
      return null;
    }
    return personInfo;
  }
}
