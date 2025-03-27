package com.vetclinic.vetclinic.dtos;

import java.util.Date;

public class ConsultationAgendaDTO {

    private Long id;
    private Date date;
    private String description;

    public ConsultationAgendaDTO() {}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
