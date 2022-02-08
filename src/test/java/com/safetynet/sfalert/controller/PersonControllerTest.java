package com.safetynet.sfalert.controller;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.sfalert.model.Person;
import com.safetynet.sfalert.service.impl.ChildAlertService;
import com.safetynet.sfalert.service.impl.EmailService;
import com.safetynet.sfalert.service.impl.PersonInfoService;
import com.safetynet.sfalert.service.impl.PersonService;

@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTest {

  @Autowired
  private MockMvc mockMvc;
  
  @Autowired
  private ObjectMapper objectMapper;
  
  @MockBean
  private PersonService personService;
  @MockBean
  private ChildAlertService childAlertService;
  @MockBean
  private PersonInfoService personInfoService;
  @MockBean
  private EmailService emailService;
  
  
  @Test
  public void testGetChildAlert() throws Exception{
    mockMvc.perform(get("/childAlert?address=892 Downing Ct"))
           .andExpect(status().isOk()); 
  }
  
  @Test
  public void testGetPersonInfo() throws Exception{
    mockMvc.perform(get("/personInfo?firstName=Jamie&lastName=Peters"))
           .andExpect(status().isOk()); 
  }
  
  @Test
  public void testGetEmails() throws Exception{
    mockMvc.perform(get("/communityEmail?city=Culver"))
           .andExpect(status().isOk()); 
  }
  
  @Test
  public void testAddPerson() throws Exception{
    Person person = new Person("Foster", "Shepard", "748 Townings Dr", "Culver", "97451", "841-874-6544", "jaboyd@email.com");
    when(personService.savePerson(any(Person.class))).thenReturn(person);
    mockMvc.perform(MockMvcRequestBuilders.post("/person")
            .content(objectMapper.writeValueAsString(person))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
  }
  
  @Test
  public void testAddPersonDuplicate() throws Exception{
    Person person = new Person("Foster", "Shepard", "748 Townings Dr", "Culver", "97451", "841-874-6544", "jaboyd@email.com");
    when(personService.savePerson(any(Person.class))).thenReturn(null);
    mockMvc.perform(MockMvcRequestBuilders.post("/person")
            .content(objectMapper.writeValueAsString(person))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
  }
  
  @Test
  public void testUpdatePerson() throws Exception{
    Person person = new Person("Foster", "Shepard", "748 Townings Dr", "Culver", "97451", "841-874-6544", "jaboyd@email.com");
    when(personService.updatePerson(any(Person.class))).thenReturn(person);
    mockMvc.perform(MockMvcRequestBuilders.put("/person")
            .content(objectMapper.writeValueAsString(person))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }
  
  @Test
  public void testUpdatePersonUnknown() throws Exception{
    Person person = new Person("Foster", "Shepard", "748 Townings Dr", "Culver", "97451", "841-874-6544", "jaboyd@email.com");
    when(personService.updatePerson(any(Person.class))).thenReturn(null);
    mockMvc.perform(MockMvcRequestBuilders.put("/person")
            .content(objectMapper.writeValueAsString(person))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
  }
  
  @Test
  public void testDeletePerson() throws Exception{
    when(personService.deletePerson("Jamie","Peters")).thenReturn(true);
    mockMvc.perform(delete("/person?firstName=Jamie&lastName=Peters"))
           .andExpect(status().isOk()); 
  }
  
  @Test
  public void testDeletePersonUnknown() throws Exception{
    when(personService.deletePerson("Jamie","Peters")).thenReturn(false);
    mockMvc.perform(delete("/person?firstName=Jamie&lastName=Peters"))
           .andExpect(status().isNotFound()); 
  }
}
