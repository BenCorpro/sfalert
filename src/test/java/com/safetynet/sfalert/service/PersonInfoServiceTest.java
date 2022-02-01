package com.safetynet.sfalert.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.sfalert.dto.PersonInfoDto;
import com.safetynet.sfalert.service.impl.PersonInfoService;

@SpringBootTest
public class PersonInfoServiceTest {

  @Autowired
  PersonInfoService personInfoService;
  
  @Test
  public void testGetPersonInfo() throws Exception{
    List<PersonInfoDto> result = personInfoService.getPersonInfo("Tessa", "Carman");
    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals("tenz@email.com", result.get(0).getEmail());
  }
  
  @Test
  public void testGetPersonInfoUnknownNames() throws Exception{
    List<PersonInfoDto> result = personInfoService.getPersonInfo("Jean", "Valjean");
    assertNull(result);
  }
}
