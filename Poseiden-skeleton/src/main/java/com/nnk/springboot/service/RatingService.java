package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Transactional
    public Rating add(Rating rating) {
        return ratingRepository.save(rating);
    }

    public List<Rating> getAll() {
        return ratingRepository.findAll();
    }

    public Rating getById(int id) {
        return ratingRepository.findById(id).orElse(null);
    }

    @Transactional
    public Rating update(Rating rating, int id) {
        ratingRepository.findById(id).ifPresentOrElse(r -> {
                    r.setFitchRating(rating.getFitchRating());
                    r.setMoodysRating(rating.getMoodysRating());
                    r.setSandPRating(rating.getSandPRating());
                    r.setOrderNumber(rating.getOrderNumber());
                    ratingRepository.save(r);
                },
                () -> {
                    throw new RuntimeException("Rating not found");
                }
        );
        return rating;
    }

    @Transactional
    public void delete(int id) {
        ratingRepository.deleteById(id);
    }
}
