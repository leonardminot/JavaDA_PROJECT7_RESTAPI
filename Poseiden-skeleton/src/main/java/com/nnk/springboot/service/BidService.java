package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
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


    public BidList add(BidList bidList) {
        return bidListRepository.save(new BidList(
                bidList.getAccount(),
                bidList.getType(),
                bidList.getBidQuantity(),
                bidList.getAskQuantity(),
                bidList.getBid(),
                bidList.getAsk(),
                bidList.getBenchmark(),
                dateProvider.getNow()
        ));
    }

    public List<BidList> getAll() {
        return bidListRepository.findAll();
    }

    public BidList getById(int id) {
        return bidListRepository.findById(id).orElse(null);
    }

    public BidList update(BidList bidList, Integer id) {
        bidListRepository.findById(id)
                .ifPresentOrElse(
                        bl -> {
                            bl.setAccount(bidList.getAccount());
                            bl.setType(bidList.getType());
                            bl.setBidQuantity(bidList.getBidQuantity());
                            bl.setAskQuantity(bidList.getAskQuantity());
                            bl.setBid(bidList.getBid());
                            bl.setAsk(bidList.getAsk());
                            bl.setBenchmark(bidList.getBenchmark());
                            bl.setBidListDate(dateProvider.getNow());
                            bidListRepository.save(bl);
                        },
                        () -> {
                            throw new RuntimeException("BidList not found");
                        }
                );
        return bidList;
    }

    public void delete(int id) {
        bidListRepository.deleteById(id);
    }
}
