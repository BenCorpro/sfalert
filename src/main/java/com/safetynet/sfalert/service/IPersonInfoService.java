package com.safetynet.sfalert.service;

import java.util.List;

import com.safetynet.sfalert.dto.PersonInfoDto;

public interface IPersonInfoService {

  public List<PersonInfoDto> getPersonInfo(String firstName, String lastName);

}