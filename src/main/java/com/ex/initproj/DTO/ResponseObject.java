package com.ex.initproj.DTO;

public class ResponseObject<T> {
    private String message;
    private T data;

    public ResponseObject(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public ResponseObject() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
