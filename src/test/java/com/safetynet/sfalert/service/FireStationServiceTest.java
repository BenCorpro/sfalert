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

import com.safetynet.sfalert.dto.FireAndFloodAlertDto;
import com.safetynet.sfalert.model.FireStation;
import com.safetynet.sfalert.model.Json;
import com.safetynet.sfalert.model.MedicalRecord;
import com.safetynet.sfalert.model.Person;
import com.safetynet.sfalert.service.impl.FireStationService;


@SpringBootTest
public class FireStationServiceTest {

  @Autowired
  private FireStationService fireStationService;
  
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
    List<Person> listPersonsTest = new ArrayList<Person>();
    listPersonsTest.addAll(listPersonsTestOne);
    listPersonsTest.addAll(listPersonsTestTwo);
    List<FireStation> listFireStationsTest = new ArrayList<FireStation>();
    listFireStationsTest.add(new FireStation("947 E. Rose Dr", "1", listPersonsTestOne));
    listFireStationsTest.add(new FireStation("951 LoneTree Rd", "2", listPersonsTestTwo));
    listFireStationsTest.add(new FireStation("489 Manchester St", "4"));
    listFireStationsTest.add(new FireStation("748 Townings Dr", "3"));
    when(json.getFirestations()).thenReturn(listFireStationsTest);
    when(json.getPersons()).thenReturn(listPersonsTest);
  }
  
  
  @Test
  public void getStationPeopleList_CorrectStation_ReturnsStationPeopleList() throws Exception{
    Map<String, Object> result = fireStationService.getStationPeopleList("1");
    assertNotNull(result);
    assertEquals(3, result.size());
    assertTrue(result.containsKey("Persons covered"));
    assertEquals(1, result.get("Number of childs"));
  }
  
  @Test
  public void getStationPeopleList_UnknownStation_ReturnsNull() throws Exception{
    Map<String, Object> result = fireStationService.getStationPeopleList("8");
    assertNull(result);
  }
  
  @Test
  public void getPhoneNumberList_CorrectStation_ReturnsPhoneNumberList() throws Exception{
    List<String> result = fireStationService.getPhoneNumberList("2");
    assertNotNull(result);
    assertEquals(1, result.size());
    assertTrue(result.contains("841-874-7458"));
  }
  
  @Test
  public void getPhoneNumberList_UnknownStation_ReturnsNull() throws Exception{
    List<String> result = fireStationService.getPhoneNumberList("11");
    assertNull(result);
  }
  
  @Test
  public void getFireListAddress_CorrectAddress_ReturnsFireList() throws Exception{
    Map<String, List<FireAndFloodAlertDto>> result = fireStationService.getFireListAddress("947 E. Rose Dr");
    assertNotNull(result);
    assertEquals(1, result.size());
    assertTrue(result.containsKey("station 1"));
    assertEquals("Stelzer", result.get("station 1").get(0).getLastName());
  }
  
  @Test
  public void getFireListAddress_UnknownAddress_ReturnsNull() throws Exception{
    Map<String, List<FireAndFloodAlertDto>> result = fireStationService.getFireListAddress("56 avenue Richelieu");
    assertNull(result);
  }
  
  @Test
  public void getFloodListStation_CorrectStations_ReturnsFloodList() throws Exception{
    List<String> stationList = new ArrayList<String>();
    stationList.add("1");
    stationList.add("2");
    Map<String, List<FireAndFloodAlertDto>> result = fireStationService.getFloodListStation(stationList);
    assertNotNull(result);
    assertEquals(2, result.size());
    assertTrue(result.containsKey("947 E. Rose Dr"));
    assertEquals("Eric", result.get("951 LoneTree Rd").get(0).getFirstName());
  }
  
  @Test
  public void getFloodListStation_UnknownStations_ReturnsNull() throws Exception{
    List<String> stationList = new ArrayList<String>();
    stationList.add("9");
    stationList.add("5");
    Map<String, List<FireAndFloodAlertDto>> result = fireStationService.getFloodListStation(stationList);
    assertNull(result);
  }
  
  
  @Test
  public void saveFireStation_CorrectInfos_ReturnsFireStation() throws Exception{
    FireStation fireStationTest = new FireStation("34 impasse du Collier", "3");
    FireStation result = fireStationService.saveFireStation(fireStationTest);
    assertNotNull(result);
    assertTrue(result instanceof FireStation);
    assertEquals("34 impasse du Collier", result.getAddress());
  }
  
  @Test
  public void saveFireStation_DuplicateFireStation_ReturnsNull() throws Exception{
    FireStation fireStationTest = new FireStation("951 LoneTree Rd", "2");
    FireStation result = fireStationService.saveFireStation(fireStationTest);
    assertNull(result);
  }
  
  @Test
  public void updateFireStation_CorrectAddress_ReturnsFireStation() throws Exception{
    FireStation fireStationTest = new FireStation("748 Townings Dr", "6");
    FireStation result = fireStationService.updateFireStation(fireStationTest);
    assertNotNull(result);
    assertTrue(result instanceof FireStation);
    assertEquals("6", result.getStation());
  }
  
  @Test
  public void updateFireStation_UnknownAddress_ReturnsNull() throws Exception{
    FireStation fireStationTest = new FireStation("4 boulevard Caribou", "3");
    FireStation result = fireStationService.updateFireStation(fireStationTest);
    assertNull(result);
  }
  
  @Test
  public void deleteFireStation_CorrectInfos_ReturnsTrue() throws Exception{
    boolean result = fireStationService.deleteFireStation("489 Manchester St");
    assertTrue(result);
  }
  
  @Test
  public void deleteFireStation_UnknownStation_ReturnsFalse() throws Exception{
    boolean result = fireStationService.deleteFireStation("8");
    assertFalse(result);
  }
 
  
}
