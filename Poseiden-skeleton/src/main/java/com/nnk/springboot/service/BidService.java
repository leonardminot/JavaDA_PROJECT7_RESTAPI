package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidService {
    private final BidListRepository bidListRepository;
    private final DateProvider dateProvider;

    @Autowired
    public BidService(BidListRepository bidListRepository, DateProvider dateProvider) {
        this.bidListRepository = bidListRepository;
        this.dateProvider = dateProvider;
    }


    @Transactional
    public BidList add(BidList bidList) {
        return bidListRepository.save(new BidList(
                bidList.getAccount(),
                bidList.getType(),
                bidList.getBidQuantity(),
                bidList.getAskQuantity(),
                bidList.getBid(),
                bidList.getAsk(),
                bidList.getBenchmark(),
                dateProvider.getNow(),
                bidList.getCommentary(),
                bidList.getSecurity(),
                bidList.getStatus(),
                bidList.getTrader(),
                bidList.getBook(),
                bidList.getCreationName(),
                dateProvider.getNow(),
                bidList.getRevisionName(),
                null,
                bidList.getDealName(),
                bidList.getDealType(),
                bidList.getSourceListId(),
                bidList.getSide()
        ));
    }

    public List<BidList> getAll() {
        return bidListRepository.findAll();
    }

    public BidList getById(int id) {
        return bidListRepository.findById(id).orElse(null);
    }

    @Transactional
    public BidList update(BidList bidList, Integer id) {
        bidListRepository.findById(id)
                .ifPresentOrElse(
                        bl -> {
                            System.out.println(bl.getCreationDate());
                            bl.setAccount(bidList.getAccount());
                            bl.setType(bidList.getType());
                            bl.setBidQuantity(bidList.getBidQuantity());
                            bl.setAskQuantity(bidList.getAskQuantity());
                            bl.setBid(bidList.getBid());
                            bl.setAsk(bidList.getAsk());
                            bl.setBenchmark(bidList.getBenchmark());
                            bl.setBidListDate(dateProvider.getNow());
                            bl.setCommentary(bidList.getCommentary());
                            bl.setSecurity(bidList.getSecurity());
                            bl.setStatus(bidList.getStatus());
                            bl.setTrader(bidList.getTrader());
                            bl.setBook(bidList.getBook());
                            bl.setCreationName(bidList.getCreationName());
                            bl.setCreationDate(bl.getCreationDate());
                            bl.setRevisionName(bidList.getRevisionName());
                            bl.setRevisionDate(dateProvider.getNow());
                            bl.setDealName(bidList.getDealName());
                            bl.setDealType(bidList.getDealType());
                            bl.setSourceListId(bidList.getSourceListId());
                            bl.setSide(bidList.getSide());
                            bidListRepository.save(bl);
                        },
                        () -> {
                            throw new RuntimeException("BidList not found");
                        }
                );
        return bidList;
    }

    @Transactional
    public void delete(int id) {
        bidListRepository.deleteById(id);
    }
}
