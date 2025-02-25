package com.smop.routing.controller;

import com.smop.routing.engine.RoutingEngine;
import com.smop.routing.model.Route;
import com.smop.routing.model.RouteOptimization;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/routing")
public class RoutingController {

    private final RoutingEngine routingEngine;

    public RoutingController(RoutingEngine routingEngine) {
        this.routingEngine = routingEngine;
    }

    @GetMapping("/route")
    public Route getRoute(
            @RequestParam double fromLat,
            @RequestParam double fromLon,
            @RequestParam double toLat,
            @RequestParam double toLon,
            @RequestParam(defaultValue = "FASTEST") RouteOptimization optimization) {
        
        return routingEngine.calculateRoute(
                fromLat, fromLon, toLat, toLon, optimization);
    }
}
