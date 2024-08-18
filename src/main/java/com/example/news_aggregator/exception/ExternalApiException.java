package com.example.news_aggregator.exception;

public class ExternalApiException  extends RuntimeException{
    public ExternalApiException(String message)
    {
        super(message);
    }
}
