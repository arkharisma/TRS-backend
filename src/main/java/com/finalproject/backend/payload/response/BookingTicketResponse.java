package com.finalproject.backend.payload.response;

public class BookingTicketResponse {
    private String id;

    public BookingTicketResponse() {
    }

    public BookingTicketResponse(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
