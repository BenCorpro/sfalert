package com.safetynet.sfalert.util;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.safetynet.sfalert.model.MedicalRecord;
import com.safetynet.sfalert.repository.MedicalRecordProxy;

public class AgeCalculator {
  
  MedicalRecordProxy medicalRecordProxy = new MedicalRecordProxy();
  
  
  public static Period ageCalculator(String dataBirthDate) throws ParseException {
    LocalDate dateNow = LocalDate.now();
    LocalDate birthDate = LocalDate.parse(dataBirthDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    Period age = Period.between(birthDate, dateNow);
    return age;
  }
  
  public List<Boolean> legalAgeFonction(List<Period> ages){
    long legalAge = 18;
    List<Boolean> majority = new ArrayList<Boolean>();
    for(Period a : ages) {
      if(a.minusYears(legalAge).isNegative()) {
        majority.add(false);
      } else {
        majority.add(true);
      }
    }
    return majority;
  }
  
  public List<Integer> childAndAdultCount(List<Boolean> majority) {
    int adultCount = 0;
    int childCount = 0;
    List<Integer> majorityCount = new ArrayList<Integer>();
    for(Boolean m : majority) {
      if(m == false) {
        childCount++;
      } else {
        adultCount++;
      }
    } 
    majorityCount.add(childCount);
    majorityCount.add(adultCount);
    return majorityCount;
  }
}
