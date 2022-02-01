package com.safetynet.sfalert.service;

import java.util.List;
import java.util.Map;

import com.safetynet.sfalert.dto.FireAndFloodAlertDto;

public interface IFloodAlertService {

  public Map<String, List<FireAndFloodAlertDto>> floodListStation(List<String> station);

}