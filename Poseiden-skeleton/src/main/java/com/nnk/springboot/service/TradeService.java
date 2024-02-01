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
                dateProvider.getNow(),
                trade.getCommentary(),
                trade.getSecurity(),
                trade.getStatus(),
                trade.getTrader(),
                trade.getBook(),
                trade.getCreationName(),
                dateProvider.getNow(),
                trade.getRevisionName(),
                null,
                trade.getDealName(),
                trade.getDealType(),
                trade.getSourceListId(),
                trade.getSide()
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
                            t.setTradeDate(dateProvider.getNow());
                            t.setCommentary(trade.getCommentary());
                            t.setSecurity(trade.getSecurity());
                            t.setStatus(trade.getStatus());
                            t.setTrader(trade.getTrader());
                            t.setBook(trade.getBook());
                            t.setCreationName(trade.getCreationName());
                            t.setCreationDate(t.getCreationDate());
                            t.setRevisionName(trade.getRevisionName());
                            t.setRevisionDate(dateProvider.getNow());
                            t.setDealName(trade.getDealName());
                            t.setDealType(trade.getDealType());
                            t.setSourceListId(trade.getSourceListId());
                            t.setSide(trade.getSide());
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
