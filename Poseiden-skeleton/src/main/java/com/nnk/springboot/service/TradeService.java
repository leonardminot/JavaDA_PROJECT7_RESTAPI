package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeService {
    private final TradeRepository tradeRepository;
    private final DateProvider dateProvider;

    @Autowired
    public TradeService(TradeRepository tradeRepository, DateProvider dateProvider) {
        this.tradeRepository = tradeRepository;
        this.dateProvider = dateProvider;
    }

    public Trade add(Trade trade) {
        return tradeRepository.save(new Trade(
                trade.getAccount(),
                trade.getType(),
                trade.getBuyQuantity(),
                trade.getSellQuantity(),
                trade.getBuyPrice(),
                trade.getSellPrice(),
                trade.getBenchmark(),
                dateProvider.getNow()
        ));
    }

    public List<Trade> getAll() {
        return tradeRepository.findAll();
    }

    public Trade getById(int id) {
        return tradeRepository.findById(id).orElse(null);
    }

    public Trade update(Trade trade, int id) {
        tradeRepository.findById(id)
                .ifPresentOrElse(
                        t -> {
                            t.setAccount(trade.getAccount());
                            t.setType(trade.getType());
                            t.setBuyQuantity(trade.getBuyQuantity());
                            t.setSellQuantity(trade.getSellQuantity());
                            t.setBuyPrice(trade.getBuyPrice());
                            t.setSellPrice(trade.getSellPrice());
                            t.setBenchmark(trade.getBenchmark());
                            tradeRepository.save(t);
                        },
                        () -> {
                            throw new RuntimeException("Trade not found");
                        });
        return trade;
    }

    public void delete(int id) {
        tradeRepository.deleteById(id);
    }
}
