package com.safetynet.sfalert.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.sfalert.service.impl.EmailService;

@SpringBootTest
public class EmailServiceTest {

  @Autowired
  EmailService emailService;
  
  @Test
  public void testGetEmailList() throws Exception{
    List<String> result = emailService.getEmailList("Culver");
    assertNotNull(result);
    assertEquals(23, result.size());
    assertTrue(result.contains("jaboyd@email.com"));
  }
  
  @Test
  public void testGetEmailListUnknownCity() throws Exception{
    List<String> result = emailService.getEmailList("Pitivier");
    assertNull(result);
  }
}
