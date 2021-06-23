package com.finalproject.backend.payload.response;

import com.finalproject.backend.models.transportation.TripSchedule;

public class TicketResponse {
    private String id;
    private Boolean cancellable;
    private String journey_date;
    private Integer seat_number;
    private String passenger;
    private TripSchedule trip_schedule;

    public TicketResponse() {
    }

    public TicketResponse(String id, Boolean cancellable, String journey_date, Integer seat_number, String passenger,
            TripSchedule trip_schedule) {
        this.id = id;
        this.cancellable = cancellable;
        this.journey_date = journey_date;
        this.seat_number = seat_number;
        this.passenger = passenger;
        this.trip_schedule = trip_schedule;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getCancellable() {
        return cancellable;
    }

    public void setCancellable(Boolean cancellable) {
        this.cancellable = cancellable;
    }

    public String getJourney_date() {
        return journey_date;
    }

    public void setJourney_date(String journey_date) {
        this.journey_date = journey_date;
    }

    public Integer getSeat_number() {
        return seat_number;
    }

    public void setSeat_number(Integer seat_number) {
        this.seat_number = seat_number;
    }

    public String getPassenger() {
        return passenger;
    }

    public void setPassenger(String passenger) {
        this.passenger = passenger;
    }

    public TripSchedule getTrip_schedule() {
        return trip_schedule;
    }

    public void setTrip_schedule(TripSchedule trip_schedule) {
        this.trip_schedule = trip_schedule;
    }
}
