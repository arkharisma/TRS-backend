package com.finalproject.backend.models.transportation;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Stop")
public class Stop {
    @Id
    @GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @NotBlank
    @Column(name = "code")
    private String code;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "detail")
    private String detail;

    public Stop() {
    }

    public Stop(@NotBlank String code, @NotBlank String name, @NotBlank String detail) {
        this.code = code;
        this.name = name;
        this.detail = detail;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
