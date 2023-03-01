package com.arthe.banco.mvn.utils;

import java.util.List;

public class GenericResponse<T> {
    private List<T> lstData;
    private T data;
    private String message;
    private String detailMessage;
    private boolean success;
    private DetailTransaction errorDetails;

    public GenericResponse() {
    }

    public GenericResponse(
            List<T> lstData,
            T data,
            String message,
            String detailMessage,
            boolean success) {
        this.lstData = lstData;
        this.data = data;
        this.message = message;
        this.detailMessage = detailMessage;
        this.success = success;
    }

    public GenericResponse(
            List<T> lstData,
            T data,
            String message,
            String detailMessage,
            boolean success,
            DetailTransaction errorDetails) {
        this.lstData = lstData;
        this.data = data;
        this.message = message;
        this.detailMessage = detailMessage;
        this.success = success;
        this.errorDetails = errorDetails;
    }

    public List<T> getLstData() {
        return lstData;
    }

    public void setLstData(List<T> lstData) {
        this.lstData = lstData;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public void setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
    }

    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DetailTransaction getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(DetailTransaction errorDetails) {
        this.errorDetails = errorDetails;
    }


}
