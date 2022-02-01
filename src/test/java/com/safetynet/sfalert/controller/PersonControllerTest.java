package com.safetynet.sfalert.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.sfalert.service.impl.ChildAlertService;
import com.safetynet.sfalert.service.impl.EmailService;
import com.safetynet.sfalert.service.impl.PersonInfoService;
import com.safetynet.sfalert.service.impl.PersonService;

@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTest {

  @Autowired
  private MockMvc mockMvc;
  
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
  

}
