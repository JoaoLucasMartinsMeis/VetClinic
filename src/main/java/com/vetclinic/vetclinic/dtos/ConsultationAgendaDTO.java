package com.vetclinic.vetclinic.dtos;

import java.time.Instant;

public class ConsultationAgendaDTO {

    private Long id;
    private Instant date;
    private String description;

    public ConsultationAgendaDTO() {}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDate() {
        return date;
    }
    public void setDate(Instant date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
