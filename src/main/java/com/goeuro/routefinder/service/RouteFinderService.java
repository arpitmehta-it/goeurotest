package com.goeuro.routefinder.service;

import com.goeuro.routefinder.component.DirectRouteExistInfo;
import com.goeuro.routefinder.component.PlannedRoutes;

import java.util.Map;

public class RouteFinderService {

  Map<Integer, Map<Integer, Integer>> busRouteMap = null;

  public RouteFinderService() {
  }

  public DirectRouteExistInfo checkRouteExist(int departureStationID, int arrivalStationID) {

    if (busRouteMap == null){
      this.busRouteMap = new PlannedRoutes().getRouteMap();
    }
    
    if (checkParticularBusRoute(departureStationID, arrivalStationID)) {
        return new DirectRouteExistInfo(departureStationID, arrivalStationID, true);
    }
    return new DirectRouteExistInfo(departureStationID, arrivalStationID, false);
  }

  private boolean checkParticularBusRoute(int departureStationID, int arrivalStationID) {
    for (Map<Integer, Integer> individualRoute : busRouteMap.values()){
      int departureStationNumber = -1;
      int arrivalStationNumber = -1;
      if(individualRoute.get(departureStationID) != null){
        departureStationNumber = individualRoute.get(departureStationID); 
      }
      if(individualRoute.get(arrivalStationID) != null){
        arrivalStationNumber = individualRoute.get(arrivalStationID);
      }      
      if(departureStationNumber < arrivalStationNumber){
        return true;
      }
    }
    return false;
  }
}
