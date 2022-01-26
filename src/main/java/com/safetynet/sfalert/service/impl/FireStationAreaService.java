package com.safetynet.sfalert.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.sfalert.model.Json;
import com.safetynet.sfalert.model.Person;
import com.safetynet.sfalert.model.StationPeople;
import com.safetynet.sfalert.service.IFireStationAreaService;
import com.safetynet.sfalert.util.AgeCalculator;

@Service
public class FireStationAreaService implements IFireStationAreaService {

  @Autowired
  Json json;

  @Override 
  public Map<String, Object> getStationPeople(String stationNumber){
    List<Person> persons = json.getPersons();
    List<StationPeople> stationPeoples = new ArrayList<StationPeople>();
    Map<String, Object> fireSationArea = new HashMap<String, Object>();
    int adultCount = 0;
    int childCount = 0;
    for(Person p : persons) {
      if(p.getFireStation().getStation().equals(stationNumber)) {
        stationPeoples.add(new StationPeople(p.getFirstName(),
                                             p.getLastName(), 
                                             p.getAddress() + " " + 
                                             p.getZip() + " " + 
                                             p.getCity(),
                                             p.getPhone()));
        if (AgeCalculator.legalAgeFonction(AgeCalculator.ageCalculator(p.getMedicalRecord().getBirthdate()))) {
          adultCount++;
        } else {
          childCount++;
        }
        fireSationArea.put("Persons covered", stationPeoples);
        fireSationArea.put("Number of child", childCount);
        fireSationArea.put("Number of adults", adultCount);
      }
    }
    return fireSationArea;
  }

  
}
