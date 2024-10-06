package com.example.apimicroservicio3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;



@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private RestTemplate restTemplate;

    // Obtener reviews por bookId llamando al microservicio de libros y autor
    public List<ReviewDTO> getReviewsByBookAndAuthor(String title, String authorName) {
        // Llamada al microservicio 1 para obtener el book_id
        String bookServiceUrl = "http://api-microservicio1_c:8001/books/get_book_id?title=" + title + "&author_name=" + authorName;
        Map<String, Object> bookResponse = restTemplate.getForObject(bookServiceUrl, Map.class);
        Integer bookId = (Integer) bookResponse.get("book_id");

        if (bookId == null) {
            throw new RuntimeException("Book not found");
        }

        // Obtener las reviews desde MongoDB
        List<Review> reviews = reviewRepository.findByBookId(bookId);

        // Obtener datos del usuario desde el microservicio 2
        return reviews.stream().map(review -> {
            String userServiceUrl = "http://api-microservicio2_c:8002/users/" + review.getUserId();
            Map<String, String> userResponse = restTemplate.getForObject(userServiceUrl, Map.class);

            String userName = userResponse.getOrDefault("name", "Unknown");
            String userEmail = userResponse.getOrDefault("email", "Unknown");

            return new ReviewDTO(userName, userEmail, review.getRating(), review.getComment());
        }).toList();
    }

    // Obtener reviews por rating
    public List<BookDetailsDTO> getReviewsByRating(int rating) {
        List<Review> reviews = reviewRepository.findByRating(rating);

        return reviews.stream().map(review -> {
            // Llamada al microservicio 1 para obtener detalles del libro
            String bookServiceUrl = "http://api-microservicio1_c:8001/books/" + review.getBookId() + "/details";
            Map<String, String> bookResponse = restTemplate.getForObject(bookServiceUrl, Map.class);

            String title = bookResponse.getOrDefault("title", "Unknown");
            String authorName = bookResponse.getOrDefault("author_name", "Unknown");

            return new BookDetailsDTO(title, authorName);
        }).toList();
    }

    // Guardar una nueva review
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

}

