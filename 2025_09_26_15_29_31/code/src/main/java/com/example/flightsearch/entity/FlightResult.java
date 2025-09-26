package com.example.flightsearch.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity representing a flight result for a search.
 */
@Entity
@Table(name = "FLIGHT_RESULT")
public class FlightResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "searchCriteriaId")
    private SearchCriteria searchCriteria;
    private String flightId;
    private String airline;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private double price;
    private String duration;
    private int stops;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public SearchCriteria getSearchCriteria() { return searchCriteria; }
    public void setSearchCriteria(SearchCriteria searchCriteria) { this.searchCriteria = searchCriteria; }
    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }
    public LocalDateTime getDeparture() { return departure; }
    public void setDeparture(LocalDateTime departure) { this.departure = departure; }
    public LocalDateTime getArrival() { return arrival; }
    public void setArrival(LocalDateTime arrival) { this.arrival = arrival; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }
    public int getStops() { return stops; }
    public void setStops(int stops) { this.stops = stops; }
}
