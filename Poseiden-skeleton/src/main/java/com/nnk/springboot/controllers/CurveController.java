package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurvePointService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;


@Controller
public class CurveController {
    private final CurvePointService curvePointService;

    @Autowired
    public CurveController(CurvePointService curvePointService) {
        this.curvePointService = curvePointService;
    }

    @GetMapping("/curvePoint/list")
    public String home(Model model, Principal principal) {
        model.addAttribute("curvePoints", curvePointService.getAll());
        model.addAttribute("connectedUser", principal.getName());
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(Model model) {
        model.addAttribute("curvePoint", new CurvePoint());
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@ModelAttribute @Valid CurvePoint curvePoint, BindingResult result) {
        if (result.hasErrors()) {
            return "curvePoint/add";
        } else {
            curvePointService.add(curvePoint);
            return "redirect:/curvePoint/list";
        }

    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        if (!model.containsAttribute("curvePoint")) {
            model.addAttribute("curvePoint", curvePointService.getById(id));
        } else {
            CurvePoint existingCurvePoint = (CurvePoint) model.getAttribute("curvePoint");
            assert existingCurvePoint != null;
            existingCurvePoint.setId(id);
        }
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @ModelAttribute("curvePoint") @Valid CurvePoint curvePoint,
                            BindingResult result) {
        if (result.hasErrors()) {
            return "/curvePoint/update";
        } else {
            curvePointService.update(curvePoint, id);
            return "redirect:/curvePoint/list";
        }
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id) {
        curvePointService.delete(id);
        return "redirect:/curvePoint/list";
    }
}
