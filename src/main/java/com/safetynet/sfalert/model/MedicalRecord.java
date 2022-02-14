package com.safetynet.sfalert.model;

import java.util.List;


/**
 * The MedicalRecord Class, entity.
 */
public class MedicalRecord {
  
  /** person's first name. */
  private String firstName;
  
  /** person's last name. */
  private String lastName;
  
  /** person's birthdate. */
  private String birthdate;
  
  /** person's medications list. */
  private List<String> medications;
  
  /** person's allergies list. */
  private List<String> allergies;
  
  
  /**
   * Empty constructor of a new medical record.
   */
  public MedicalRecord() {

  }

  /**
   * Entity constructor of a new medical record.
   *
   * @param firstName person's first name
   * @param lastName person's last name
   * @param birthdate person's birthdate
   * @param medications person's medications list
   * @param allergies person's allergies list
   */
  public MedicalRecord(String firstName, String lastName, String birthdate,
      List<String> medications, List<String> allergies) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthdate = birthdate;
    this.medications = medications;
    this.allergies = allergies;
  }
  
  
  /**
   * Gets the first name.
   *
   * @return the person's first name
   */
  public String getFirstName() {
    return firstName;
  }
  
  /**
   * Sets the first name.
   *
   * @param firstName the new person's first name
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  
  /**
   * Gets the last name.
   *
   * @return the person's last name
   */
  public String getLastName() {
    return lastName;
  }
  
  /**
   * Sets the last name.
   *
   * @param lastName the new person's last name
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  
  /**
   * Gets the birthdate.
   *
   * @return the person's birthdate
   */
  public String getBirthdate() {
    return birthdate;
  }
  
  /**
   * Sets the birthdate.
   *
   * @param birthdate the new person's birthdate
   */
  public void setBirthdate(String birthdate) {
    this.birthdate = birthdate;
  }
  
  /**
   * Gets the medications.
   *
   * @return the list of medications
   */
  public List<String> getMedications() {
    return medications;
  }
  
  /**
   * Sets the medications.
   *
   * @param medications the new list of medications
   */
  public void setMedications(List<String> medications) {
    this.medications = medications;
  }
  
  /**
   * Gets the allergies.
   *
   * @return the list of allergies
   */
  public List<String> getAllergies() {
    return allergies;
  }
  
  /**
   * Sets the allergies.
   *
   * @param allergies the new list of allergies
   */
  public void setAllergies(List<String> allergies) {
    this.allergies = allergies;
  }

  
  /**
   * To string.
   *
   * @return string
   */
  @Override
  public String toString() {
    return "MedicalRecord [firstName=" + firstName + ", lastName=" + lastName
        + ", birthdate=" + birthdate + ", medications=" + medications
        + ", allergies=" + allergies + "]";
  }
  
  
}
