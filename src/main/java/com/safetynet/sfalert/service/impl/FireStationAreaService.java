package com.safetynet.sfalert.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.sfalert.model.FireStation;
import com.safetynet.sfalert.model.Json;
import com.safetynet.sfalert.model.Person;
import com.safetynet.sfalert.dto.StationPeopleDto;
import com.safetynet.sfalert.service.IFireStationAreaService;
import com.safetynet.sfalert.util.AgeCalculator;

@Service
public class FireStationAreaService implements IFireStationAreaService {

  @Autowired
  private Json json;
  
  private static Logger logger = LoggerFactory.getLogger(FireStationAreaService.class);

  @Override 
  public Map<String, Object> getStationPeople(String stationNumber){
    List<FireStation> fireStations = json.getFirestations();
    List<Person> persons = new ArrayList<Person>();
    List<StationPeopleDto> stationPeoples = new ArrayList<StationPeopleDto>();
    Map<String, Object> fireSationArea = new HashMap<String, Object>();
    int adultCount = 0;
    int childCount = 0;
    logger.debug("Searching people served by station " + stationNumber + " and calculationg their ages");
    for(FireStation f : fireStations) {
      if(f.getStation().equals(stationNumber)) {
        persons.addAll(f.getPersons());
      }
    }
    for(Person p : persons) {
        stationPeoples.add(new StationPeopleDto(p.getFirstName(),
                                             p.getLastName(), 
                                             p.getAddress() + " " + 
                                             p.getZip() + " " + 
                                             p.getCity(),
                                             p.getPhone()));
        if (AgeCalculator.legalAgeFonction(p.getMedicalRecord().getBirthdate())) {
          adultCount++;
        } else {
          childCount++;
        }
        fireSationArea.put("Persons covered", stationPeoples);
        fireSationArea.put("Number of childs", childCount);
        fireSationArea.put("Number of adults", adultCount);
    }
    if(fireSationArea.isEmpty()) {
      logger.error("Invalid station: " + stationNumber);
      return null;
    }
    logger.info(childCount + " child(s) and " + adultCount + " adult(s) covered by station " + stationNumber);
    return fireSationArea;

  }

  
}
