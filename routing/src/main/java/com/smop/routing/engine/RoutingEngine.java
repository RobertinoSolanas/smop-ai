package com.smop.routing.engine;

import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.graphhopper.ResponsePath;
import com.graphhopper.util.InstructionList;
import com.smop.routing.model.Route;
import com.smop.routing.model.RouteOptimization;
import com.smop.routing.model.VehicleType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class RoutingEngine {

    private final GraphHopper graphHopper;
    private final VehicleSwitchingService vehicleSwitchingService;

    public RoutingEngine(GraphHopper graphHopper, 
                        VehicleSwitchingService vehicleSwitchingService) {
        this.graphHopper = graphHopper;
        this.vehicleSwitchingService = vehicleSwitchingService;
    }

    public Route calculateRoute(double fromLat, double fromLon, 
                              double toLat, double toLon,
                              RouteOptimization optimization) {
        GHRequest request = new GHRequest(fromLat, fromLon, toLat, toLon)
                .setLocale(Locale.US)
                .setAlgorithm(optimization.getAlgorithm());

        GHResponse response = graphHopper.route(request);
        if (response.hasErrors()) {
            throw new RuntimeException("Routing failed: " + response.getErrors());
        }

        ResponsePath path = response.getBest();
        InstructionList instructions = path.getInstructions();
        
        List<VehicleType> suggestedVehicles = vehicleSwitchingService
                .suggestVehicleSwitches(path);

        return new Route(
                path.getDistance(),
                path.getTime(),
                path.getPoints(),
                instructions,
                suggestedVehicles,
                optimization
        );
    }
}
