package com.safetynet.sfalert.service;

import java.util.List;
import java.util.Map;

import com.safetynet.sfalert.dto.ChildAlertDto;
import com.safetynet.sfalert.dto.PersonInfoDto;
import com.safetynet.sfalert.model.MedicalRecord;


public interface IMedicalRecordService {

  public Map<String, List<ChildAlertDto>> getChildAlertList(String address);
  
  public List<PersonInfoDto> getPersonInfoList(String firstName, String lastName);
  
  public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord);

  public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord);

  public boolean deleteMedicalRecord(String firstName, String lastName);

  
}