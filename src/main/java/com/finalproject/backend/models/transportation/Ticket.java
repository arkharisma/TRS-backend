package com.finalproject.backend.models.transportation;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.finalproject.backend.models.user.User;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Ticket")
public class Ticket {
    @Id
    @GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @NotNull
    @Column(name = "seatNumber")
    private Integer seatNumber;

    @NotNull
    @Column(name = "cancellable")
    private Boolean cancellable;

    @NotBlank
    @Column(name = "journeyDate")
    private String journeyDate;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    private User passenger;
    
    @OneToOne(targetEntity = TripSchedule.class, fetch = FetchType.LAZY)
    private TripSchedule tripSchedule;

    public Ticket() {
    }

    public Ticket(Integer seatNumber, Boolean cancellable, String journeyDate, User passenger, TripSchedule tripSchedule) {
        this.seatNumber = seatNumber;
        this.cancellable = cancellable;
        this.journeyDate = journeyDate;
        this.passenger = passenger;
        this.tripSchedule = tripSchedule;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Boolean getCancellable() {
        return cancellable;
    }

    public void setCancellable(Boolean cancellable) {
        this.cancellable = cancellable;
    }

    public String getJourneyDate() {
        return journeyDate;
    }

    public void setJourneyDate(String journeyDate) {
        this.journeyDate = journeyDate;
    }

    public User getPassenger() {
        return passenger;
    }

    public void setPassenger(User passenger) {
        this.passenger = passenger;
    }

    public TripSchedule getTripSchedule() {
        return tripSchedule;
    }

    public void setTripSchedule(TripSchedule tripSchedule) {
        this.tripSchedule = tripSchedule;
    }
}
