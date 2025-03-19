package com.vetclinic.vetclinic.dtos;

public class DiegnosticDTO {

    private Long id;
    private String consultationCause;
    private String Consideration;
    private String Treatment;

    public DiegnosticDTO() {}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getConsultationCause() {
        return consultationCause;
    }
    public void setConsultationCause(String consultationCause) {
        this.consultationCause = consultationCause;
    }

    public String getConsideration() {
        return Consideration;
    }
    public void setConsideration(String consideration) {
        Consideration = consideration;
    }

    public String getTreatment() {
        return Treatment;
    }
    public void setTreatment(String treatment) {
        Treatment = treatment;
    }
}
