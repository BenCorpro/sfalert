package com.safetynet.sfalert.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.sfalert.service.impl.FireStationAreaService;

@SpringBootTest
public class FireStationAreaServiceTest {

  @Autowired
  FireStationAreaService fireStationAreaService;
  
  @Test
  public void testGetStationPeople() throws Exception{
    Map<String, Object> result = fireStationAreaService.getStationPeople("2");
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
