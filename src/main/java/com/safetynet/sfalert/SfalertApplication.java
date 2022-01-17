package com.safetynet.sfalert;



import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.safetynet.sfalert.model.ChildAlert;
import com.safetynet.sfalert.model.Entity;
import com.safetynet.sfalert.model.FireAlert;
import com.safetynet.sfalert.model.FireStation;
import com.safetynet.sfalert.model.FireStationArea;
import com.safetynet.sfalert.model.MedicalRecord;
import com.safetynet.sfalert.model.Person;
import com.safetynet.sfalert.model.PersonInfo;
import com.safetynet.sfalert.model.StationPeople;
import com.safetynet.sfalert.repository.PersonProxy;
import com.safetynet.sfalert.repository.EntityProxy;
import com.safetynet.sfalert.repository.FireStationProxy;
import com.safetynet.sfalert.repository.MedicalRecordProxy;
import com.safetynet.sfalert.service.ChildAlertService;
import com.safetynet.sfalert.service.EmailService;
import com.safetynet.sfalert.service.FireAlertService;
import com.safetynet.sfalert.service.FireStationAreaService;
import com.safetynet.sfalert.service.FloodAlertService;
import com.safetynet.sfalert.service.PersonInfoService;
import com.safetynet.sfalert.service.PersonService;
import com.safetynet.sfalert.service.PhoneAlertService;

@SpringBootApplication
public class SfalertApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SfalertApplication.class, args);
	}

  @Override
  public void run(String... args) throws Exception {
    EntityProxy entityProxy = new EntityProxy();
    FireAlertService fireAlertService = new FireAlertService();
    FloodAlertService floodAlertService = new FloodAlertService();
    PersonInfoService personInfoService = new PersonInfoService();
    EmailService emailService = new EmailService();
    //}
    //System.out.println(medicalRecordProxy.getMedicalrecord("Peter", "Duncan"));
    //for(Boolean a : personService.legalAgeFonction(personService.ageCalculator())) {
    //  System.out.println(a);
    //}
    //for(Integer c : personService.childAndAdultCount(personService.legalAgeFonction(personService.ageCalculator()))) {
    //  System.out.println(c);
    //}
    //for(ChildAlert cA : childAlertService.getChilds("947 E. Rose Dr")){
    // System.out.println(cA);
    //}
    //for(Entity e : entityProxy.getEntities()) {
    //  System.out.println(e);
    //}
    //for(FireAlert f : floodAlertService.floodListStation("1")) {
    //  System.out.println(f);
    //}
    //PersonInfo p = personInfoService.getPersonInfo("Foster", "Shepard");
    //System.out.println(p);
    for(String m : emailService.getEmailList("Culver")) {
      System.out.println(m);
    }
  }

}
