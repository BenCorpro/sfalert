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

import com.safetynet.sfalert.dto.ChildAlertDto;
import com.safetynet.sfalert.service.impl.ChildAlertService;

@SpringBootTest
public class ChildAlertServiceTest {

  @Autowired
  ChildAlertService childAlertService;
  
  @Test
  public void testGetChilds() throws Exception{
    Map<String, List<ChildAlertDto>> result = childAlertService.getChilds("947 E. Rose Dr");
    assertNotNull(result);
    assertEquals(2, result.size());
    assertTrue(result.containsKey("Adults at address"));
    assertEquals("Kendrik", result.get("Childs at address").get(0).getFirstName());
  }
  
  @Test
  public void testGetChildsAddressWithoutChilds() throws Exception{
    Map<String, List<ChildAlertDto>> result = childAlertService.getChilds("908 73rd St");
    assertNotNull(result);
    assertTrue(result.isEmpty());
  }
  
  @Test
  public void testGetChildsUnknownAddress() throws Exception{
    Map<String, List<ChildAlertDto>> result = childAlertService.getChilds("15 rue du Bac");
    assertNull(result);
  }
}
