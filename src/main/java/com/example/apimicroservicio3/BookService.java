package com.example.apimicroservicio3;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookService {

    private final RestTemplate restTemplate;

    public BookService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getBookDetails(int bookId) {
        String url = "http://api-microservicio1_c:8001/books/" + bookId + "/details";
        return restTemplate.getForObject(url, String.class);
    }
}