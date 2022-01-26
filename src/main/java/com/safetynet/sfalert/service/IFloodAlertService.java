package com.safetynet.sfalert.service;

import java.util.List;
import java.util.Map;

import com.safetynet.sfalert.model.FireAndFloodAlert;

public interface IFloodAlertService {

  public Map<String, List<FireAndFloodAlert>> floodListStation(List<String> station);

}