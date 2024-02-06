package com.nnk.springboot.controllers.restcontrollers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1")
public class RatingRestController {
    private final RatingService ratingService;

    @Autowired
    public RatingRestController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("ratings")
    public List<Rating> getRatings() {
        return ratingService.getAll();
    }

    @GetMapping("rating/{id}")
    public Rating getRating(@PathVariable("id") Integer id) {
        return ratingService.getById(id);
    }

    @PostMapping("rating")
    public void addRating(@RequestBody @Valid Rating rating) {
        ratingService.add(rating);
    }

    @PutMapping("rating")
    public void updateRating(@RequestBody @Valid Rating rating) {
        ratingService.update(rating, rating.getId());
    }

    @DeleteMapping("rating/{id}")
    public void deleteMapping(@PathVariable("id") Integer id) {
        ratingService.delete(id);
    }
}
