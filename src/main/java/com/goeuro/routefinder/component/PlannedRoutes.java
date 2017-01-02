package com.goeuro.routefinder.component;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@Component
public class PlannedRoutes implements ApplicationRunner {

  private static Map<Integer, Map<Integer, Integer>> routeMap;
  private String filePath;

  /**
   * Here write logic for loading and storing the file in-memory
   * TODO: Hot restart on file change
   * @param args
   * @throws Exception
   */
  @Override
  public void run(ApplicationArguments args) throws Exception {
    List<String> value = args.getOptionValues("fileloc");
    filePath = value.get(0);
    routeMap = createBusRouteFromFile();
  }

/**
   * Building Route Hashmap Object by reading the input example file
   * return the route map object, empty map otherwise
   */
  public  Map<Integer, Map<Integer, Integer>> createBusRouteFromFile() {
    try {
      Scanner fileIn = new Scanner(new File(this.filePath));
      int numberOfRoutes = Integer.parseInt(fileIn.nextLine());
      Map<Integer, Map<Integer, Integer>> routeMap = new HashMap<>();
      while (fileIn.hasNext()) {
        String inputLine = fileIn.nextLine().trim();
        String[] routeSplit = inputLine.split(" ");
        int routeNumber = Integer.parseInt(routeSplit[0]);
        Map<Integer, Integer> individualRouteMap = new HashMap<>();
        for(int i = 1; i<routeSplit.length; i++){
          individualRouteMap.put(Integer.parseInt(routeSplit[i]), i);
        }
        routeMap.put(routeNumber, individualRouteMap);
      }
      if (routeMap.size() == numberOfRoutes) {
        return routeMap;
      } else {
        return Collections.emptyMap();
      }
    } catch (IOException e) {
      e.printStackTrace();
      return Collections.emptyMap();
    }

  }

  public Map<Integer, Map<Integer, Integer>> getRouteMap() {
    return routeMap;
  }
}
