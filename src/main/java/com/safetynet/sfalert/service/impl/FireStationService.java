package com.safetynet.sfalert.service.impl;


import java.util.List;
import java.util.ListIterator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.safetynet.sfalert.model.FireStation;
import com.safetynet.sfalert.model.Json;
import com.safetynet.sfalert.service.IFireStationService;


@Service
public class FireStationService implements IFireStationService {

  @Autowired
  Json json;
  
  @Override 
  public FireStation saveFireStation(FireStation fireStation) {
    List<FireStation> fireStations = json.getFirestations();
    for(FireStation f : fireStations) {
      if(f.getAddress().equals(fireStation.getAddress())) {
        return null;
      }
    }
    fireStations.add(fireStation);
    json.setFireStations(fireStations);
    return fireStation;
  }
  
  @Override 
  public FireStation updateFireStation(FireStation fireStation) {
    List<FireStation> fireStations = json.getFirestations();
    for(FireStation f : fireStations) {
      if(f.getAddress().equals(fireStation.getAddress())) {
        f.setStation(fireStation.getStation());  
        json.setFireStations(fireStations);
        return fireStation;
      } 
    }
    return null;
  }
  
  @Override 
  public boolean deleteFireStation(String station) {
    List<FireStation> fireStations = json.getFirestations();
    ListIterator<FireStation> fireStationsIter = fireStations.listIterator();
    while(fireStationsIter.hasNext()) {
      FireStation f = fireStationsIter.next();
      if(f.getAddress().equals(station) || f.getStation().equals(station)) {
        fireStationsIter.remove();
        json.setFireStations(fireStations);
        return true;
      }
    }
    return false;
  }
  
  
}
