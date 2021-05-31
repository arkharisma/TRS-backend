package com.finalproject.backend.models.transportation;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Trip")
public class Trip {
    @Id
    @GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @NotNull
    @Column(name = "fare")
    private Integer fare;

    @NotBlank
    @Column(name = "journeyTime")
    private String journeyTime;

    @OneToOne(targetEntity = Stop.class, fetch = FetchType.LAZY)
    private Stop sourceStop;

    @OneToOne(targetEntity = Stop.class, fetch = FetchType.LAZY)
    private Stop destStop;

    @ManyToOne(targetEntity = Bus.class, fetch = FetchType.LAZY)
    private Bus bus;

    @ManyToOne(targetEntity = Agency.class, fetch = FetchType.LAZY)
    private Agency agency;

    public Trip() {
    }

    public Trip(Integer fare, String journeyTime, Stop sourceStop, Stop destStop, Bus bus, Agency agency) {
        this.fare = fare;
        this.journeyTime = journeyTime;
        this.sourceStop = sourceStop;
        this.destStop = destStop;
        this.bus = bus;
        this.agency = agency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getFare() {
        return fare;
    }

    public void setFare(Integer fare) {
        this.fare = fare;
    }

    public String getJourneyTime() {
        return journeyTime;
    }

    public void setJourneyTime(String journeyTime) {
        this.journeyTime = journeyTime;
    }

    public Stop getSourceStop() {
        return sourceStop;
    }

    public void setSourceStop(Stop sourceStop) {
        this.sourceStop = sourceStop;
    }

    public Stop getDestStop() {
        return destStop;
    }

    public void setDestStop(Stop destStop) {
        this.destStop = destStop;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }
}
