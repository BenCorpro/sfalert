package com.safetynet.sfalert.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.sfalert.dto.FireAndFloodAlertDto;
import com.safetynet.sfalert.service.impl.FloodAlertService;

@SpringBootTest
public class FloodAlertServiceTest {

  @Autowired
  FloodAlertService floodAlertService;
  
  @Test
  public void testfloodListStation() throws Exception{
    List<String> stationList = new ArrayList<String>();
    stationList.add("2");
    stationList.add("3");
    Map<String, List<FireAndFloodAlertDto>> result = floodAlertService.floodListStation(stationList);
    assertNotNull(result);
    assertEquals(8, result.size());
    assertTrue(result.containsKey("834 Binoc Ave"));
    assertEquals("Eric", result.get("951 LoneTree Rd").get(0).getFirstName());
  }
  
  @Test
  public void testfloodListStationUnknownStations() throws Exception{
    List<String> stationList = new ArrayList<String>();
    stationList.add("9");
    stationList.add("5");
    Map<String, List<FireAndFloodAlertDto>> result = floodAlertService.floodListStation(stationList);
    assertNull(result);
  }
}
