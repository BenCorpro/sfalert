package com.safetynet.sfalert.dto;

public class StationPeopleDto {
  
  private String firstName;
  private String lastName;
  private String address;
  private String phone;
  
  
  public StationPeopleDto() {

  }
  
  public StationPeopleDto(String firstName, String lastName, String address,
      String phone) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.phone = phone;
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
  public String getAddress() {
    return address;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  public String getPhone() {
    return phone;
  }
  public void setPhone(String phone) {
    this.phone = phone;
  }

  
  @Override
  public String toString() {
    return "StationPeople [firstName=" + firstName + ", lastName=" + lastName
        + ", address=" + address + ", phone=" + phone + "]";
  }
  
  
}
