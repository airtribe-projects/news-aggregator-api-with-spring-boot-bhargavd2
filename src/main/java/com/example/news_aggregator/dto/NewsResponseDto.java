package com.example.news_aggregator.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NewsResponseDto {

    String article_id;
    String title;
    String link;
    List<String> keywords;
    List<String> creator;
    String image_url;
    String pubDate;
    String description;
    String content;
    List<String> category;

}
