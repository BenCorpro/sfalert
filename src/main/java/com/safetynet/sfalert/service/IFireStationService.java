package com.safetynet.sfalert.service;

import java.util.List;
import java.util.Map;

import com.safetynet.sfalert.dto.FireAndFloodAlertDto;
import com.safetynet.sfalert.model.FireStation;


/**
 * The Interface IFireStationService, groups the service methods requiring firestation entity.
 */
public interface IFireStationService {

  /**
   * Gets the list of persons covered by the station.
   *
   * @param stationNumber the station number
   * @return the list of persons covered by the station
   */
  public Map<String, Object> getStationPeopleList(String stationNumber);
  
  /**
   * Gets the list of person's phone number covered by the station.
   *
   * @param station the station number
   * @return the list of person's phone number covered by the station
   */
  public List<String> getPhoneNumberList(String station);
  
  /**
   * Gets the list of persons living at the address.
   *
   * @param address the address
   * @return the list of persons living at the address
   */
  public Map<String, List<FireAndFloodAlertDto>> getFireListAddress(String address);
  
  /**
   * Gets the list of person's covered by the stations.
   *
   * @param station the stations numbers
   * @return the list of person's covered by the stations
   */
  public Map<String, List<FireAndFloodAlertDto>> getFloodListStation(List<String> station);
  
  /**
   * Save a new fire station.
   *
   * @param fireStation the new fire station
   * @return the fire station saved, if successful, null if not
   */
  public FireStation saveFireStation(FireStation fireStation);

  /**
   * Update a fire station.
   *
   * @param fireStation the fire station to update
   * @return the fire station updated, if successful, null if not
   */
  public FireStation updateFireStation(FireStation fireStation);

  /**
   * Delete a fire station.
   *
   * @param station the station to delete, address or number
   * @return true, if successful
   */
  public boolean deleteFireStation(String station);

  
}