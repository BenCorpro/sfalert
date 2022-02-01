package com.safetynet.sfalert.service;

import java.util.List;
import java.util.Map;

import com.safetynet.sfalert.dto.FireAndFloodAlertDto;

public interface IFireAlertService {

  public Map<String, List<FireAndFloodAlertDto>> fireListAddress(String address);

}