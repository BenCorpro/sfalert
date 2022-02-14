package com.safetynet.sfalert.model;


/**
 * The Person Class, entity.
 */
public class Person {
  
  /** The person's first name. */
  private String firstName;
  
  /** The person's last name. */
  private String lastName;
  
  /** The person's address. */
  private String address;
  
  /** The city. */
  private String city;
  
  /** The zip. */
  private String zip;
  
  /** The person's phone. */
  private String phone;
  
  /** The person's email. */
  private String email;
  
  /** The person's medical record. */
  private MedicalRecord medicalRecord;
  
  
  /**
   * Empty constructor of a new person.
   */
  public Person() {

  }
  
  /**
   * Entity constructor of a new person.
   *
   * @param firstName the first name
   * @param lastName the last name
   * @param address the address
   * @param city the city
   * @param zip the zip
   * @param phone the phone
   * @param email the email
   */
  public Person(String firstName, String lastName, String address, String city,
      String zip, String phone, String email) {
    super();
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.city = city;
    this.zip = zip;
    this.phone = phone;
    this.email = email;
  }

  /**
   * constructor of a new person, with link to the person's medical record.
   *
   * @param firstName the first name
   * @param lastName the last name
   * @param address the address
   * @param city the city
   * @param zip the zip
   * @param phone the phone
   * @param email the email
   * @param medicalRecord the medical record
   */
  public Person(String firstName, String lastName, String address, String city,
      String zip, String phone, String email, MedicalRecord medicalRecord) {
    super();
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.city = city;
    this.zip = zip;
    this.phone = phone;
    this.email = email;
    this.medicalRecord = medicalRecord;
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
   * Gets the address.
   *
   * @return the person's address
   */
  public String getAddress() {
    return address;
  }
  
  /**
   * Sets the address.
   *
   * @param address the new person's address
   */
  public void setAddress(String address) {
    this.address = address;
  }
  
  /**
   * Gets the city.
   *
   * @return the city
   */
  public String getCity() {
    return city;
  }
  
  /**
   * Sets the city.
   *
   * @param city the new city
   */
  public void setCity(String city) {
    this.city = city;
  }
  
  /**
   * Gets the zip.
   *
   * @return the zip
   */
  public String getZip() {
    return zip;
  }
  
  /**
   * Sets the zip.
   *
   * @param zip the new zip
   */
  public void setZip(String zip) {
    this.zip = zip;
  }
  
  /**
   * Gets the phone.
   *
   * @return the person's phone
   */
  public String getPhone() {
    return phone;
  }
  
  /**
   * Sets the phone.
   *
   * @param phone the new person's phone
   */
  public void setPhone(String phone) {
    this.phone = phone;
  }
  
  /**
   * Gets the email.
   *
   * @return the person's email
   */
  public String getEmail() {
    return email;
  }
  
  /**
   * Sets the email.
   *
   * @param email the new person's email
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Gets the medical record.
   *
   * @return the person's medical record
   */
  public MedicalRecord getMedicalRecord() {
    return medicalRecord;
  }

  /**
   * Sets the medical record.
   *
   * @param medicalRecord the new person's medical record
   */
  public void setMedicalRecord(MedicalRecord medicalRecord) {
    this.medicalRecord = medicalRecord;
  }

  
  /**
   * To string.
   *
   * @return string
   */
  @Override
  public String toString() {
    return "Person [firstName=" + firstName + ", lastName=" + lastName
        + ", address=" + address + ", city=" + city + ", zip=" + zip
        + ", phone=" + phone + ", email=" + email + "]";
  }
  
  
}
