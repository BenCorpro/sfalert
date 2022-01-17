package com.safetynet.sfalert.model;

public class Entity {
  private Person person;
  private FireStation fireStation;
  private MedicalRecord medicalRecord;
  
  
  public Entity() {

  }
  
  public Entity(Person person, FireStation fireStation,
      MedicalRecord medicalRecord) {

    this.person = person;
    this.fireStation = fireStation;
    this.medicalRecord = medicalRecord;
  }
  
  
  public Person getPerson() {
    return person;
  }
  public void setPerson(Person person) {
    this.person = person;
  }
  public FireStation getFireStation() {
    return fireStation;
  }
  public void setFireStation(FireStation fireStation) {
    this.fireStation = fireStation;
  }
  public MedicalRecord getMedicalRecord() {
    return medicalRecord;
  }
  public void setMedicalRecord(MedicalRecord medicalRecord) {
    this.medicalRecord = medicalRecord;
  }

  
  @Override
  public String toString() {
    return "Entity [person=" + person + ", fireStation=" + fireStation
        + ", medicalRecord=" + medicalRecord + "]";
  }
  
  
}
