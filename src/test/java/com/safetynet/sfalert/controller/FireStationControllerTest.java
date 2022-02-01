package com.safetynet.sfalert.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.sfalert.service.impl.FireAlertService;
import com.safetynet.sfalert.service.impl.FireStationAreaService;
import com.safetynet.sfalert.service.impl.FireStationService;
import com.safetynet.sfalert.service.impl.FloodAlertService;
import com.safetynet.sfalert.service.impl.PhoneAlertService;

@WebMvcTest(controllers = FireStationController.class)
public class FireStationControllerTest {

  @Autowired
  private MockMvc mockMvc;
  
  @MockBean
  private FireStationAreaService fireStationAreaService;
  @MockBean
  private PhoneAlertService phoneAlertService;
  @MockBean
  private FireAlertService fireAlertService;
  @MockBean
  private FloodAlertService floodAlertService;
  @MockBean
  private FireStationService fireStationService;
  
  @Test
  public void testgetStationPeoples() throws Exception{
    mockMvc.perform(get("/firestation?stationNumber=3"))
           .andExpect(status().isOk());
  }
  
  @Test
  public void testGetPhoneAlert() throws Exception{
    mockMvc.perform(get("/phoneAlert?firestation=2"))
           .andExpect(status().isOk());
  }
  
  @Test
  public void testGetFireAlert() throws Exception{
    mockMvc.perform(get("/fire?address=489 Manchester St"))
           .andExpect(status().isOk());
  }
  
  @Test
  public void testGetFloodAlert() throws Exception{
    mockMvc.perform(get("/flood/stations?stations=4,1"))
           .andExpect(status().isOk());
  }
}
