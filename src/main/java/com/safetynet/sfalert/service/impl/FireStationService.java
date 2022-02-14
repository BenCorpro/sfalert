package com.safetynet.sfalert.service.impl;


import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.sfalert.dto.FireAndFloodAlertDto;
import com.safetynet.sfalert.dto.StationPeopleDto;
import com.safetynet.sfalert.model.FireStation;
import com.safetynet.sfalert.model.Json;
import com.safetynet.sfalert.model.Person;
import com.safetynet.sfalert.service.IFireStationService;
import com.safetynet.sfalert.util.AgeCalculator;


/**
 * The Class FireStationService, groups the service methods requiring firestation entity.
 * Implements IFireStationService
 * @see IFireStationService
 */
@Service
public class FireStationService implements IFireStationService {

  /** The json data file. */
  @Autowired
  private Json json;
  
  /** The logger. */
  private static Logger logger = LoggerFactory.getLogger(FireStation.class);
  
  
  @Override 
  public Map<String, Object> getStationPeopleList(String stationNumber){
    List<FireStation> fireStations = json.getFirestations();
    List<Person> persons = new ArrayList<Person>();
    List<StationPeopleDto> stationPeoples = new ArrayList<StationPeopleDto>();
    Map<String, Object> fireStationArea = new HashMap<String, Object>();
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
        fireStationArea.put("Persons covered", stationPeoples);
        fireStationArea.put("Number of childs", childCount);
        fireStationArea.put("Number of adults", adultCount);
    }
    if(fireStationArea.isEmpty()) {
      logger.error("Invalid station: " + stationNumber);
      return null;
    }
    logger.info(childCount + " child(s) and " + adultCount + " adult(s) covered by station " + stationNumber);
    return fireStationArea;

  }
  
  @Override 
  public List<String> getPhoneNumberList(String station){
    List<FireStation> fireStations = json.getFirestations();
    List<Person> persons = new ArrayList<Person>();
    List<String> phoneNumbers = new ArrayList<String>();
    logger.debug("Searching phone numbers served by station " + station);
    for(FireStation f : fireStations) {
      if(f.getStation().equals(station)) {
        persons.addAll(f.getPersons());
      }
    }
    for(Person p : persons) {
        phoneNumbers.add(p.getPhone());
    }
    if(phoneNumbers.isEmpty()) {
      logger.error("Invalid station: " + station);
      return null;
    }
    logger.info(phoneNumbers.size() + " phone numbers served by station " + station);
    return phoneNumbers;
  }
  
  @Override 
  public Map<String, List<FireAndFloodAlertDto>> getFireListAddress(String address){
    Map<String, List<FireAndFloodAlertDto>> fireAlert = new HashMap<String, List<FireAndFloodAlertDto>>();
    List<Person> persons = json.getPersons();
    List<FireStation> fireStations = json.getFirestations();
    String station = null;
    List<FireAndFloodAlertDto> fireAlerts = new ArrayList<FireAndFloodAlertDto>();
    logger.debug("Searching people living at " + address + " and calculationg their ages");
    for(FireStation f : fireStations) {
      if(f.getAddress().equals(address)) {
        if(Objects.isNull(station)) {
          station = f.getStation();
        } else {
          station = station.concat(" & " + f.getStation());
        }
      }
    }
    for(Person p : persons) {
      if(p.getAddress().equals(address)) {
        Period etAge = AgeCalculator.ageCalculator(p.getMedicalRecord().getBirthdate());
        fireAlerts.add(new FireAndFloodAlertDto(p.getFirstName(), 
                                     p.getLastName(), 
                                     p.getPhone(), 
                                     etAge.getYears(), 
                                     p.getMedicalRecord().getMedications(),
                                     p.getMedicalRecord().getAllergies()));
      }
    }
    if(fireAlerts.isEmpty()) {
      logger.error("Invalid address: " + address);
      return null;
    }
    fireAlert.put("station " + station, fireAlerts);
    logger.info(fireAlerts.size() + " persons served at " + address + " by station " + station);
    return fireAlert;
  }
  
  @Override 
  public Map<String, List<FireAndFloodAlertDto>> getFloodListStation(List<String> station){
    Map<String, List<FireAndFloodAlertDto>> floodAlert = new HashMap<String, List<FireAndFloodAlertDto>>();
    List<FireStation> fireStations = json.getFirestations();
    List<String> addresses = new ArrayList<String>();
    for(String s : station) {
      for(FireStation f : fireStations) {
        if(f.getStation().equals(s)) {
          addresses.add(f.getAddress());
        }
      }
    }
    if(addresses.isEmpty()) {
      logger.error("Invalid station(s): " + station);
      return null;
    }
    List<Person> persons = json.getPersons();
    logger.debug("Searching people served by " + station + " and calculationg their ages");
    for(String a : addresses) {
      List<FireAndFloodAlertDto> floodAlerts = new ArrayList<FireAndFloodAlertDto>();
      for(Person p : persons) {
        if(p.getAddress().equals(a)) {
          Period etAge = AgeCalculator.ageCalculator(p.getMedicalRecord().getBirthdate());
          floodAlerts.add(new FireAndFloodAlertDto(p.getFirstName(),
                                        p.getLastName(),
                                        p.getPhone(),
                                        etAge.getYears(),
                                        p.getMedicalRecord().getMedications(),
                                        p.getMedicalRecord().getAllergies()));
        }
      }
      floodAlert.put(a, floodAlerts);
    }
    logger.info(floodAlert.size() + " adresses served by station(s) " + station);
    return floodAlert;
  }
  
  
  @Override 
  public FireStation saveFireStation(FireStation fireStation) {
    List<FireStation> fireStations = json.getFirestations();
    logger.debug("Looking for " + fireStation.getAddress() + " firestation in data");
    for(FireStation f : fireStations) {
      if(f.getAddress().equals(fireStation.getAddress())) {
        logger.error("Firestation for " + fireStation.getAddress() + " already in data");
        return null;
      }
    }
    fireStations.add(fireStation);
    json.setFireStations(fireStations);
    logger.info("New firestation for " + fireStation.getAddress() + " created");
    return fireStation;
  }
  
  @Override 
  public FireStation updateFireStation(FireStation fireStation) {
    List<FireStation> fireStations = json.getFirestations();
    logger.debug("Looking for " + fireStation.getAddress() + " firestation in data");
    for(FireStation f : fireStations) {
      if(f.getAddress().equals(fireStation.getAddress())) {
        f.setStation(fireStation.getStation());  
        json.setFireStations(fireStations);
        logger.info("Firestation for " + fireStation.getAddress() + " updated");
        return fireStation;
      } 
    }
    logger.error("Firestation for " + fireStation.getAddress() + " not found in data");
    return null;
  }
  
  @Override 
  public boolean deleteFireStation(String station) {
    List<FireStation> fireStations = json.getFirestations();
    ListIterator<FireStation> fireStationsIter = fireStations.listIterator();
    logger.debug("Looking for " + station + " firestation in data");
    while(fireStationsIter.hasNext()) {
      FireStation f = fireStationsIter.next();
      if(f.getAddress().equals(station) || f.getStation().equals(station)) {
        fireStationsIter.remove();
        json.setFireStations(fireStations);
        logger.info("Firestation for " + station + " deleted");
        return true;
      }
    }
    logger.error("Firestation for " + station + " not found in data");
    return false;
  }
  
  
}
