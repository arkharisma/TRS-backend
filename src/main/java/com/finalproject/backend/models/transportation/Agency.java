package com.finalproject.backend.models.transportation;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.finalproject.backend.models.user.User;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Agency")
public class Agency {
    @Id
    @GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @NotBlank
    @Column(name = "code")
    private String code;

    @NotBlank
    @Size(max = 50)
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "details")
    private String details;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    private User owner;

    public Agency() {
    }

    public Agency(String code, String name, String details, User owner) {
        this.code = code;
        this.name = name;
        this.details = details;
        this.owner = owner;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
