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

import com.safetynet.sfalert.model.FireStation;
import com.safetynet.sfalert.model.Json;
import com.safetynet.sfalert.service.impl.FireStationService;

@SpringBootTest
public class FireStationServiceTest {

  @Autowired
  private FireStationService fireStationService;
  
  @MockBean
  private Json json;
  
  @BeforeEach
  public void setUp() {
    List<FireStation> listFireStationsTest = new ArrayList<FireStation>();
    listFireStationsTest.add(new FireStation("644 Gershwin Cir", "1"));
    listFireStationsTest.add(new FireStation("489 Manchester St", "4"));
    listFireStationsTest.add(new FireStation("834 Binoc Ave", "3"));
    when(json.getFirestations()).thenReturn(listFireStationsTest);
  }
  
  @Test
  public void testSaveFireStation() throws Exception{
    FireStation fireStationTest = new FireStation("34 impasse du Collier", "3");
    FireStation result = fireStationService.saveFireStation(fireStationTest);
    assertNotNull(result);
    assertTrue(result instanceof FireStation);
    assertEquals("34 impasse du Collier", result.getAddress());
  }
  
  @Test
  public void testSaveFireStationDuplicate() throws Exception{
    FireStation fireStationTest = new FireStation("834 Binoc Ave", "3");
    FireStation result = fireStationService.saveFireStation(fireStationTest);
    assertNull(result);
  }
  
  @Test
  public void testUpdateFireStation() throws Exception{
    FireStation fireStationTest = new FireStation("644 Gershwin Cir", "6");
    FireStation result = fireStationService.updateFireStation(fireStationTest);
    assertNotNull(result);
    assertTrue(result instanceof FireStation);
    assertEquals("6", result.getStation());
  }
  
  @Test
  public void testUpdateFireStationUnknown() throws Exception{
    FireStation fireStationTest = new FireStation("4 boulevard Caribou", "3");
    FireStation result = fireStationService.updateFireStation(fireStationTest);
    assertNull(result);
  }
  
  @Test
  public void testDeleteFireStation() throws Exception{
    boolean result = fireStationService.deleteFireStation("489 Manchester St");
    assertTrue(result);
  }
  
  @Test
  public void testDeleteFireStationUnknown() throws Exception{
    boolean result = fireStationService.deleteFireStation("8");
    assertFalse(result);
  }
  
}
