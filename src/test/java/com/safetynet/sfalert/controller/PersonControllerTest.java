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
import com.safetynet.sfalert.service.impl.PersonService;


@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTest {

  @Autowired
  private MockMvc mockMvc;
  
  @Autowired
  private ObjectMapper objectMapper;
  
  @MockBean
  private PersonService personService;
  

  @Test
  public void eMail_CorrectCity_ReturnsOkStatus() throws Exception{
    mockMvc.perform(get("/communityEmail?city=Culver"))
           .andExpect(status().isOk()); 
  }
  
  @Test
  public void addPerson_CorrectInfos_ReturnsCreatedStatus() throws Exception{
    Person person = new Person("Foster", "Shepard", "748 Townings Dr", "Culver", "97451", "841-874-6544", "jaboyd@email.com");
    when(personService.savePerson(any(Person.class))).thenReturn(person);
    mockMvc.perform(MockMvcRequestBuilders.post("/person")
            .content(objectMapper.writeValueAsString(person))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
  }
  
  @Test
  public void addPerson_DuplicatePerson_ReturnsNoContentStatus() throws Exception{
    Person person = new Person("Foster", "Shepard", "748 Townings Dr", "Culver", "97451", "841-874-6544", "jaboyd@email.com");
    when(personService.savePerson(any(Person.class))).thenReturn(null);
    mockMvc.perform(MockMvcRequestBuilders.post("/person")
            .content(objectMapper.writeValueAsString(person))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
  }
  
  @Test
  public void updatePerson_CorrectInfos_ReturnsOkStatus() throws Exception{
    Person person = new Person("Foster", "Shepard", "748 Townings Dr", "Culver", "97451", "841-874-6544", "jaboyd@email.com");
    when(personService.updatePerson(any(Person.class))).thenReturn(person);
    mockMvc.perform(MockMvcRequestBuilders.put("/person")
            .content(objectMapper.writeValueAsString(person))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }
  
  @Test
  public void updatePerson_UnknownNames_ReturnsNotFoundStatus() throws Exception{
    Person person = new Person("Foster", "Shepard", "748 Townings Dr", "Culver", "97451", "841-874-6544", "jaboyd@email.com");
    when(personService.updatePerson(any(Person.class))).thenReturn(null);
    mockMvc.perform(MockMvcRequestBuilders.put("/person")
            .content(objectMapper.writeValueAsString(person))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
  }
  
  @Test
  public void deletePerson_CorrectInfos_ReturnsOkStatus() throws Exception{
    when(personService.deletePerson("Jamie","Peters")).thenReturn(true);
    mockMvc.perform(delete("/person?firstName=Jamie&lastName=Peters"))
           .andExpect(status().isOk()); 
  }
  
  @Test
  public void deletePerson_UnknownNames_ReturnsNotFoundStatus() throws Exception{
    when(personService.deletePerson("Jamie","Peters")).thenReturn(false);
    mockMvc.perform(delete("/person?firstName=Jamie&lastName=Peters"))
           .andExpect(status().isNotFound()); 
  }
  
  
}
