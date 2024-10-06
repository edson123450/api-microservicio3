package com.example.apimicroservicio3;

import com.example.apimicroservicio3.Review;
import com.example.apimicroservicio3.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ReviewService reviewService;

    // Obtener todas las reviews
    @GetMapping("/all")
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // Obtener reviews por bookId y autor
    @GetMapping("/by-book-author")
    public List<ReviewDTO> getReviewsByBookAndAuthor(@RequestParam String title, @RequestParam String authorName) {
        return reviewService.getReviewsByBookAndAuthor(title, authorName);
    }

    // Obtener reviews por rating
    @GetMapping("/by-rating")
    public List<BookDetailsDTO> getReviewsByRating(@RequestParam int rating) {
        return reviewService.getReviewsByRating(rating);
    }

    // Guardar una nueva review
    @PostMapping("/new")
    public ResponseEntity<String> addNewReview(@RequestBody Review review) {
        reviewService.saveReview(review);
        return ResponseEntity.status(201).body("Review saved successfully");
    }

    // Nueva API: Verificar si hay una review por book_id, author_id y user_id
    @GetMapping("/check-review")
    public Respuesta checkReview(@RequestParam int bookId, @RequestParam int authorId, @RequestParam int userId) {
        Optional<Review> reviewOpt = reviewRepository.findByBookIdAndAuthorIdAndUserId(bookId, authorId, userId);

        if (reviewOpt.isPresent()) {
            Review review = reviewOpt.get();
            // Si la review existe, devolver message = "si" y los datos de la review
            return new Respuesta("si", review.getRating(), review.getComment());
        } else {
            // Si no se encuentra la review, devolver message = "no" y valores por defecto
            return new Respuesta("no", 0, "");
        }
    }
}