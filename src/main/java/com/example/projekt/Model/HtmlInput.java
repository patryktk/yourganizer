package com.example.projekt.Model;

import java.util.Date;

public class HtmlInput {

    private Long longType;
    private String stringType;
    private Date dateType;

    public HtmlInput(Long longType, String stringType, Date dateType) {
        this.longType = longType;
        this.stringType = stringType;
        this.dateType = dateType;
    }

    public HtmlInput() {
    }

    public Long getLongType() {
        return longType;
    }

    public void setLongType(Long longType) {
        this.longType = longType;
    }

    public String getStringType() {
        return stringType;
    }

    public void setStringType(String stringType) {
        this.stringType = stringType;
    }

    public Date getDateType() {
        return dateType;
    }

    public void setDateType(Date dateType) {
        this.dateType = dateType;
    }
}
