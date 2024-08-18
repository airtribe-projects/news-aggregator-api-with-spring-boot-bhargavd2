package com.example.news_aggregator.dto;

import java.util.List;

public class ExternalApiResponseDto {

    private String status;
    private int totalResults;
    private List<ExternalApiResponseResultDto> results;
    private String nextPage;

    // Getters and setters

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<ExternalApiResponseResultDto> getResults() {
        return results;
    }

    public void setResults(List<ExternalApiResponseResultDto> externalApiResponseResultDtos) {
        this.results = externalApiResponseResultDtos;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }
}

