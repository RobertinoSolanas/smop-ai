package com.smop.routing.service;

import com.graphhopper.ResponsePath;
import com.smop.routing.model.VehicleType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleSwitchingService {

    public List<VehicleType> suggestVehicleSwitches(ResponsePath path) {
        List<VehicleType> suggestions = new ArrayList<>();
        
        // Example logic for vehicle switching suggestions
        double distance = path.getDistance();
        if (distance > 5000) { // Over 5km
            suggestions.add(VehicleType.E_CAR);
        } else if (distance > 2000) { // 2-5km
            suggestions.add(VehicleType.E_SCOOTER);
        } else { // Under 2km
            suggestions.add(VehicleType.E_BIKE);
        }
        
        return suggestions;
    }
}
