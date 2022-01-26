package com.safetynet.sfalert.service;

import java.util.List;

import com.safetynet.sfalert.model.PersonInfo;

public interface IPersonInfoService {

  public List<PersonInfo> getPersonInfo(String firstName, String lastName);

}