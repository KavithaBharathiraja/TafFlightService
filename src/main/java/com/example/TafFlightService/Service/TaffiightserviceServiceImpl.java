package com.example.TafFlightService.Service;

import com.example.TafFlightService.Models.Flight;
import com.example.TafFlightService.Service.Interfaces.TaffiightserviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Service
public class TaffiightserviceServiceImpl implements TaffiightserviceService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String BASE_URL = "http://localhost:8080/datastore/flights"; // Change to your datastore microservice URL

    @Override
    public Flight AddNewFlight(Flight flight) {
        // Send a POST request to the datastore service to add a new flight
        ResponseEntity<Flight> response = restTemplate.postForEntity(BASE_URL, flight, Flight.class);
        return response.getBody(); // Return the created flight
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
        // Send a DELETE request to remove the flight by ID
        String url = BASE_URL + "/" + flightId;
        restTemplate.delete(url);  // Remove the flight from the datastore service
    }
}
