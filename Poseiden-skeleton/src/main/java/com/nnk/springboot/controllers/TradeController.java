package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class TradeController {
    private final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @GetMapping("/trade/list")
    public String home(Model model)
    {
        model.addAttribute("trades", tradeService.getAll());
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Model model) {
        model.addAttribute("trade", new Trade());
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@ModelAttribute @Valid Trade trade, BindingResult result) {
        if (result.hasErrors())
            return "trade/add";
        else {
            tradeService.add(trade);
            return "redirect:/trade/list";
        }
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        if (!model.containsAttribute("trade"))
            model.addAttribute("trade", tradeService.getById(id));
        else {
            Trade existingTrade = (Trade) model.getAttribute("trade");
            assert existingTrade != null;
            existingTrade.setTradeId(id);
        }
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result) {
        if (result.hasErrors())
            return "/trade/update";
        else {
            tradeService.update(trade, id);
            return "redirect:/trade/list";
        }
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id) {
        tradeService.delete(id);
        return "redirect:/trade/list";
    }
}
