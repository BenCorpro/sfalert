package com.safetynet.sfalert.repository;

import java.util.List;

import com.safetynet.sfalert.model.Json;
import com.safetynet.sfalert.model.MedicalRecord;

public class MedicalRecordProxy {
  JsonProxy jsonProxy = new JsonProxy();
  
  public List<MedicalRecord> getMedicalrecords() {
    Json dataJson = jsonProxy.retrieveData();
    return dataJson.getMedicalrecords();
  }
  
  public MedicalRecord getMedicalrecord(String firstName, String lastName) {
    Json dataJson = jsonProxy.retrieveData();
    List<MedicalRecord> medicalRecords = dataJson.getMedicalrecords();
    MedicalRecord medicalRecord = null;
    for(MedicalRecord m : medicalRecords) {
      if(m.getFirstName().equals(firstName) && m.getLastName().equals(lastName)) {
        medicalRecord = m;
      }
    }
    return medicalRecord;
  }
}
