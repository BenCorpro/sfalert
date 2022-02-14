package com.safetynet.sfalert.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.safetynet.sfalert.dto.ChildAlertDto;
import com.safetynet.sfalert.dto.PersonInfoDto;
import com.safetynet.sfalert.model.Json;
import com.safetynet.sfalert.model.MedicalRecord;
import com.safetynet.sfalert.model.Person;
import com.safetynet.sfalert.service.impl.MedicalRecordService;


@SpringBootTest
public class MedicalRecordServiceTest {

  @Autowired
  private MedicalRecordService medicalRecordService;
  
  @MockBean
  private Json json;
  
  @BeforeEach
  public void setUp() {
    List<MedicalRecord> listMedicalRecordsTest = new ArrayList<MedicalRecord>();
    List<String> medicationsBrian = new ArrayList<String>();
    medicationsBrian.add("ibupurin:200mg");
    medicationsBrian.add("hydrapermazol:400mg");
    List<String> medicationsKendrik = new ArrayList<String>();
    medicationsKendrik.add("noxidian:100mg");
    medicationsKendrik.add("pharmacol:2500mg");
    List<String> medicationsEric = new ArrayList<String>();
    medicationsKendrik.add("tradoxidine:400mg");
    List<String> allergiesBrian = new ArrayList<String>();
    allergiesBrian.add("nillacilan");  
    listMedicalRecordsTest.add(new MedicalRecord("Brian", "Stelzer", "12/06/1975", medicationsBrian, allergiesBrian));
    listMedicalRecordsTest.add(new MedicalRecord("Shawna", "Stelzer", "07/08/1980", new ArrayList<String>(), new ArrayList<String>()));
    listMedicalRecordsTest.add(new MedicalRecord("Kendrik", "Stelzer", "03/06/2014", medicationsKendrik, new ArrayList<String>()));
    listMedicalRecordsTest.add(new MedicalRecord("Eric", "Cadigan", "08/06/1945", medicationsEric, new ArrayList<String>()));
    listMedicalRecordsTest.add(new MedicalRecord("Clive", "Ferguson", "03/06/1994", new ArrayList<String>(), new ArrayList<String>()));
    List<Person> listPersonsTest = new ArrayList<Person>();
    listPersonsTest.add(new Person("Brian", "Stelzer", "947 E. Rose Dr", "Culver", "97451", "841-874-7784", "bstel@email.com", listMedicalRecordsTest.get(0)));
    listPersonsTest.add(new Person("Shawna", "Stelzer", "947 E. Rose Dr", "Culver", "97451", "841-874-7784", "ssanw@email.com", listMedicalRecordsTest.get(1)));
    listPersonsTest.add(new Person("Kendrik", "Stelzer", "947 E. Rose Dr", "Culver", "97451", "841-874-7784", "bstel@email.com", listMedicalRecordsTest.get(2)));
    listPersonsTest.add(new Person("Eric", "Cadigan", "951 LoneTree Rd", "Culver", "97451", "841-874-7458", "gramps@email.com", listMedicalRecordsTest.get(3)));
    when(json.getMedicalrecords()).thenReturn(listMedicalRecordsTest);
    when(json.getPersons()).thenReturn(listPersonsTest);
  }
  
  
  @Test
  public void getChildAlertList_CorrectAddress_ReturnsChildAlertList() throws Exception{
    Map<String, List<ChildAlertDto>> result = medicalRecordService.getChildAlertList("947 E. Rose Dr");
    assertNotNull(result);
    assertEquals(2, result.size());
    assertTrue(result.containsKey("Adults at address"));
    assertEquals("Kendrik", result.get("Childs at address").get(0).getFirstName());
  }
  
  @Test
  public void getChildAlertList_AddressWithoutChilds_ReturnsEmptyList() throws Exception{
    Map<String, List<ChildAlertDto>> result = medicalRecordService.getChildAlertList("951 LoneTree Rd");
    assertNotNull(result);
    assertTrue(result.isEmpty());
  }
  
  @Test
  public void getChildAlertList_UnknownAddress_ReturnsENull() throws Exception{
    Map<String, List<ChildAlertDto>> result = medicalRecordService.getChildAlertList("15 rue du Bac");
    assertNull(result);
  }
  
  @Test
  public void getPersonInfoList_CorrectNames_ReturnsPersonInfoList() throws Exception{
    List<PersonInfoDto> result = medicalRecordService.getPersonInfoList("Kendrik", "Stelzer");
    assertNotNull(result);
    assertEquals(3, result.size());
    assertEquals("bstel@email.com", result.get(0).getEmail());
  }
  
  @Test
  public void getPersonInfoList_UnknownNames_ReturnsNull() throws Exception{
    List<PersonInfoDto> result = medicalRecordService.getPersonInfoList("Horace", "DeSaxe");
    assertNull(result);
  }
  
  
  @Test
  public void saveMedicalRecord_CorrectInfos_ReturnsMedicalRecord() throws Exception{
    List<String> medicationsTest = new ArrayList<String>();
    medicationsTest.add("curcuma:500mg");
    medicationsTest.add("origan:250mg");  
    List<String> allergiesTest = new ArrayList<String>();
    allergiesTest.add("fraise");  
    MedicalRecord medicalRecordTest = new MedicalRecord("Jean", "Valjean", "02/06/1889", medicationsTest, allergiesTest);
    MedicalRecord result = medicalRecordService.saveMedicalRecord(medicalRecordTest);
    assertNotNull(result);
    assertTrue(result instanceof MedicalRecord);
    assertEquals("Jean", result.getFirstName());
  }
  
  @Test
  public void saveMedicalRecord_DuplicateNames_ReturnsNull() throws Exception{
    List<String> medicationsTest = new ArrayList<String>();
    medicationsTest.add("curcuma:500mg");
    medicationsTest.add("origan:250mg");  
    List<String> allergiesTest = new ArrayList<String>();
    allergiesTest.add("fraise");  
    MedicalRecord medicalRecordTest = new MedicalRecord("Shawna", "Stelzer", "07/08/1980", medicationsTest, allergiesTest);
    MedicalRecord result = medicalRecordService.saveMedicalRecord(medicalRecordTest);
    assertNull(result);
  }
  
  @Test
  public void updateMedicalRecord_CorrectInfos_ReturnsMedicalRecord() throws Exception{
    List<String> medicationsTest = new ArrayList<String>();
    medicationsTest.add("curcuma:500mg");
    medicationsTest.add("origan:250mg");  
    List<String> allergiesTest = new ArrayList<String>();
    allergiesTest.add("fraise");  
    MedicalRecord medicalRecordTest = new MedicalRecord("Brian", "Stelzer", "24/03/1968", medicationsTest, allergiesTest);
    MedicalRecord result = medicalRecordService.updateMedicalRecord(medicalRecordTest);
    assertNotNull(result);
    assertTrue(result instanceof MedicalRecord);
    assertEquals("24/03/1968", result.getBirthdate());
  }
  
  @Test
  public void updateMedicalRecord_UnknownNames_ReturnsNull() throws Exception{
    List<String> medicationsTest = new ArrayList<String>();
    medicationsTest.add("curcuma:500mg");
    medicationsTest.add("origan:250mg");  
    List<String> allergiesTest = new ArrayList<String>();
    allergiesTest.add("fraise");  
    MedicalRecord medicalRecordTest = new MedicalRecord("Remy", "Bouchard", "02/06/1889", medicationsTest, allergiesTest);
    MedicalRecord result = medicalRecordService.updateMedicalRecord(medicalRecordTest);
    assertNull(result);
  }
  
  @Test
  public void deleteMedicalRecord_CorrectNames_ReturnsTrue() throws Exception{
    boolean result = medicalRecordService.deleteMedicalRecord("Clive", "Ferguson");
    assertTrue(result);
  }
  
  @Test
  public void deleteMedicalRecord_UnknownNames_ReturnsFalse() throws Exception{
    boolean result = medicalRecordService.deleteMedicalRecord("Florian", "Lepreux");
    assertFalse(result);
  }
  
  
}
