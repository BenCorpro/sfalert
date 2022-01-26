package com.safetynet.sfalert.service.impl;

import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.sfalert.constants.Constants;
import com.safetynet.sfalert.model.ChildAlert;
import com.safetynet.sfalert.model.Json;
import com.safetynet.sfalert.model.Person;
import com.safetynet.sfalert.service.IChildAlertService;
import com.safetynet.sfalert.util.AgeCalculator;

@Service
public class ChildAlertService implements IChildAlertService {
  
  @Autowired
  Json json;
  
  @Override 
  public Map<String, List<ChildAlert>> getChilds(String address){
    Map<String, List<ChildAlert>> childAlert = new HashMap<String, List<ChildAlert>>();
    List<Person> persons = json.getPersons();
    List<ChildAlert> childAlerts = new ArrayList<ChildAlert>();
    List<ChildAlert> childAlertKids = new ArrayList<ChildAlert>();
    List<ChildAlert> childAlertAdults = new ArrayList<ChildAlert>();
    for(Person p : persons) {
        if(p.getAddress().equals(address)) {
          Period age = AgeCalculator.ageCalculator(p.getMedicalRecord().getBirthdate());
          childAlerts.add(new ChildAlert(p.getLastName(), 
                                         p.getFirstName(), 
                                         age.getYears()));
        }
    }
    for(ChildAlert cA : childAlerts) {
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
