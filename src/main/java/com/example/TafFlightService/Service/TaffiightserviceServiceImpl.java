package com.example.TafFlightService.Service;

import com.example.TafFlightService.Models.Flight;
import com.example.TafFlightService.Service.Interfaces.TaffiightserviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@Service
public class TaffiightserviceServiceImpl implements TaffiightserviceService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String BASE_URL = "http://localhost:8080/datastore/flights"; // Change to your datastore microservice URL

    @Override
    public Flight AddNewFlight(Flight flight) {
        try {
            ResponseEntity<Flight> response = restTemplate.postForEntity(BASE_URL, flight, Flight.class);
            return response.getBody(); // Return the created flight
        } catch (HttpServerErrorException e) {
            // Log the error details for debugging
            System.err.println("Error response from datastore: " + e.getResponseBodyAsString());
            throw new RuntimeException("Failed to add new flight: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Flight> getAllavilableflights() {
        // Send a GET request to fetch all available flights
        ResponseEntity<List> response = restTemplate.exchange(BASE_URL, org.springframework.http.HttpMethod.GET, null, List.class);
        return response.getBody(); // Return the list of flights
    }

    @Override
    public Flight getFlightById(Long flightId) {
        // Send a GET request to fetch a flight by its ID
        String url = BASE_URL + "/" + flightId;
        ResponseEntity<Flight> response = restTemplate.getForEntity(url, Flight.class);
        return response.getBody(); // Return the flight if found
    }

    @Override
    public Flight updateFlight(Long flightId, Flight flight) {
        // Send a PUT request to update the flight details
        String url = BASE_URL + "/" + flightId;
        restTemplate.put(url, flight);  // Update the flight in the datastore service
        return flight;  // Return the updated flight
    }

    @Override
    public void deleteFlight(Long flightId) {
        String url = BASE_URL + "/" + flightId;
        try {
            // Send DELETE request to remove the flight by ID
            restTemplate.delete(url); // This will call the DELETE endpoint on the datastore service
            System.out.println("Flight with ID " + flightId + " deleted successfully.");
        } catch (HttpServerErrorException e) {
            // Log the error response for debugging
            System.err.println("Error response from datastore while deleting flight with ID " + flightId + ": " + e.getResponseBodyAsString());
            throw new RuntimeException("Failed to delete flight with ID " + flightId + ": " + e.getMessage(), e);
        } catch (Exception e) {
            // Handle other exceptions
            System.err.println("An unexpected error occurred while deleting flight with ID " + flightId + ": " + e.getMessage());
            throw new RuntimeException("Unexpected error occurred while deleting flight with ID " + flightId, e);
        }
    }

}
