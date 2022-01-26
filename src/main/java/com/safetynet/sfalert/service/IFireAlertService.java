package com.safetynet.sfalert.service;

import java.util.List;
import java.util.Map;

import com.safetynet.sfalert.model.FireAndFloodAlert;

public interface IFireAlertService {

  public Map<String, List<FireAndFloodAlert>> fireListAddress(String address);

}