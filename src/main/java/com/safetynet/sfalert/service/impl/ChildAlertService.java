package com.safetynet.sfalert.service.impl;

import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.sfalert.constants.Constants;
import com.safetynet.sfalert.dto.ChildAlertDto;
import com.safetynet.sfalert.model.Json;
import com.safetynet.sfalert.model.Person;
import com.safetynet.sfalert.service.IChildAlertService;
import com.safetynet.sfalert.util.AgeCalculator;

@Service
public class ChildAlertService implements IChildAlertService {
  
  @Autowired
  private Json json;
  
  @Override 
  public Map<String, List<ChildAlertDto>> getChilds(String address){
    Map<String, List<ChildAlertDto>> childAlert = new HashMap<String, List<ChildAlertDto>>();
    List<Person> persons = json.getPersons();
    List<com.safetynet.sfalert.dto.ChildAlertDto> childAlerts = new ArrayList<ChildAlertDto>();
    List<ChildAlertDto> childAlertKids = new ArrayList<ChildAlertDto>();
    List<ChildAlertDto> childAlertAdults = new ArrayList<ChildAlertDto>();
    for(Person p : persons) {
        if(p.getAddress().equals(address)) {
          Period age = AgeCalculator.ageCalculator(p.getMedicalRecord().getBirthdate());
          childAlerts.add(new ChildAlertDto(p.getLastName(), 
                                         p.getFirstName(), 
                                         age.getYears()));
        }
    }
    if(childAlerts.isEmpty()) {
      return null;
    }
    for(ChildAlertDto cA : childAlerts) {
      if(cA.getAge() <= Constants.LEGAL_AGE) {
        childAlertKids.add(cA);
      } else {
        childAlertAdults.add(cA);
      }
    }
    if(!childAlertKids.isEmpty()) {
      childAlert.put("Childs at address", childAlertKids);
      childAlert.put("Adults at address", childAlertAdults);
      }
    return childAlert;
  }
}
