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
import com.safetynet.sfalert.service.IFireAlertService;
import com.safetynet.sfalert.service.IFireStationAreaService;
import com.safetynet.sfalert.service.IFireStationService;
import com.safetynet.sfalert.service.IFloodAlertService;
import com.safetynet.sfalert.service.IPhoneAlertService;

@RestController
public class FireStationController {

  @Autowired
  private IFireStationAreaService fireStationAreaService;
  @Autowired
  private IPhoneAlertService phoneAlertService;
  @Autowired
  private IFireAlertService fireAlertService;
  @Autowired
  private IFloodAlertService floodAlertService;
  @Autowired
  private IFireStationService fireStationService;
  
  
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
  
  @PutMapping("/firestation")
  public ResponseEntity<FireStation> updateFireStation(@RequestBody FireStation fireStation) {
    FireStation updatedFireStation = fireStationService.updateFireStation(fireStation); 
    if(Objects.isNull(updatedFireStation)) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok().body(updatedFireStation);
    }
  }
  
  @DeleteMapping("/firestation")
  public ResponseEntity<Boolean> deleteFireStation(@RequestParam("station") String station) {
    boolean fireStationDeleted = fireStationService.deleteFireStation(station);
    if(!fireStationDeleted) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok().body(fireStationDeleted);
    }
  }
  
  @GetMapping("/firestation")
  public ResponseEntity<Map<String, Object>> getStationPeoples(@RequestParam("stationNumber") String stationNumber){
    Map<String, Object> stationPeoples = fireStationAreaService.getStationPeople(stationNumber);
    if(Objects.isNull(stationPeoples)) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok().body(stationPeoples);
    }   
  }
  
  @GetMapping("/phoneAlert")
  public ResponseEntity<List<String>> getPhoneAlert(@RequestParam("firestation") String firestation){
    List<String> phoneAlert = phoneAlertService.phoneNumberList(firestation);
    if(Objects.isNull(phoneAlert)) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok().body(phoneAlert);
    } 
  }
  
  @GetMapping("/fire")
  public ResponseEntity<Map<String, List<FireAndFloodAlertDto>>> getFireAlert(@RequestParam("address") String address){
    Map<String, List<FireAndFloodAlertDto>> fireAlert = fireAlertService.fireListAddress(address);
    if(Objects.isNull(fireAlert)) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok().body(fireAlert);
    } 
  }
  
  @GetMapping("/flood/stations")
  public ResponseEntity<Map<String, List<FireAndFloodAlertDto>>> getFloodAlert(@RequestParam("stations") List<String> stations){
    Map<String, List<FireAndFloodAlertDto>> floodAlert = floodAlertService.floodListStation(stations);
    if(Objects.isNull(floodAlert)) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok().body(floodAlert);
    } 
  }
  
}
