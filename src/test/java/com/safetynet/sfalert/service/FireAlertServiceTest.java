package com.safetynet.sfalert.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.sfalert.dto.FireAndFloodAlertDto;
import com.safetynet.sfalert.service.impl.FireAlertService;

@SpringBootTest
public class FireAlertServiceTest {

  @Autowired
  FireAlertService fireAlertService;
  
  @Test
  public void testFireListAddress() throws Exception{
    Map<String, List<FireAndFloodAlertDto>> result = fireAlertService.fireListAddress("892 Downing Ct");
    assertNotNull(result);
    assertEquals(1, result.size());
    assertTrue(result.containsKey("station: 2"));
    assertEquals("Zemicks", result.get("station: 2").get(0).getLastName());
  }
  
  @Test
  public void testFireListAddressUnknownAddress() throws Exception{
    Map<String, List<FireAndFloodAlertDto>> result = fireAlertService.fireListAddress("56 avenue Richelieu");
    assertNull(result);
  }
}
