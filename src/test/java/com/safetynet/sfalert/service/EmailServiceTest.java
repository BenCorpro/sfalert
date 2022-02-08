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

import com.safetynet.sfalert.model.Json;
import com.safetynet.sfalert.model.Person;
import com.safetynet.sfalert.service.impl.EmailService;

@SpringBootTest
public class EmailServiceTest {

  @Autowired
  private EmailService emailService;
  
  @MockBean
  private Json json;
  
  @BeforeEach
  public void setUp() {
    List<Person> listPersonsTest = new ArrayList<Person>();
    listPersonsTest.add(new Person("Brian", "Stelzer", "947 E. Rose Dr", "Culver", "97451", "841-874-7784", "bstel@email.com"));
    listPersonsTest.add(new Person("Shawna", "Stelzer", "947 E. Rose Dr", "Culver", "97451", "841-874-7784", "ssanw@email.com"));
    listPersonsTest.add(new Person("Kendrik", "Stelzer", "947 E. Rose Dr", "Culver", "97451", "841-874-7784", "bstel@email.com"));
    listPersonsTest.add(new Person("Eric", "Cadigan", "951 LoneTree Rd", "Culver", "97451", "841-874-7458", "gramps@email.com"));
    when(json.getPersons()).thenReturn(listPersonsTest);
  }
  
  @Test
  public void testGetEmailList() throws Exception{
    List<String> result = emailService.getEmailList("Culver");
    assertNotNull(result);
    assertEquals(4, result.size());
    assertTrue(result.contains("ssanw@email.com"));
  }
  
  @Test
  public void testGetEmailListUnknownCity() throws Exception{
    List<String> result = emailService.getEmailList("Pitivier");
    assertNull(result);
  }
}
