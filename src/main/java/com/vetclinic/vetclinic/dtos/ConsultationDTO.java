package com.vetclinic.vetclinic.dtos;

import java.time.Instant;
import java.util.Date;

public class ConsultationDTO {

    private Long id;
    private Date date;
    private int consultory;
    private double price;
    private String requiredExams;

    public ConsultationDTO() {}

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

    public int getConsultory() {
        return consultory;
    }
    public void setConsultory(int consultory) {
        this.consultory = consultory;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public String getRequiredExams() {
        return requiredExams;
    }
    public void setRequiredExams(String requiredExams) {
        this.requiredExams = requiredExams;
    }
}
