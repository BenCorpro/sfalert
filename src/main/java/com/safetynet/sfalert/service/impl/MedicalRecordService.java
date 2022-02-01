package com.safetynet.sfalert.service.impl;

import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.sfalert.model.Json;
import com.safetynet.sfalert.model.MedicalRecord;
import com.safetynet.sfalert.service.IMedicalRecordService;

@Service
public class MedicalRecordService implements IMedicalRecordService {

  @Autowired
  Json json;
  

  @Override 
  public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord){
    List<MedicalRecord> medicalRecords = json.getMedicalrecords();
    for(MedicalRecord m : medicalRecords) {
      if(m.getFirstName().equals(medicalRecord.getFirstName()) && m.getLastName().equals(m.getLastName())) {
        return null;
      } 
    }
    medicalRecords.add(medicalRecord);
    json.setMedicalRecords(medicalRecords);
    return medicalRecord;
  }
  
  @Override 
  public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) {
    List<MedicalRecord> medicalRecords = json.getMedicalrecords();
    for(MedicalRecord m : medicalRecords) {
      if(m.getFirstName().equals(medicalRecord.getFirstName()) && m.getLastName().equals(m.getLastName())) {
        m.setBirthdate(medicalRecord.getBirthdate());
        m.setMedications(medicalRecord.getMedications());
        m.setAllergies(medicalRecord.getAllergies());
        json.setMedicalRecords(medicalRecords);
        return medicalRecord;
      } 
    }
    return null;
  }
  
  @Override 
  public boolean deleteMedicalRecord(String firstName, String lastName) {
    List<MedicalRecord> medicalRecords = json.getMedicalrecords();
    ListIterator<MedicalRecord> medicalRecordsIter = medicalRecords.listIterator();
    while(medicalRecordsIter.hasNext()) {
      MedicalRecord m = medicalRecordsIter.next();
      if(m.getFirstName().equals(firstName) && m.getLastName().equals(lastName)) {
        medicalRecordsIter.remove();
        json.setMedicalRecords(medicalRecords);
        return true;
      }
    }
    return false;
  }
}
