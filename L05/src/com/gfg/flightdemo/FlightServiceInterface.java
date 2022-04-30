package com.gfg.flightdemo;

import java.util.List;

public interface FlightServiceInterface {

    List<FlightData> getFlightDataOfCriteria(String criteria);
}
