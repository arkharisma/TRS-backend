package com.finalproject.backend.models.transportation;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Bus")
public class Bus {
    @Id
    @GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @NotBlank
    @Column(name = "code")
    private String code;

    @NotNull
    @Column(name = "capacity")
    private Integer capacity;

    @NotBlank
    @Column(name = "make")
    private String make;

    @ManyToOne(targetEntity = Agency.class, fetch = FetchType.LAZY)
    private Agency agency;

    public Bus() {
    }

    public Bus(String code, Integer capacity, String make, Agency agency) {
        this.code = code;
        this.capacity = capacity;
        this.make = make;
        this.agency = agency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }
}
