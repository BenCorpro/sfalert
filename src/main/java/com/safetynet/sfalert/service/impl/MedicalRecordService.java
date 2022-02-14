package com.safetynet.sfalert.service.impl;

import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.sfalert.constants.Constants;
import com.safetynet.sfalert.dto.ChildAlertDto;
import com.safetynet.sfalert.dto.PersonInfoDto;
import com.safetynet.sfalert.model.Json;
import com.safetynet.sfalert.model.MedicalRecord;
import com.safetynet.sfalert.model.Person;
import com.safetynet.sfalert.service.IMedicalRecordService;
import com.safetynet.sfalert.util.AgeCalculator;


@Service
public class MedicalRecordService implements IMedicalRecordService {

  @Autowired
  private Json json;
  
  private static Logger logger = LoggerFactory.getLogger(MedicalRecord.class);
  
  
  @Override 
  public Map<String, List<ChildAlertDto>> getChildAlertList(String address){
    Map<String, List<ChildAlertDto>> childAlert = new HashMap<String, List<ChildAlertDto>>();
    List<Person> persons = json.getPersons();
    List<ChildAlertDto> childAlerts = new ArrayList<ChildAlertDto>();
    List<ChildAlertDto> childAlertKids = new ArrayList<ChildAlertDto>();
    List<ChildAlertDto> childAlertAdults = new ArrayList<ChildAlertDto>();
    logger.debug("Searching people living at " + address + " and calculating their ages");
    for(Person p : persons) {
        if(p.getAddress().equals(address)) {
          Period age = AgeCalculator.ageCalculator(p.getMedicalRecord().getBirthdate());
          childAlerts.add(new ChildAlertDto(p.getLastName(), 
                                         p.getFirstName(), 
                                         age.getYears()));
        }
    }
    if(childAlerts.isEmpty()) {
      logger.error("Invalid address: " + address);
      return null;
    }
    for(ChildAlertDto cA : childAlerts) {
      if(cA.getAge() <= Constants.LEGAL_AGE) {
        childAlertKids.add(cA);
      } else {
        childAlertAdults.add(cA);
      }
    }
    if(!childAlertKids.isEmpty()) {
      childAlert.put("Childs at address", childAlertKids);
      childAlert.put("Adults at address", childAlertAdults);
      logger.info(childAlertKids.size() + " child(s) found at " + address);
      }
    return childAlert;
  }
  
  @Override 
  public List<PersonInfoDto> getPersonInfoList(String firstName, String lastName){
    List<Person> persons = json.getPersons();
    List<PersonInfoDto> personInfo = new ArrayList<PersonInfoDto>();
    logger.debug("Searching for " + lastName + " and calculating age");
    for(Person p : persons) {
      if(p.getLastName().equals(lastName)) {
        Period etAge = AgeCalculator.ageCalculator(p.getMedicalRecord().getBirthdate());
        personInfo.add(new PersonInfoDto(p.getFirstName(),
                                    p.getLastName(),
                                    p.getAddress() + " " + 
                                    p.getZip() + " " + 
                                    p.getCity(),
                                    etAge.getYears(),
                                    p.getEmail(),
                                    p.getMedicalRecord().getMedications(),
                                    p.getMedicalRecord().getAllergies()));
      }
    }
    if(personInfo.isEmpty()) {
      logger.error("Invalid name: " + lastName);
      return null;
    }
    logger.info("Informations found for " + lastName);
    return personInfo;
  }
  
  
  @Override 
  public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord){
    List<MedicalRecord> medicalRecords = json.getMedicalrecords();
    logger.debug("Looking for " + medicalRecord.getFirstName() + " " + medicalRecord.getLastName() + " medical record in data");
    for(MedicalRecord m : medicalRecords) {
      if(m.getFirstName().equals(medicalRecord.getFirstName()) && m.getLastName().equals(m.getLastName())) {
        logger.error("Medical record for " + medicalRecord.getFirstName() + " " + medicalRecord.getLastName() + " already in data");
        return null;
      } 
    }
    medicalRecords.add(medicalRecord);
    json.setMedicalRecords(medicalRecords);
    logger.info("New medical record for " + medicalRecord.getFirstName() + " " + medicalRecord.getLastName() + " created");
    return medicalRecord;
  }
  
  @Override 
  public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) {
    List<MedicalRecord> medicalRecords = json.getMedicalrecords();
    logger.debug("Looking for " + medicalRecord.getFirstName() + " " + medicalRecord.getLastName() + " medical record in data");
    for(MedicalRecord m : medicalRecords) {
      if(m.getFirstName().equals(medicalRecord.getFirstName()) && m.getLastName().equals(m.getLastName())) {
        m.setBirthdate(medicalRecord.getBirthdate());
        m.setMedications(medicalRecord.getMedications());
        m.setAllergies(medicalRecord.getAllergies());
        json.setMedicalRecords(medicalRecords);
        logger.info("Medical record for " + medicalRecord.getFirstName() + " " + medicalRecord.getLastName() + " updated");
        return medicalRecord;
      } 
    }
    logger.error("Medical record for " + medicalRecord.getFirstName() + " " + medicalRecord.getLastName() + " not found in data");
    return null;
  }
  
  @Override 
  public boolean deleteMedicalRecord(String firstName, String lastName) {
    List<MedicalRecord> medicalRecords = json.getMedicalrecords();
    ListIterator<MedicalRecord> medicalRecordsIter = medicalRecords.listIterator();
    logger.debug("Looking for " + firstName + " " + lastName + " medical record in data");
    while(medicalRecordsIter.hasNext()) {
      MedicalRecord m = medicalRecordsIter.next();
      if(m.getFirstName().equals(firstName) && m.getLastName().equals(lastName)) {
        medicalRecordsIter.remove();
        json.setMedicalRecords(medicalRecords);
        logger.info("Medical record for " + firstName + " " + lastName + " deleted");
        return true;
      }
    }
    logger.error("Medical record for " + firstName + " " + lastName + " not found in data");
    return false;
  }
  
  
}
