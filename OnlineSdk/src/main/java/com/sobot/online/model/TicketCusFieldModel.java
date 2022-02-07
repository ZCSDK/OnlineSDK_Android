package com.sobot.online.model;

public class TicketCusFieldModel {

    private String id;
    private Object value;
    private String summary;

    @Override
    public String toString() {
        return "TicketCusFieldModel{" +
                "id='" + id + '\'' +
                ", value='" + value + '\'' +
                ", summary='" + summary + '\'' +
                '}';
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}