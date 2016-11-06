package com.goeuro.routefinder.service;

import com.goeuro.routefinder.component.DirectRouteExistInfo;
import com.goeuro.routefinder.component.PlannedRoutes;

import java.util.List;

public class RouteFinderService {

    List<String> busRouteList = null;

    public RouteFinderService() {
    }

    public DirectRouteExistInfo checkRouteExist(int departureStationID, int arrivalStationID) {

        if (busRouteList == null)
            this.busRouteList = new PlannedRoutes().getListOfStops();


        for (String route : this.busRouteList) {
            if (checkParticularBusRoute(route, departureStationID, arrivalStationID)) {
                return new DirectRouteExistInfo(departureStationID, arrivalStationID, true);
            }
        }
        return new DirectRouteExistInfo(departureStationID, arrivalStationID, false);
    }

    private boolean checkParticularBusRoute(String route, int departureStationID, int arrivalStationID) {
        return route.contains(" " + departureStationID + " ") && route.contains(" " + arrivalStationID + " ") &&
                (route.indexOf(" " + arrivalStationID + " ") > route.indexOf(" " + departureStationID + " "));
    }


}
