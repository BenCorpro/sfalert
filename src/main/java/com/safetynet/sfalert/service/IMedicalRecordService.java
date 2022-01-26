package com.safetynet.sfalert.service;

import com.safetynet.sfalert.model.MedicalRecord;

public interface IMedicalRecordService {

  public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord);

  public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord);

  public boolean deleteMedicalRecord(String firstName, String lastName);

}