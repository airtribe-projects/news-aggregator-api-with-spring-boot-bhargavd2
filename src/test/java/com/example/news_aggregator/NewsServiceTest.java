package com.example.news_aggregator;

import com.example.news_aggregator.dto.NewsResponseDto;
import com.example.news_aggregator.mapper.NewResponseMapper;
import com.example.news_aggregator.service.NewsService;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class NewsServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private NewsService newsService;

    @Mock
    private Dotenv dotenv;

    @Value("${news.api.url}")
    private String url;

    public NewsServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        System.out.println("url"+url);
    }

    @Test
    public void testFetchNews() {

        String query = "sport";
        String apiResponse = "{\"status\":\"success\",\"totalResults\":61745,\"results\":"
        + "[{\"article_id\":\"4f908b80ea5737a0c8447f7d30a94edf\",\"title\":\"Duplantissetsnewpolevaultworld"
        + "recordof6.26m\",\"link\":\"https://kuwaittimes.com/article/18231/sports/othersports/duplantissets"
        + "newpolevaultworldrecordof626m/\",\"keywords\":null,\"creator\":[\"kuwaittimes\"],\"video_url\":"
        + "null,\"description\":\"CHORZOW,Poland:OlympicchampionArmandDuplantisimprovedhisownpolevaultworldr"
        + "ecordbyacentimeteratSunday’sDiamondLeagueSilesiameeting,postingamarkof6.26meter...\",\"content\":"
        + "\"CHORZOW,Poland:OlympicchampionArmandDuplantisimprovedhisownpolevaultworldrecordbyacentimeteratSunday’"
        + "sDiamondLeagueSilesiameeting,postingamarkof6.26metersonhissecondattempt.Duplantisbrokethemarkof6.25mhe"
        + "setwhendefendinghisOlympictitleinParisearlierthismonth.Itisthe10thtimethe24yearoldSwedehassetanewworld"
        + "marksincehefirstbecametheworldrecordholderwithavaultof6.17minTorun,Poland,inFebruary2020.—AFP\",\"pubD"
        + "ate\":\"2024082522:06:00\",\"image_url\":\"https://kuwaittimes.com/kuwaittimes/uploads/images/2024/08/2"
        + "5/147638.jpg\",\"source_id\":\"kuwaittimes\",\"source_priority\":295375,\"source_name\":\"KuwaitTimes"
        + "\",\"source_url\":\"https://www.kuwaittimes.com\",\"source_icon\":\"https://i.bytvi.com/domain_icons/k"
        + "uwaittimes.png\",\"language\":\"english\",\"country\":[\"kuwait\"],\"category\":[\"top\"],\"ai_tag\":"
        + "[\"sports\"],\"ai_region\":[\"chorzow\",\"poland,europe\"],\"ai_org\":null,\"sentiment\":\"positive"
        + "\",\"sentiment_stats\":{\"positive\":90.96,\"neutral\":8.34,\"negative\":0.7}}],\"nextPage\":\"17246235"
        +"60240555878\"}";

        List<NewsResponseDto> response = new ArrayList<>();
        when(dotenv.get("API_KEY")).thenReturn("123");
        ResponseEntity<String> responseEntity = new ResponseEntity<>(apiResponse, HttpStatus.OK);
        when(restTemplate.getForEntity(anyString(), eq(String.class))).thenReturn(responseEntity);

        response = newsService.fetchNews(query);

        assertNotNull(response);
        assertEquals(1, response.size());
        verify(restTemplate).getForEntity(
                contains("https://newsdata.io/api/1/latest"),
                eq(String.class)
        );

    }
}
