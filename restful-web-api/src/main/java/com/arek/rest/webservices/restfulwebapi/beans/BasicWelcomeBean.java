package com.arek.rest.webservices.restfulwebapi.beans;

public class BasicWelcomeBean {
    private String message;

    public BasicWelcomeBean(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
