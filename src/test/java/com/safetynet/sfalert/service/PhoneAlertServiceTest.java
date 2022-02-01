package com.safetynet.sfalert.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.sfalert.service.impl.PhoneAlertService;

@SpringBootTest
public class PhoneAlertServiceTest {

  @Autowired
  PhoneAlertService phoneAlertService;
  
  @Test
  public void testPhoneNumberList() throws Exception{
    List<String> result = phoneAlertService.phoneNumberList("3");
    assertNotNull(result);
    assertEquals(8, result.size());
    assertTrue(result.contains("841-874-6512"));
  }
  
  @Test
  public void testPhoneNumberListUnknownStation() throws Exception{
    List<String> result = phoneAlertService.phoneNumberList("11");
    assertNull(result);
  }
}
