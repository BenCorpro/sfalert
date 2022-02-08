package com.safetynet.sfalert.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.safetynet.sfalert.model.FireStation;
import com.safetynet.sfalert.model.Json;
import com.safetynet.sfalert.model.MedicalRecord;
import com.safetynet.sfalert.model.Person;
import com.safetynet.sfalert.service.impl.FireStationAreaService;

@SpringBootTest
public class FireStationAreaServiceTest {

  @Autowired
  private FireStationAreaService fireStationAreaService;
  
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
    List<Person> listPersonsTestOne = new ArrayList<Person>();
    List<Person> listPersonsTestTwo = new ArrayList<Person>();
    listPersonsTestOne.add(new Person("Brian", "Stelzer", "947 E. Rose Dr", "Culver", "97451", "841-874-7784", "bstel@email.com", listMedicalRecordsTest.get(0)));
    listPersonsTestOne.add(new Person("Shawna", "Stelzer", "947 E. Rose Dr", "Culver", "97451", "841-874-7784", "ssanw@email.com", listMedicalRecordsTest.get(1)));
    listPersonsTestOne.add(new Person("Kendrik", "Stelzer", "947 E. Rose Dr", "Culver", "97451", "841-874-7784", "bstel@email.com", listMedicalRecordsTest.get(2)));
    listPersonsTestTwo.add(new Person("Eric", "Cadigan", "951 LoneTree Rd", "Culver", "97451", "841-874-7458", "gramps@email.com", listMedicalRecordsTest.get(3)));
    List<FireStation> listFireStationsTest = new ArrayList<FireStation>();
    listFireStationsTest.add(new FireStation("947 E. Rose Dr", "1", listPersonsTestOne));
    listFireStationsTest.add(new FireStation("951 LoneTree Rd", "2", listPersonsTestTwo));
    when(json.getFirestations()).thenReturn(listFireStationsTest);
  }
  
  @Test
  public void testGetStationPeople() throws Exception{
    Map<String, Object> result = fireStationAreaService.getStationPeople("1");
    assertNotNull(result);
    assertEquals(3, result.size());
    assertTrue(result.containsKey("Persons covered"));
    assertEquals(1, result.get("Number of childs"));
  }
  
  @Test
  public void testGetStationPeopleUnknownStation() throws Exception{
    Map<String, Object> result = fireStationAreaService.getStationPeople("8");
    assertNull(result);
  }
  
}
