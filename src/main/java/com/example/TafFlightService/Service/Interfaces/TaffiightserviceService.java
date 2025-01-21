package com.example.TafFlightService.Service.Interfaces;



import com.example.TafFlightService.Models.Flight;


import java.util.List;

public interface TaffiightserviceService {

    Flight AddNewFlight(Flight flight);
    List<Flight> getAllavilableflights();
    Flight getFlightById(Long flightId);
    Flight updateFlight(Long flightId, Flight flight);
    void deleteFlight(Long flightId);




}
