package com.safetynet.sfalert.service;

import java.util.List;
import java.util.Map;

import com.safetynet.sfalert.model.ChildAlert;

public interface IChildAlertService {

  public Map<String, List<ChildAlert>> getChilds(String address);

}