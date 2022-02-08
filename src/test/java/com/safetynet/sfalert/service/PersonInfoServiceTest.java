package com.safetynet.sfalert.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.safetynet.sfalert.dto.PersonInfoDto;
import com.safetynet.sfalert.model.Json;
import com.safetynet.sfalert.model.MedicalRecord;
import com.safetynet.sfalert.model.Person;
import com.safetynet.sfalert.service.impl.PersonInfoService;

@SpringBootTest
public class PersonInfoServiceTest {

  @Autowired
  private PersonInfoService personInfoService;
  
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
    List<Person> listPersonsTest = new ArrayList<Person>();
    listPersonsTest.add(new Person("Brian", "Stelzer", "947 E. Rose Dr", "Culver", "97451", "841-874-7784", "bstel@email.com", listMedicalRecordsTest.get(0)));
    listPersonsTest.add(new Person("Shawna", "Stelzer", "947 E. Rose Dr", "Culver", "97451", "841-874-7784", "ssanw@email.com", listMedicalRecordsTest.get(1)));
    listPersonsTest.add(new Person("Kendrik", "Stelzer", "947 E. Rose Dr", "Culver", "97451", "841-874-7784", "bstel@email.com", listMedicalRecordsTest.get(2)));
    listPersonsTest.add(new Person("Eric", "Cadigan", "951 LoneTree Rd", "Culver", "97451", "841-874-7458", "gramps@email.com", listMedicalRecordsTest.get(3)));
    when(json.getPersons()).thenReturn(listPersonsTest);
  }
  
  @Test
  public void testGetPersonInfo() throws Exception{
    List<PersonInfoDto> result = personInfoService.getPersonInfo("Kendrik", "Stelzer");
    assertNotNull(result);
    assertEquals(3, result.size());
    assertEquals("bstel@email.com", result.get(0).getEmail());
  }
  
  @Test
  public void testGetPersonInfoUnknownNames() throws Exception{
    List<PersonInfoDto> result = personInfoService.getPersonInfo("Jean", "Valjean");
    assertNull(result);
  }
}
