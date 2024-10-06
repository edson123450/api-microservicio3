package com.example.apimicroservicio3;

import com.example.apimicroservicio3.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends MongoRepository<Review, String> {
    List<Review> findByBookId(int bookId);
    List<Review> findByRating(int rating);
    // Buscar review por book_id, author_id y user_id
    Optional<Review> findByBookIdAndAuthorIdAndUserId(int bookId, int authorId, int userId);
}
