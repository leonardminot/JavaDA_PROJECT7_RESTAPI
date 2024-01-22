package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class BidListController {
    private final BidService bidService;

    @Autowired
    public BidListController(BidService bidService) {
        this.bidService = bidService;
    }

    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        model.addAttribute("bidLists", bidService.getAll());
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(Model model) {
        model.addAttribute("bidList", new BidList());
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@ModelAttribute @Valid BidList bid, BindingResult result) {
        if (result.hasErrors())
            return "bidList/add";
        else {
            bidService.add(bid);
            return "redirect:/bidList/list";
        }
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        if (!model.containsAttribute("bidList"))
            model.addAttribute("bidList", bidService.getById(id));
        else {
            BidList existingBidList = (BidList) model.getAttribute("bidList");
            assert existingBidList != null;
            existingBidList.setBidListId(id);
        }
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @ModelAttribute @Valid BidList bidList,
                             BindingResult result) {
        if (result.hasErrors())
            return "/bidList/update";
        else {
            bidService.update(bidList, id);
            return "redirect:/bidList/list";
        }

    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id) {
        bidService.delete(id);
        return "redirect:/bidList/list";
    }
}
