package com.example.TafFlightService.Controller;

import com.example.TafFlightService.Models.Flight;
import com.example.TafFlightService.Service.TaffiightserviceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/flights")  // Base URL for this controller
public class FlightController {

    @Autowired
    private TaffiightserviceServiceImpl tafflightserviceService;

    // Endpoint to add a new flight
    @PostMapping()
    public ResponseEntity<Flight> addNewFlight(@RequestBody Flight flight) {
        Flight createdFlight = tafflightserviceService.AddNewFlight(flight);
        return new ResponseEntity<>(createdFlight, HttpStatus.CREATED);
    }

    // Endpoint to get all available flights
    @GetMapping()
    public ResponseEntity<List<Flight>> getAllAvailableFlights() {
        List<Flight> flights = tafflightserviceService.getAllavilableflights();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    // Endpoint to get a flight by ID
    @GetMapping("/{flightId}")
    public ResponseEntity<Flight> getFlight(@PathVariable Long flightId) {
        Flight flight = tafflightserviceService.getFlightById(flightId);
        if (flight != null) {
            return new ResponseEntity<>(flight, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Flight not found
        }
    }

    // Endpoint to update a flight
    @PutMapping("/{flightId}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long flightId, @RequestBody Flight flight) {
        Flight updatedFlight = tafflightserviceService.updateFlight(flightId, flight);
        return new ResponseEntity<>(updatedFlight, HttpStatus.OK);
    }

    // Endpoint to delete a flight by ID
    @DeleteMapping("/{flightId}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long flightId) {
        tafflightserviceService.deleteFlight(flightId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Successfully deleted
    }
}