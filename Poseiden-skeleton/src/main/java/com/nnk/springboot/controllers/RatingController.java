package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class RatingController {
    // TODO: Inject Rating service
    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("/rating/list")
    public String home(Model model) {
        model.addAttribute("ratings", ratingService.getAll());
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Model model) {
        model.addAttribute("rating", new Rating());
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@ModelAttribute @Valid Rating rating, BindingResult result) {
        if (result.hasErrors()) {
            return "rating/add";
        } else {
            ratingService.add(rating);
            return "redirect:/rating/list";
        }
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        if (!model.containsAttribute("rating")) {
            model.addAttribute("rating", ratingService.getById(id));
        } else {
            Rating existingRating = (Rating) model.getAttribute("rating");
            assert existingRating != null;
            existingRating.setId(id);
        }
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "/rating/update";
        } else {
            ratingService.update(rating, id);
            return "redirect:/rating/list";
        }

    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id) {
        ratingService.delete(id);
        return "redirect:/rating/list";
    }
}
