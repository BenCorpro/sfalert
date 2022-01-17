package com.safetynet.sfalert.repository;

import java.util.ArrayList;
import java.util.List;

import com.safetynet.sfalert.model.FireStation;
import com.safetynet.sfalert.model.Json;

public class FireStationProxy {
  
  JsonProxy jsonProxy = new JsonProxy();
  
  public List<FireStation> getFirestations() {
    Json dataJson = jsonProxy.retrieveData();
    return dataJson.getFirestations();
  }
  
  public List<FireStation> getFirestation(String station) {
    Json dataJson = jsonProxy.retrieveData();
    List<FireStation> fireStations = dataJson.getFirestations();
    List<FireStation> fireStation = new ArrayList<FireStation>();
    for(FireStation f : fireStations) {
      if(f.getStation().equals(station)) {
        fireStation.add(f);
      }
    }
    return fireStation;
  }
  
  public FireStation getFirestationFromAddress(String address) {
    Json dataJson = jsonProxy.retrieveData();
    List<FireStation> fireStations = dataJson.getFirestations();
    FireStation fireStation = null;
    for(FireStation f : fireStations) {
      if(f.getAddress().equals(address)) {
        fireStation = f ;
      }
    }
    return fireStation;
  }
  
}
