package com.example.news_aggregator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ExternalApiResponseResultDto {
    @JsonProperty("article_id")
    private String articleId;
    private String title;
    private String link;
    private List<String> keywords;
    private List<String> creator;
    private String video_url;
    private String description;
    private String content;
    @JsonProperty("pubDate")
    private String publicationDate;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("source_id")
    private String sourceId;
    @JsonProperty("source_priority")
    private int sourcePriority;
    @JsonProperty("source_name")
    private String sourceName;
    @JsonProperty("source_url")
    private String sourceUrl;
    @JsonProperty("source_icon")
    private String sourceIcon;
    private String language;
    private List<String> country;
    private List<String> category;
    @JsonProperty("ai_tag")
    private String aiTag;
    @JsonProperty("sentiment")
    private String sentiment;
    @JsonProperty("sentiment_stats")
    private String sentimentStats;
    @JsonProperty("ai_region")
    private String aiRegion;
    @JsonProperty("ai_org")
    private String aiOrg;
    private boolean duplicate;

    // Getters and setters

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public List<String> getCreator() {
        return creator;
    }

    public void setCreator(List<String> creator) {
        this.creator = creator;
    }

    public String getvideo_url() {
        return video_url;
    }

    public void setvideo_url(String videoUrl) {
        this.video_url = videoUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public int getSourcePriority() {
        return sourcePriority;
    }

    public void setSourcePriority(int sourcePriority) {
        this.sourcePriority = sourcePriority;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getSourceIcon() {
        return sourceIcon;
    }

    public void setSourceIcon(String sourceIcon) {
        this.sourceIcon = sourceIcon;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<String> getCountry() {
        return country;
    }

    public void setCountry(List<String> country) {
        this.country = country;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public String getAiTag() {
        return aiTag;
    }

    public void setAiTag(String aiTag) {
        this.aiTag = aiTag;
    }

    public String getSentiment() {
        return sentiment;
    }

    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }

    public String getSentimentStats() {
        return sentimentStats;
    }

    public void setSentimentStats(String sentimentStats) {
        this.sentimentStats = sentimentStats;
    }

    public String getAiRegion() {
        return aiRegion;
    }

    public void setAiRegion(String aiRegion) {
        this.aiRegion = aiRegion;
    }

    public String getAiOrg() {
        return aiOrg;
    }

    public void setAiOrg(String aiOrg) {
        this.aiOrg = aiOrg;
    }

    public boolean isDuplicate() {
        return duplicate;
    }

    public void setDuplicate(boolean duplicate) {
        this.duplicate = duplicate;
    }
}
