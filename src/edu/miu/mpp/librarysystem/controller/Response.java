package edu.miu.mpp.librarysystem.controller;

public class Response {
    private String message;
    private Boolean status;
    private Object data;

    public Response(String message, Boolean status, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public Boolean getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }
}
