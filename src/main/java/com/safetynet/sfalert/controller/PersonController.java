package com.safetynet.sfalert.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.sfalert.model.ChildAlert;
import com.safetynet.sfalert.model.FireAlert;
import com.safetynet.sfalert.model.FireStationArea;
import com.safetynet.sfalert.model.Person;
import com.safetynet.sfalert.model.PersonInfo;
import com.safetynet.sfalert.model.StationPeople;
import com.safetynet.sfalert.service.ChildAlertService;
import com.safetynet.sfalert.service.FireAlertService;
import com.safetynet.sfalert.service.FireStationAreaService;
import com.safetynet.sfalert.service.FloodAlertService;
import com.safetynet.sfalert.service.PersonInfoService;
import com.safetynet.sfalert.service.PersonService;
import com.safetynet.sfalert.service.PhoneAlertService;

@RestController
public class PersonController {

  PersonService personService = new PersonService();
  FireStationAreaService fireStationAreaService = new FireStationAreaService();
  ChildAlertService childAlertService = new ChildAlertService();
  PhoneAlertService phoneAlertService = new PhoneAlertService();
  FireAlertService fireAlertService = new FireAlertService();
  FloodAlertService floodAlertService = new FloodAlertService();
  PersonInfoService personInfoService = new PersonInfoService();
  
  @GetMapping("/persons")
  public List<Person> getPersons(){
    return personService.getPersons();
  }
  
  @GetMapping("/firestation")
  public FireStationArea getStationPeoples(@RequestParam("stationNumber") String stationNumber) throws ParseException {
    return fireStationAreaService.getStationPeople(stationNumber);
  }
  
  @GetMapping("/childAlert")
  public List<ChildAlert> getChildAlert(@RequestParam("address") String address) throws ParseException{
    return childAlertService.getChilds(address);
  }
  
  @GetMapping("/phoneAlert")
  public List<String> getPhoneAlert(@RequestParam("firestation") String firestation){
    return phoneAlertService.phoneNumberList(firestation);
  }
  
  @GetMapping("/fire")
  public List<FireAlert> getFireAlert(@RequestParam("address") String address) throws ParseException{
    return fireAlertService.fireListAddress(address);
  }
  
  @GetMapping("/flood/stations")
  public List<FireAlert> getFloodAlert(@RequestParam("stations") String stations) throws ParseException{
    return floodAlertService.floodListStation(stations);
  }
  
  @GetMapping("/personInfo")
  public PersonInfo getPersonInfo(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) throws ParseException {
    return personInfoService.getPersonInfo(firstName, lastName);
  }
}
