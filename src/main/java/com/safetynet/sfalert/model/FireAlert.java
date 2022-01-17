package com.safetynet.sfalert.model;

import java.time.Period;
import java.util.List;

public class FireAlert {
  
  private String firstName;
  private String lastName;
  private String phone;
  private Period age;
  private List<String> medications;
  private List<String> allergies;
  
  
  
  public FireAlert() {

  }

  public FireAlert(String firstName, String lastName, String phone, Period age,
      List<String> medications, List<String> allergies) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.phone = phone;
    this.age = age;
    this.medications = medications;
    this.allergies = allergies;
  }
  
  
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  public String getPhone() {
    return phone;
  }
  public void setPhone(String phone) {
    this.phone = phone;
  }
  public Period getAge() {
    return age;
  }
  public void setAge(Period age) {
    this.age = age;
  }
  public List<String> getMedications() {
    return medications;
  }
  public void setMedications(List<String> medications) {
    this.medications = medications;
  }
  public List<String> getAllergies() {
    return allergies;
  }
  public void setAllergies(List<String> allergies) {
    this.allergies = allergies;
  }
  
  
  @Override
  public String toString() {
    return "FireAlert [firstName=" + firstName + ", lastName=" + lastName
        + ", phone=" + phone + ", age=" + age + ", medications=" + medications
        + ", allergies=" + allergies + "]";
  }
  
  
}
