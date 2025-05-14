package com.vetclinic.vetclinic.dtos;

import java.util.Date;

public class ConsultationDTO {

    private Long id;
    private Date startDate;
    private Date endDate;
    private int office;
    private double price;
    private String requiredExams;
    private String consultationCause;
    private String diagnostic;
    private String Treatment;

    public ConsultationDTO() {}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getOffice() {
        return office;
    }
    public void setOffice(int office) {
        this.office = office;
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

    public String getConsultationCause() {
        return consultationCause;
    }
    public void setConsultationCause(String consultationCause) {
        this.consultationCause = consultationCause;
    }

    public String getDiagnostic() {
        return diagnostic;
    }
    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getTreatment() {
        return Treatment;
    }
    public void setTreatment(String treatment) {
        Treatment = treatment;
    }
}
