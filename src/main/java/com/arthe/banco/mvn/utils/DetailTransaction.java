package com.arthe.banco.mvn.utils;

import java.util.Date;
import java.util.Map;

public class DetailTransaction {
    public static final String ERROR_DESCRIPTION = "description";
    public static final String ERROR_BUSINESS_MEANING = "businessMeaning" ;

    private int status;
    private Date timeStamp;
    private int code;
    private int severity;
    private String message;
    private Map<String,String> errors;

    public DetailTransaction(int status, int code, int severity, String message, Map<String, String> errors) {
        this.status = status;
        this.timeStamp = getTimeStamp();
        this.code = code;
        this.severity = severity;
        this.message = message;
        this.errors = errors;
    }

    public Date getTimeStamp() {
        timeStamp = new Date();
        return timeStamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
