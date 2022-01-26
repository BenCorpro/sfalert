package com.safetynet.sfalert.service;

import com.safetynet.sfalert.model.FireStation;

public interface IFireStationService {

  public FireStation saveFireStation(FireStation fireStation);

  public FireStation updateFireStation(FireStation fireStation);

  public boolean deleteFireStation(String station);

}