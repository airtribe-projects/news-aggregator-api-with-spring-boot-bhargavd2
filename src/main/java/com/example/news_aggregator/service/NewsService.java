package com.example.news_aggregator.service;

import com.example.news_aggregator.exception.ExternalApiException;
import com.example.news_aggregator.mapper.NewResponseMapper;
import com.example.news_aggregator.model.PreferencesEnum;
import com.example.news_aggregator.dto.NewsResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class NewsService {

    @Autowired
    UserService userService;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Dotenv dotenv;

    @Value("${news.api.url}")
    private String url;

    @Autowired
    NewResponseMapper newResponseMapper;

    public Mono<NewsResponseDto> fetchNewsByCategoty(String category) {
        String apiKey = dotenv.get("API_KEY");
        System.out.println("key"+apiKey);
        return webClientBuilder.build()
                .get()
                .uri("url"+"&apiKey="+apiKey+"&size=1&language=en&category="+category)
                .retrieve()
                .bodyToMono(NewsResponseDto.class);
    }

//    public Mono<NewsResponseDto> fetchNews(String category) {
//        String apiKey = dotenv.get("API_KEY");
//        System.out.println("key"+apiKey);
//        System.out.println("url"+url);
//        System.out.println(url+"apiKey="+apiKey+"&size=1&language=en");
//        return webClientBuilder.build()
//                .get()
//                .uri(url+"&apiKey="+apiKey+"&size=1&language=en")
//                .retrieve()
//                .bodyToMono(NewsResponseDto.class);
//    }

    public CompletableFuture<NewsResponseDto> fetchNewsAsync(String category) {
        String apiKey = dotenv.get("API_KEY");
        System.out.println("key"+apiKey);
        System.out.println("url"+url);
        System.out.println(url+"apiKey="+apiKey+"&size=1&language=en");
        CompletableFuture<NewsResponseDto> n = CompletableFuture.supplyAsync(() -> restTemplate.getForObject(url+"apiKey="+apiKey+"&size=1&language=en", NewsResponseDto.class));
        System.out.println(n.toString());
        return n;
        }

    public List<NewsResponseDto> fetchNews(String category) {
        String apiKey = dotenv.get("API_KEY");
        System.out.println("key"+apiKey);
        System.out.println("url"+url);
        System.out.println(url+"apiKey="+apiKey+"&size=1&language=en");
        String response = "[{}]";
        try {
           response = restTemplate.getForObject(url+"apiKey="+apiKey+"&size=1&language=en", String.class);
            System.out.println(response);
        }catch (Exception e)
        {
            throw new ExternalApiException(e.getMessage());
        }

        return newResponseMapper.JsonToNewsResponseDtoMapper(response);
    }


   // @Cacheable("news")
    public List<NewsResponseDto> getNews(String username) {
        Set<PreferencesEnum> preferencesSet =  userService.getPreferences(username);
        String preferences =preferencesSet.stream()
                .map(Enum::name)
                .collect(Collectors.joining(", "));
        return fetchNews(preferences);
    }

    //@CacheEvict(value = "news", allEntries = true)
   // @Scheduled(fixedRate = 3600000)
    public void evictCache() {
        System.out.println("Evicting cache...");
    }

}

