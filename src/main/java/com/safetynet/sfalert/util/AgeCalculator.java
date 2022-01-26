package com.safetynet.sfalert.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import com.safetynet.sfalert.constants.Constants;


public class AgeCalculator {
  
  
  public static Period ageCalculator(String dataBirthDate) {
    LocalDate dateNow = LocalDate.now();
    LocalDate birthDate = LocalDate.parse(dataBirthDate, DateTimeFormatter.ofPattern(Constants.DATE_PATTERN));
    Period age = Period.between(birthDate, dateNow);
    return age;
  }
  
  public static boolean legalAgeFonction(Period age){
    boolean majority;
      if(age.minusYears(Constants.LEGAL_AGE).isNegative()) {
        majority = false;
      } else {
        majority = true;
      }
      return majority;
  }
  
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
