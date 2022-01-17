package com.safetynet.sfalert.service;

import java.text.ParseException;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import com.safetynet.sfalert.model.ChildAlert;
import com.safetynet.sfalert.model.MedicalRecord;
import com.safetynet.sfalert.model.Person;
import com.safetynet.sfalert.repository.MedicalRecordProxy;
import com.safetynet.sfalert.repository.PersonProxy;
import com.safetynet.sfalert.util.AgeCalculator;

public class ChildAlertService {
  
  PersonProxy personProxy = new PersonProxy();
  MedicalRecordProxy medicalRecordProxy = new MedicalRecordProxy();
  
  public List<ChildAlert> getChilds(String address) throws ParseException{
    List<Person> persons = personProxy.getPersons(address);
    List<MedicalRecord> medicalRecords = medicalRecordProxy.getMedicalrecords();
    List<ChildAlert> childAlerts = new ArrayList<ChildAlert>();
    for(Person p : persons) {
      for(MedicalRecord m : medicalRecords) {
        if(m.getFirstName().equals(p.getFirstName()) &&  m.getLastName().equals(p.getLastName())) {
          Period age = AgeCalculator.ageCalculator(m.getBirthdate());
          childAlerts.add(new ChildAlert(p.getLastName(), p.getFirstName(), age));
        }
      }
    }
    return childAlerts;
  }
}
