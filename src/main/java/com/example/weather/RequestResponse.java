package com.example.weather;

public class RequestResponse {
    private final String title;
    private  final String message;

    public RequestResponse(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }
}
