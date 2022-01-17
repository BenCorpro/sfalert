package com.safetynet.sfalert.service;

import java.text.ParseException;
import java.time.Period;
import java.util.List;

import com.safetynet.sfalert.model.Entity;
import com.safetynet.sfalert.model.PersonInfo;
import com.safetynet.sfalert.repository.EntityProxy;
import com.safetynet.sfalert.util.AgeCalculator;

public class PersonInfoService {

  EntityProxy entityProxy = new EntityProxy();
  
  public PersonInfo getPersonInfo(String firstName, String lastName) throws ParseException{
    List<Entity> entities = entityProxy.getEntities();
    PersonInfo personInfo = null;
    for(Entity eT : entities) {
      if(eT.getPerson().getFirstName().equals(firstName) && eT.getPerson().getLastName().equals(lastName)) {
        Period etAge = AgeCalculator.ageCalculator(eT.getMedicalRecord().getBirthdate());
        personInfo = new PersonInfo(eT.getPerson().getFirstName(),
                                    eT.getPerson().getLastName(),
                                    eT.getPerson().getAddress(),
                                    etAge,
                                    eT.getPerson().getEmail(),
                                    eT.getMedicalRecord().getMedications(),
                                    eT.getMedicalRecord().getAllergies());
      }
    }
    return personInfo;
  }
}
