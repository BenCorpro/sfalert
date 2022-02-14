package com.safetynet.sfalert.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import com.safetynet.sfalert.constants.Constants;


/**
 * The AgeCalculator Class, with age and majority methods.
 */
public class AgeCalculator {
  
  
  /**
   * Age calculator, calculates the age of a person from birthdate, as of today.
   *
   * @param dataBirthDate the person's birth date
   * @return the age as a Period
   */
  public static Period ageCalculator(String dataBirthDate) {
    LocalDate dateNow = LocalDate.now();
    LocalDate birthDate = LocalDate.parse(dataBirthDate, DateTimeFormatter.ofPattern(Constants.DATE_PATTERN));
    Period age = Period.between(birthDate, dateNow);
    return age;
  }
  
  /**
   * Legal age fonction, determines the majority of a person.
   *
   * @param birthDate the person's birth date
   * @return true, if adult
   */
  public static boolean legalAgeFonction(String birthDate){
    boolean majority;
    Period age = ageCalculator(birthDate); 
      if(age.minusYears(Constants.LEGAL_AGE).isNegative()) {
        majority = false;
      } else {
        majority = true;
      }
      return majority;
  }
 
  
}
