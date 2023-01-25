package edu.miu.mpp.librarysystem.controller;

import java.util.Objects;

public final class Response {
    private final String message;
    private final Boolean status;
    private final Object data;

    Response(String message, Boolean status, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public String message() {
        return message;
    }

    public Boolean status() {
        return status;
    }

    public Object data() {
        return data;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Response) obj;
        return Objects.equals(this.message, that.message) &&
                Objects.equals(this.status, that.status) &&
                Objects.equals(this.data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, status, data);
    }

    @Override
    public String toString() {
        return "Response[" +
                "message=" + message + ", " +
                "status=" + status + ", " +
                "data=" + data + ']';
    }

}
