package com.gfg.flightdemo;

import java.util.List;
import java.util.concurrent.*;

class FlightServiceCallable implements Callable<List<FlightData>> {

    private FlightServiceInterface flightService;

    private String criteria;

    public FlightServiceCallable(FlightServiceInterface flightService, String criteria) {
        this.flightService = flightService;
        this.criteria = criteria;
    }

    @Override
    public List<FlightData> call() throws Exception {
        return flightService.getFlightDataOfCriteria(criteria);
    }
}

public class AggregatorDemo {

    private static ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        String criteria ="LKO:DLI";

        FlightServiceInterface ingoFlightService = new IndigoFlightService();
        FlightServiceInterface goAirFlightService = new GoAirFlightService();

        FlightServiceCallable goAirReq = new FlightServiceCallable(goAirFlightService,criteria);
        FlightServiceCallable indigoReq = new FlightServiceCallable(ingoFlightService,criteria);

        Future<List<FlightData>> goAirResponse = executorService.submit(goAirReq);
        Future<List<FlightData>> indigoResponse = executorService.submit(indigoReq);

        List<FlightData> list1 = goAirResponse.get();

        List<FlightData> list2 = indigoResponse.get();

        /*

         */


    }
}
