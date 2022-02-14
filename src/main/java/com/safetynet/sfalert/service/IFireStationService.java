package com.safetynet.sfalert.service;

import java.util.List;
import java.util.Map;

import com.safetynet.sfalert.dto.FireAndFloodAlertDto;
import com.safetynet.sfalert.model.FireStation;


public interface IFireStationService {

  public Map<String, Object> getStationPeopleList(String stationNumber);
  
  public List<String> getPhoneNumberList(String station);
  
  public Map<String, List<FireAndFloodAlertDto>> getFireListAddress(String address);
  
  public Map<String, List<FireAndFloodAlertDto>> getFloodListStation(List<String> station);
  
  public FireStation saveFireStation(FireStation fireStation);

  public FireStation updateFireStation(FireStation fireStation);

  public boolean deleteFireStation(String station);

  
}