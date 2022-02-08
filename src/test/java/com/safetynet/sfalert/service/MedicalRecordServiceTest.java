package com.safetynet.sfalert.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.safetynet.sfalert.model.Json;
import com.safetynet.sfalert.model.MedicalRecord;
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
    List<String> allergiesTenley = new ArrayList<String>();
    allergiesTenley.add("peanut");  
    listMedicalRecordsTest.add(new MedicalRecord("Tenley", "Boyd", "02/18/2012", new ArrayList<String>(),allergiesTenley));
    listMedicalRecordsTest.add(new MedicalRecord("Tessa", "Carman", "02/18/2012", new ArrayList<String>(), new ArrayList<String>()));
    listMedicalRecordsTest.add(new MedicalRecord("Jamie","Peters", "03/06/1982", new ArrayList<String>(), new ArrayList<String>()));
    when(json.getMedicalrecords()).thenReturn(listMedicalRecordsTest);
  }
  
  @Test
  public void testSaveMedicalRecord() throws Exception{
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
  public void testSaveMedicalRecordDuplicate() throws Exception{
    List<String> medicationsTest = new ArrayList<String>();
    medicationsTest.add("curcuma:500mg");
    medicationsTest.add("origan:250mg");  
    List<String> allergiesTest = new ArrayList<String>();
    allergiesTest.add("fraise");  
    MedicalRecord medicalRecordTest = new MedicalRecord("Tessa", "Carman", "02/06/1889", medicationsTest, allergiesTest);
    MedicalRecord result = medicalRecordService.saveMedicalRecord(medicalRecordTest);
    assertNull(result);
  }
  
  @Test
  public void testUpdateMedicalRecord() throws Exception{
    List<String> medicationsTest = new ArrayList<String>();
    medicationsTest.add("curcuma:500mg");
    medicationsTest.add("origan:250mg");  
    List<String> allergiesTest = new ArrayList<String>();
    allergiesTest.add("fraise");  
    MedicalRecord medicalRecordTest = new MedicalRecord("Tenley", "Boyd", "02/06/1889", medicationsTest, allergiesTest);
    MedicalRecord result = medicalRecordService.updateMedicalRecord(medicalRecordTest);
    assertNotNull(result);
    assertTrue(result instanceof MedicalRecord);
    assertEquals("02/06/1889", result.getBirthdate());
  }
  
  @Test
  public void testUpdateMedicalRecordUnknown() throws Exception{
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
  public void testDeleteMedicalRecord() throws Exception{
    boolean result = medicalRecordService.deleteMedicalRecord("Jamie", "Peters");
    assertTrue(result);
  }
  
  @Test
  public void testDeleteMedicalRecordUnknown() throws Exception{
    boolean result = medicalRecordService.deleteMedicalRecord("Florian", "Lepreux");
    assertFalse(result);
  }
}
