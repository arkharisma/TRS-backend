package com.finalproject.backend.models.transportation;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "TripSchedule")
public class TripSchedule {
    @Id
    @GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @NotBlank
    @Column(name = "tripDate")
    private String tripDate;

    @NotNull
    @Column(name = "availableSeats")
    private Integer availableSeats;

    @ManyToOne(targetEntity = Trip.class, fetch = FetchType.LAZY)
    private Trip tripDetail;

    public TripSchedule() {
    }

    public TripSchedule(@NotBlank String tripDate, @NotNull Integer availableSeats, Trip tripDetail) {
        this.tripDate = tripDate;
        this.availableSeats = availableSeats;
        this.tripDetail = tripDetail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTripDate() {
        return tripDate;
    }

    public void setTripDate(String tripDate) {
        this.tripDate = tripDate;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Trip getTripDetail() {
        return tripDetail;
    }

    public void setTripDetail(Trip tripDetail) {
        this.tripDetail = tripDetail;
    }
}
