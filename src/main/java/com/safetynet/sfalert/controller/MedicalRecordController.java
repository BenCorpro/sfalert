package com.safetynet.sfalert.controller;

import java.net.URI;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.safetynet.sfalert.model.MedicalRecord;
import com.safetynet.sfalert.service.IMedicalRecordService;

@RestController
public class MedicalRecordController {

  @Autowired
  IMedicalRecordService medicalRecordService;
  
  @PostMapping("/medicalRecord")
  public ResponseEntity<MedicalRecord> addMedicalRecord(@RequestBody MedicalRecord medicalRecord){
    MedicalRecord addedMedicalRecord = medicalRecordService.saveMedicalRecord(medicalRecord);
    if(Objects.isNull(addedMedicalRecord)) {
      return ResponseEntity.noContent().build();
    } else {
      URI location = ServletUriComponentsBuilder.fromCurrentRequest()
          .path("/{firstName}&{lastName}")
          .buildAndExpand(addedMedicalRecord.getFirstName(), addedMedicalRecord.getLastName())
          .toUri();
      return ResponseEntity.created(location).build(); 
    } 
  }
  
  @PutMapping("/medicalRecord")
  public ResponseEntity<MedicalRecord> updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
    MedicalRecord updatedMedicalRecord = medicalRecordService.updateMedicalRecord(medicalRecord);
    if(Objects.isNull(updatedMedicalRecord)) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok().body(updatedMedicalRecord);
    }
  }
  
  @DeleteMapping("/medicalRecord")
  public ResponseEntity<Boolean> deleteMedicalRecord(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
    boolean medicalRecordDeleted = medicalRecordService.deleteMedicalRecord(firstName, lastName);
    if(!medicalRecordDeleted) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok().body(medicalRecordDeleted);
    }
  }
}
