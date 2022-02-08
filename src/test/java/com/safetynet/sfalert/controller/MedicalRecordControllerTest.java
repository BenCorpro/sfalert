package com.safetynet.sfalert.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.sfalert.model.MedicalRecord;
import com.safetynet.sfalert.service.impl.MedicalRecordService;

@WebMvcTest(controllers = MedicalRecordController.class)
public class MedicalRecordControllerTest {
  
  @Autowired
  private MockMvc mockMvc;
  
  @Autowired
  private ObjectMapper objectMapper;
  
  @MockBean
  private MedicalRecordService medicalRecordService;
  
  @Test
  public void testAddMedicalRecord() throws Exception{
    List<String> medicationsTest = new ArrayList<String>();
    medicationsTest.add("tetracyclaz:650mg");
    List<String> allergiesTest = new ArrayList<String>();
    allergiesTest.add("xilliathal"); 
    MedicalRecord medicalRecord = new MedicalRecord("Felicia", "Boyd", "01/08/1986", medicationsTest, allergiesTest);
    when(medicalRecordService.saveMedicalRecord(any(MedicalRecord.class))).thenReturn(medicalRecord);
    mockMvc.perform(MockMvcRequestBuilders.post("/medicalRecord")
            .content(objectMapper.writeValueAsString(medicalRecord))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
  }
  
  @Test
  public void testAddMedicalRecordDuplicate() throws Exception{
    List<String> medicationsTest = new ArrayList<String>();
    medicationsTest.add("tetracyclaz:650mg");
    List<String> allergiesTest = new ArrayList<String>();
    allergiesTest.add("xilliathal"); 
    MedicalRecord medicalRecord = new MedicalRecord("Felicia", "Boyd", "01/08/1986", medicationsTest, allergiesTest);
    when(medicalRecordService.saveMedicalRecord(any(MedicalRecord.class))).thenReturn(null);
    mockMvc.perform(MockMvcRequestBuilders.post("/medicalRecord")
            .content(objectMapper.writeValueAsString(medicalRecord))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
  }
  
  @Test
  public void testUpdateMedicalRecord() throws Exception{
    List<String> medicationsTest = new ArrayList<String>();
    medicationsTest.add("tetracyclaz:650mg");
    List<String> allergiesTest = new ArrayList<String>();
    allergiesTest.add("xilliathal"); 
    MedicalRecord medicalRecord = new MedicalRecord("Felicia", "Boyd", "01/08/1986", medicationsTest, allergiesTest);
    when(medicalRecordService.updateMedicalRecord(any(MedicalRecord.class))).thenReturn(medicalRecord);
    mockMvc.perform(MockMvcRequestBuilders.put("/medicalRecord")
            .content(objectMapper.writeValueAsString(medicalRecord))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }
  
  @Test
  public void testUpdateMedicalRecordUnknown() throws Exception{
    List<String> medicationsTest = new ArrayList<String>();
    medicationsTest.add("tetracyclaz:650mg");
    List<String> allergiesTest = new ArrayList<String>();
    allergiesTest.add("xilliathal"); 
    MedicalRecord medicalRecord = new MedicalRecord("Felicia", "Boyd", "01/08/1986", medicationsTest, allergiesTest);
    when(medicalRecordService.updateMedicalRecord(any(MedicalRecord.class))).thenReturn(null);
    mockMvc.perform(MockMvcRequestBuilders.put("/medicalRecord")
            .content(objectMapper.writeValueAsString(medicalRecord))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
  }
  
  @Test
  public void testDeleteMedicalRecord() throws Exception{
    when(medicalRecordService.deleteMedicalRecord("Clive","Ferguson")).thenReturn(true);
    mockMvc.perform(delete("/medicalRecord?firstName=Clive&lastName=Ferguson"))
           .andExpect(status().isOk()); 
  }
  
  @Test
  public void testDeleteMedicalRecordUnknown() throws Exception{
    when(medicalRecordService.deleteMedicalRecord("Clive","Ferguson")).thenReturn(false);
    mockMvc.perform(delete("/medicalRecord?firstName=Clive&lastName=Ferguson"))
           .andExpect(status().isNotFound()); 
  }

}
