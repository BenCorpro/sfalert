package com.safetynet.sfalert.service;

import java.util.List;
import java.util.Map;

import com.safetynet.sfalert.dto.ChildAlertDto;

public interface IChildAlertService {

  public Map<String, List<ChildAlertDto>> getChilds(String address);

}