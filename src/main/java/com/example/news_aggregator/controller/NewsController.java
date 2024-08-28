package com.example.news_aggregator.controller;

import com.example.news_aggregator.service.NewsService;
import com.example.news_aggregator.dto.NewsResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

//    @Autowired
//    private ArticleService articleService;

    @GetMapping("/")
    public List<NewsResponseDto> getNews(Authentication authentication) {
//        return newsService.getNews(authentication.getName())
//                .map(news -> ResponseEntity.ok(news))
//                .defaultIfEmpty(ResponseEntity.notFound().build());

        return newsService.getNews(authentication.getName());
    }

//    @PostMapping("/{id}/favorite")
//    public ResponseEntity<?> markAsFavorite(@PathVariable Long id) {
//        articleService.markAsFavorite(id);
//        return ResponseEntity.ok().body("Marked as favorite");
//    }
//
//    @GetMapping("/read")
//    public List<NewsArticle> getReadArticles() {
//        return articleService.getReadArticles();
//    }
//
//    @GetMapping("/favorites")
//    public List<NewsArticle> getFavoriteArticles() {
//        return articleService.getFavoriteArticles();
//    }
//
//    @GetMapping("/search/{keyword}")
//    public List<NewsArticle> searchNews(@PathVariable String keyword) {
//        return articleService.searchArticles(keyword);
//    }

}

