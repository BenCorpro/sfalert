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

import com.safetynet.sfalert.model.Json;
import com.safetynet.sfalert.model.Person;
import com.safetynet.sfalert.service.impl.PersonService;

@SpringBootTest
public class PersonServiceTest {

  @Autowired
  private PersonService personService;
  
  @MockBean
  private Json json;
  
  @BeforeEach
  public void setUp() {
    List<Person> listPersonsTest = new ArrayList<Person>();
    listPersonsTest.add(new Person("Foster", "Shepard", "748 Townings Dr", "Culver", "97451", "841-874-6544", "jaboyd@email.com"));
    listPersonsTest.add(new Person("Zach", "Zemicks", "892 Downing Ct","Culver", "97451", "841-874-7512", "zarc@email.com"));
    listPersonsTest.add(new Person("Brian","Stelzer", "947 E. Rose Dr", "Culver", "97451", "841-874-7784", "bstel@email.com"));
    when(json.getPersons()).thenReturn(listPersonsTest);
  }
  
  @Test
  public void testSavePerson() throws Exception{
    Person personTest = new Person("Jean", "Valjean", "22 rue de la Pompe", "Paris", "75018", "0609840321", "jvaljean@email.fr");
    Person result = personService.savePerson(personTest);
    assertNotNull(result);
    assertTrue(result instanceof Person);
    assertEquals("Valjean", result.getLastName());
  }
  
  @Test
  public void testSavePersonDuplicate() throws Exception{
    Person personTest = new Person("Zach", "Zemicks", "22 rue de la Pompe", "Paris", "75018", "0609840321", "jvaljean@email.fr");
    Person result = personService.savePerson(personTest);
    assertNull(result);
  }
  
  @Test
  public void testUpdatePerson() throws Exception{
    Person personTest = new Person("Brian", "Stelzer", "22 rue de la Pompe", "Paris", "75018", "0609840321", "jvaljean@email.fr");
    Person result = personService.updatePerson(personTest);
    assertNotNull(result);
    assertTrue(result instanceof Person);
    assertEquals("Paris", result.getCity());
  }
  
  @Test
  public void testUpdatePersonUnkwon() throws Exception{
    Person personTest = new Person("Remy", "Bouchard", "22 rue de la Pompe", "Paris", "75018", "0609840321", "jvaljean@email.fr");
    Person result = personService.updatePerson(personTest);
    assertNull(result);
  }
  
  @Test
  public void testDeletePerson() throws Exception{
    boolean result = personService.deletePerson("Foster", "Shepard");
    assertTrue(result);
  }
  
  @Test
  public void testDeletePersonUnkwon() throws Exception{
    boolean result = personService.deletePerson("Florian", "Lepreux");
    assertFalse(result);
  }
}
