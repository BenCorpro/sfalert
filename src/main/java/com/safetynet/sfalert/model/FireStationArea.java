package com.safetynet.sfalert.model;

import java.util.List;

public class FireStationArea {
  private List<StationPeople> stationPeople;
  private int areaChildCount;
  private int areaAdultCount;
  
  
  public FireStationArea() {

  }
  
  public FireStationArea(List<StationPeople> stationPeople, int areaChildCount,
      int areaAdultCount) {
    this.stationPeople = stationPeople;
    this.areaChildCount = areaChildCount;
    this.areaAdultCount = areaAdultCount;
  }
  
  
  public List<StationPeople> getStationPeople() {
    return stationPeople;
  }
  public void setStationPeople(List<StationPeople> stationPeople) {
    this.stationPeople = stationPeople;
  }
  public int getAreaChildCount() {
    return areaChildCount;
  }
  public void setAreaChildCount(int areaChildCount) {
    this.areaChildCount = areaChildCount;
  }
  public int getAreaAdultCount() {
    return areaAdultCount;
  }
  public void setAreaAdultCount(int areaAdultCount) {
    this.areaAdultCount = areaAdultCount;
  }
  
  
  @Override
  public String toString() {
    return "FireStationArea [stationPeople=" + stationPeople
        + ", areaChildCount=" + areaChildCount + ", areaAdultCount="
        + areaAdultCount + "]";
  }
  
  
}
