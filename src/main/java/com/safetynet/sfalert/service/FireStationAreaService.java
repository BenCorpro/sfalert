package com.safetynet.sfalert.service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.safetynet.sfalert.model.FireStation;
import com.safetynet.sfalert.model.FireStationArea;
import com.safetynet.sfalert.model.MedicalRecord;
import com.safetynet.sfalert.model.Person;
import com.safetynet.sfalert.model.StationPeople;
import com.safetynet.sfalert.repository.FireStationProxy;
import com.safetynet.sfalert.repository.MedicalRecordProxy;
import com.safetynet.sfalert.repository.PersonProxy;

public class FireStationAreaService {
  FireStationProxy fireStationProxy = new FireStationProxy();
  PersonProxy personProxy = new PersonProxy();
  MedicalRecordProxy medicalRecordProxy = new MedicalRecordProxy();
  
  public FireStationArea getStationPeople(String stationNumber) throws ParseException{
    List<FireStation> stations = fireStationProxy.getFirestation(stationNumber);
    List<Person> persons = personProxy.getPersons();
    List<Person> result = new ArrayList<Person>();
    for(FireStation f : stations) {
      for(Person p : persons) {
        if(p.getAddress().equals(f.getAddress())) {
          result.add(p);
        }
      }
    }
    List<StationPeople> stationPeoples = new ArrayList<StationPeople>();
    for(Person p : result) {
      stationPeoples.add(new StationPeople(p.getFirstName(), p.getLastName(), p.getAddress() + " " + p.getZip() + " " + p.getCity(), p.getPhone()));
    }
    List<Integer> majorityCount = this.childAndAdultCount(this.legalAgeFonction(this.ageCalculator()));
    FireStationArea fireSationArea = new FireStationArea(stationPeoples, majorityCount.get(1), majorityCount.get(0));
    return fireSationArea;
  }
  
  
  public List<Period> ageCalculator() throws ParseException {
    List<MedicalRecord> medicalRecords = medicalRecordProxy.getMedicalrecords();
    List<LocalDate> birthDates = new ArrayList<LocalDate>();
    LocalDate dateNow = LocalDate.now();
    List<Period> ages = new ArrayList<Period>();
    for(MedicalRecord m : medicalRecords) {
      birthDates.add(LocalDate.parse(m.getBirthdate(), DateTimeFormatter.ofPattern("MM/dd/yyyy")));
      
    }
    for(LocalDate d : birthDates) {
      ages.add(Period.between(d, dateNow));
    }

    return ages;
  }
  
  public List<Boolean> legalAgeFonction(List<Period> ages){
    long legalAge = 18;
    List<Boolean> majority = new ArrayList<Boolean>();
    for(Period a : ages) {
      if(a.minusYears(legalAge).isNegative()) {
        majority.add(false);
      } else {
        majority.add(true);
      }
    }
    return majority;
  }
  
  public List<Integer> childAndAdultCount(List<Boolean> majority) {
    int adultCount = 0;
    int childCount = 0;
    List<Integer> majorityCount = new ArrayList<Integer>();
    for(Boolean m : majority) {
      if(m == false) {
        childCount++;
      } else {
        adultCount++;
      }
    } 
    majorityCount.add(childCount);
    majorityCount.add(adultCount);
    return majorityCount;
  }
}
