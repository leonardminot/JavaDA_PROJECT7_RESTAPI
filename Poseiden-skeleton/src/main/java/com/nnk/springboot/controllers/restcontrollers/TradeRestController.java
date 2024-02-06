package com.nnk.springboot.controllers.restcontrollers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class TradeRestController {
    private final TradeService tradeService;

    @Autowired
    public TradeRestController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @GetMapping("trades")
    public List<Trade> getTrades() {
        return tradeService.getAll();
    }

    @GetMapping("trade/{id}")
    public Trade getTradeById(@PathVariable("id") Integer id) {
        return tradeService.getById(id);
    }

    @PostMapping("trade")
    public void addTrade(@RequestBody @Valid Trade trade) {
        tradeService.add(trade);
    }

    @PutMapping("trade")
    public void updateTrade(@RequestBody @Valid Trade trade) {
        tradeService.update(trade, trade.getTradeId());
    }

    @DeleteMapping("trade/{id}")
    public void deleteTrade(@PathVariable("id") Integer id) {
        tradeService.delete(id);
    }
}
