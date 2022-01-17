package com.safetynet.sfalert.model;

import java.time.Period;

public class ChildAlert {
    private String lastName;
    private String firstName;
    private Period age;
    
    
    public ChildAlert() {

    }
    
    public ChildAlert(String lastName, String firstName, Period age) {
      this.lastName = lastName;
      this.firstName = firstName;
      this.age = age;
    }
    
    
    public String getLastName() {
      return lastName;
    }
    public void setLastName(String lastName) {
      this.lastName = lastName;
    }
    public String getFirstName() {
      return firstName;
    }
    public void setFirstName(String firstName) {
      this.firstName = firstName;
    }
    public Period getAge() {
      return age;
    }
    public void setAge(Period age) {
      this.age = age;
    }
    
    
    @Override
    public String toString() {
      return "ChildAlert [lastName=" + lastName + ", firstName=" + firstName
          + ", age=" + age + "]";
    }
    
    
}
