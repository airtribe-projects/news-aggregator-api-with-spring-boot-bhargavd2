package com.example.news_aggregator.mapper;

import com.example.news_aggregator.dto.ExternalApiResponseDto;
import com.example.news_aggregator.dto.NewsResponseDto;
import com.example.news_aggregator.dto.ExternalApiResponseResultDto;
import com.example.news_aggregator.exception.ExternalApiException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.objenesis.instantiator.sun.MagicInstantiator;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewResponseMapper {

    private final ObjectMapper objectMapper;

    public NewResponseMapper() {
        // Initialize ObjectMapper
        this.objectMapper = new ObjectMapper();
    }

    public List<NewsResponseDto> JsonToNewsResponseDtoMapper(String jsonArrayString)
    {
        List<NewsResponseDto> newsResponseDtoList = new ArrayList<>();
        try {

            ExternalApiResponseDto externalApiResponseDto = objectMapper.readValue(jsonArrayString,ExternalApiResponseDto.class);

            for(ExternalApiResponseResultDto r:externalApiResponseDto.getResults())
            {
                newsResponseDtoList.add(
                        NewsResponseDto.builder()
                                .pubDate(r.getPublicationDate())
                                .article_id(r.getArticleId())
                                .link(r.getLink())
                                .creator(r.getCreator())
                                .description(r.getDescription())
                                .image_url(r.getImageUrl())
                                .content(r.getContent())
                                .keywords(r.getKeywords())
                                .title(r.getTitle())
                                .category(r.getCategory())
                                .build()
                );
            }

        }catch (Exception e)
        {
            System.out.println(Arrays.toString(e.getStackTrace()));
            throw new ExternalApiException("Issue with External Api response");
        }
        return newsResponseDtoList;
    }
}
