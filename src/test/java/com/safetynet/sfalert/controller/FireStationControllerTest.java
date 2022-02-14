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
import com.safetynet.sfalert.service.impl.FireStationService;


@WebMvcTest(controllers = FireStationController.class)
public class FireStationControllerTest {

  @Autowired
  private MockMvc mockMvc;
  
  @Autowired
  private ObjectMapper objectMapper;
  
  @MockBean
  private FireStationService fireStationService;
  
  
  @Test
  public void stationPeople_CorrectInfo_ReturnsOkStatus() throws Exception{
    mockMvc.perform(get("/firestation?stationNumber=3"))
           .andExpect(status().isOk());
  }
  
  @Test
  public void phoneAlert_CorrectInfo_ReturnsOkStatus() throws Exception{
    mockMvc.perform(get("/phoneAlert?firestation=2"))
           .andExpect(status().isOk());
  }
  
  @Test
  public void fireAlert_CorrectInfo_ReturnsOkStatus() throws Exception{
    mockMvc.perform(get("/fire?address=489 Manchester St"))
           .andExpect(status().isOk());
  }
  
  @Test
  public void floodAlert_CorrectInfo_ReturnsOkStatus() throws Exception{
    mockMvc.perform(get("/flood/stations?stations=4,1"))
           .andExpect(status().isOk());
  }
  
  
  @Test
  public void addFireStation_CorrectInfos_ReturnsCreatedStatus() throws Exception{
    FireStation fireStation = new FireStation("644 Gershwin Cir", "1");
    when(fireStationService.saveFireStation(any(FireStation.class))).thenReturn(fireStation);
    mockMvc.perform(MockMvcRequestBuilders.post("/firestation")
            .content(objectMapper.writeValueAsString(fireStation))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
  }
  
  @Test
  public void addFireStation_DuplicateFireStation_ReturnsNoContentStatus() throws Exception{
    FireStation fireStation = new FireStation("644 Gershwin Cir", "1");
    when(fireStationService.saveFireStation(any(FireStation.class))).thenReturn(null);
    mockMvc.perform(MockMvcRequestBuilders.post("/firestation")
            .content(objectMapper.writeValueAsString(fireStation))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
  }
  
  @Test
  public void updateFireStation_CorrectFireStation_ReturnsOkStatus() throws Exception{
    FireStation fireStation = new FireStation("644 Gershwin Cir", "1");
    when(fireStationService.updateFireStation(any(FireStation.class))).thenReturn(fireStation);
    mockMvc.perform(MockMvcRequestBuilders.put("/firestation")
            .content(objectMapper.writeValueAsString(fireStation))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }
  
  @Test
  public void updateFireStation_UnknownFireStation_ReturnsNotFoundStatus() throws Exception{
    FireStation fireStation = new FireStation("644 Gershwin Cir", "1");
    when(fireStationService.updateFireStation(any(FireStation.class))).thenReturn(null);
    mockMvc.perform(MockMvcRequestBuilders.put("/firestation")
            .content(objectMapper.writeValueAsString(fireStation))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
  }
  
  @Test
  public void deleteFireStation_CorrectInfos_ReturnsOkStatus() throws Exception{
    when(fireStationService.deleteFireStation("908 73rd St")).thenReturn(true);
    mockMvc.perform(delete("/firestation?station=908 73rd St"))
           .andExpect(status().isOk()); 
  }
  
  @Test
  public void deleteFireStation_UnknownStation_ReturnsNotFoundStatus() throws Exception{
    when(fireStationService.deleteFireStation("908 73rd St")).thenReturn(false);
    mockMvc.perform(delete("/firestation?station=908 73rd St"))
           .andExpect(status().isNotFound()); 
  }
  
  
}
