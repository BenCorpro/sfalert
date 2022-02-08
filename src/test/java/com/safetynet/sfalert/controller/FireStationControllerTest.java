package com.safetynet.sfalert.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.sfalert.model.FireStation;
import com.safetynet.sfalert.service.impl.FireAlertService;
import com.safetynet.sfalert.service.impl.FireStationAreaService;
import com.safetynet.sfalert.service.impl.FireStationService;
import com.safetynet.sfalert.service.impl.FloodAlertService;
import com.safetynet.sfalert.service.impl.PhoneAlertService;

@WebMvcTest(controllers = FireStationController.class)
public class FireStationControllerTest {

  @Autowired
  private MockMvc mockMvc;
  
  @Autowired
  private ObjectMapper objectMapper;
  
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
  
  @Test
  public void testAddFireStation() throws Exception{
    FireStation fireStation = new FireStation("644 Gershwin Cir", "1");
    when(fireStationService.saveFireStation(any(FireStation.class))).thenReturn(fireStation);
    mockMvc.perform(MockMvcRequestBuilders.post("/firestation")
            .content(objectMapper.writeValueAsString(fireStation))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
  }
  
  @Test
  public void testAddFireStationDuplicate() throws Exception{
    FireStation fireStation = new FireStation("644 Gershwin Cir", "1");
    when(fireStationService.saveFireStation(any(FireStation.class))).thenReturn(null);
    mockMvc.perform(MockMvcRequestBuilders.post("/firestation")
            .content(objectMapper.writeValueAsString(fireStation))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
  }
  
  @Test
  public void testUpdateFireStation() throws Exception{
    FireStation fireStation = new FireStation("644 Gershwin Cir", "1");
    when(fireStationService.updateFireStation(any(FireStation.class))).thenReturn(fireStation);
    mockMvc.perform(MockMvcRequestBuilders.put("/firestation")
            .content(objectMapper.writeValueAsString(fireStation))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }
  
  @Test
  public void testUpdateFireStationUnknown() throws Exception{
    FireStation fireStation = new FireStation("644 Gershwin Cir", "1");
    when(fireStationService.updateFireStation(any(FireStation.class))).thenReturn(null);
    mockMvc.perform(MockMvcRequestBuilders.put("/firestation")
            .content(objectMapper.writeValueAsString(fireStation))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
  }
  
  @Test
  public void testDeleteFireStation() throws Exception{
    when(fireStationService.deleteFireStation("908 73rd St")).thenReturn(true);
    mockMvc.perform(delete("/firestation?station=908 73rd St"))
           .andExpect(status().isOk()); 
  }
  
  @Test
  public void testDeleteFireStationUnknown() throws Exception{
    when(fireStationService.deleteFireStation("908 73rd St")).thenReturn(false);
    mockMvc.perform(delete("/firestation?station=908 73rd St"))
           .andExpect(status().isNotFound()); 
  }
}
