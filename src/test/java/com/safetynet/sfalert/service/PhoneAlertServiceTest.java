package com.safetynet.sfalert.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.safetynet.sfalert.model.FireStation;
import com.safetynet.sfalert.model.Json;
import com.safetynet.sfalert.model.Person;
import com.safetynet.sfalert.service.impl.PhoneAlertService;

@SpringBootTest
public class PhoneAlertServiceTest {

  @Autowired
  private PhoneAlertService phoneAlertService;
  
  @MockBean
  private Json json;
  
  @BeforeEach
  public void setUp() {
    List<Person> listPersonsTestOne = new ArrayList<Person>();
    List<Person> listPersonsTestTwo = new ArrayList<Person>();
    listPersonsTestOne.add(new Person("Brian", "Stelzer", "947 E. Rose Dr", "Culver", "97451", "841-874-7784", "bstel@email.com"));
    listPersonsTestOne.add(new Person("Shawna", "Stelzer", "947 E. Rose Dr", "Culver", "97451", "841-874-7784", "ssanw@email.com"));
    listPersonsTestOne.add(new Person("Kendrik", "Stelzer", "947 E. Rose Dr", "Culver", "97451", "841-874-7784", "bstel@email.com"));
    listPersonsTestTwo.add(new Person("Eric", "Cadigan", "951 LoneTree Rd", "Culver", "97451", "841-874-7458", "gramps@email.com"));
    List<FireStation> listFireStationsTest = new ArrayList<FireStation>();
    listFireStationsTest.add(new FireStation("947 E. Rose Dr", "1", listPersonsTestOne));
    listFireStationsTest.add(new FireStation("951 LoneTree Rd", "2", listPersonsTestTwo));
    when(json.getFirestations()).thenReturn(listFireStationsTest);
  }
  
  @Test
  public void testPhoneNumberList() throws Exception{
    List<String> result = phoneAlertService.phoneNumberList("2");
    assertNotNull(result);
    assertEquals(1, result.size());
    assertTrue(result.contains("841-874-7458"));
  }
  
  @Test
  public void testPhoneNumberListUnknownStation() throws Exception{
    List<String> result = phoneAlertService.phoneNumberList("11");
    assertNull(result);
  }
}
