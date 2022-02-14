package com.safetynet.sfalert.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.safetynet.sfalert.dto.FireAndFloodAlertDto;
import com.safetynet.sfalert.model.FireStation;
import com.safetynet.sfalert.service.IFireStationService;


/**
 * The Class FireStationController, REST controller for endpoints around firestation entity.
 */
@RestController
public class FireStationController {

  /** The fire station service. */
  @Autowired
  private IFireStationService fireStationService;
  
  
  /**
   * Station people endpoint.
   *
   * @param stationNumber the station number
   * @return the http status and body if successful
   */
  @GetMapping("/firestation")
  public ResponseEntity<Map<String, Object>> stationPeople(@RequestParam("stationNumber") String stationNumber){
    Map<String, Object> stationPeoples = fireStationService.getStationPeopleList(stationNumber);
    if(Objects.isNull(stationPeoples)) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok().body(stationPeoples);
    }   
  }
  
  /**
   * Phone alert endpoint.
   *
   * @param firestation the firestation number
   * @return the http status and body if successful
   */
  @GetMapping("/phoneAlert")
  public ResponseEntity<List<String>> phoneAlert(@RequestParam("firestation") String firestation){
    List<String> phoneAlerts = fireStationService.getPhoneNumberList(firestation);
    if(Objects.isNull(phoneAlerts)) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok().body(phoneAlerts);
    } 
  }
  
  /**
   * Fire alert endpoint.
   *
   * @param address the address
   * @return the http status and body if successful
   */
  @GetMapping("/fire")
  public ResponseEntity<Map<String, List<FireAndFloodAlertDto>>> fireAlert(@RequestParam("address") String address){
    Map<String, List<FireAndFloodAlertDto>> fireAlerts = fireStationService.getFireListAddress(address);
    if(Objects.isNull(fireAlerts)) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok().body(fireAlerts);
    } 
  }
  
  /**
   * Flood alert endpoint.
   *
   * @param stations the stations numbers
   * @return the http status and body if successful
   */
  @GetMapping("/flood/stations")
  public ResponseEntity<Map<String, List<FireAndFloodAlertDto>>> floodAlert(@RequestParam("stations") List<String> stations){
    Map<String, List<FireAndFloodAlertDto>> floodAlerts = fireStationService.getFloodListStation(stations);
    if(Objects.isNull(floodAlerts)) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok().body(floodAlerts);
    } 
  }
  
  
  /**
   * Create a new fire station.
   *
   * @param fireStation the fire station
   * @return the http status and body if successful
   */
  @PostMapping("/firestation")
  public ResponseEntity<FireStation> addFireStation(@RequestBody FireStation fireStation) {
    FireStation addedFireStation = fireStationService.saveFireStation(fireStation);
    if(Objects.isNull(addedFireStation)) {
      return ResponseEntity.noContent().build();
    } else {
      URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                                .path("/{address}&{station}")
                                                .buildAndExpand(addedFireStation.getAddress(), addedFireStation.getStation())
                                                .toUri();
      return ResponseEntity.created(location).build();
    }
  }
  
  /**
   * Update a fire station.
   *
   * @param fireStation the fire station
   * @return the http status and body if successful
   */
  @PutMapping("/firestation")
  public ResponseEntity<FireStation> updateFireStation(@RequestBody FireStation fireStation) {
    FireStation updatedFireStation = fireStationService.updateFireStation(fireStation); 
    if(Objects.isNull(updatedFireStation)) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok().body(updatedFireStation);
    }
  }
  
  /**
   * Delete a fire station.
   *
   * @param station the station
   * @return the http status and body if successful
   */
  @DeleteMapping("/firestation")
  public ResponseEntity<Boolean> deleteFireStation(@RequestParam("station") String station) {
    boolean fireStationDeleted = fireStationService.deleteFireStation(station);
    if(!fireStationDeleted) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok().body(fireStationDeleted);
    }
  }
  
 
}
