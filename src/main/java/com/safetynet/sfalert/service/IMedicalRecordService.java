package com.safetynet.sfalert.service;

import java.util.List;
import java.util.Map;

import com.safetynet.sfalert.dto.ChildAlertDto;
import com.safetynet.sfalert.dto.PersonInfoDto;
import com.safetynet.sfalert.model.MedicalRecord;


/**
 * The Interface IMedicalRecordService, groups the service methods requiring medicalrecord entity.
 */
public interface IMedicalRecordService {

  /**
   * Gets the list of children living at the address.
   *
   * @param address the address
   * @return the list of children living at the address list of children living at the address
   */
  public Map<String, List<ChildAlertDto>> getChildAlertList(String address);
  
  /**
   * Gets the urgency infos of persons with the same name.
   *
   * @param firstName the person's first name
   * @param lastName the person's last name
   * @return the urgency infos of persons with the same name
   */
  public List<PersonInfoDto> getPersonInfoList(String firstName, String lastName);
  
  /**
   * Save a new medical record.
   *
   * @param medicalRecord the new medical record
   * @return the medical record saved if successful, null if not
   */
  public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord);

  /**
   * Update a medical record.
   *
   * @param medicalRecord the medical record
   * @return the medical record updated if successful, null if not
   */
  public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord);

  /**
   * Delete the medical record of a person.
   *
   * @param firstName the person's first name
   * @param lastName the person's last name
   * @return true, if successful
   */
  public boolean deleteMedicalRecord(String firstName, String lastName);

  
}