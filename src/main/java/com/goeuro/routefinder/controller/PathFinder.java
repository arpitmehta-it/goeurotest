package com.goeuro.routefinder.controller;

import com.goeuro.routefinder.component.DirectRouteExistInfo;
import com.goeuro.routefinder.service.RouteFinderService;
import org.springframework.web.bind.annotation.*;

@RestController
public class PathFinder {

    public static RouteFinderService routeFinderService = new RouteFinderService();

    @RequestMapping(value = "/api/direct", method = RequestMethod.GET)
    public
    @ResponseBody
    DirectRouteExistInfo existsDirectRoute(@RequestParam(value = "dep_sid") int departureStationId,
                                           @RequestParam(value = "arr_sid") int arrivalStationId) {

        return routeFinderService.checkRouteExist(departureStationId, arrivalStationId);

    }
}
