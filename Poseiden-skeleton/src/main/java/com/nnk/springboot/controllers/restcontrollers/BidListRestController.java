package com.nnk.springboot.controllers.restcontrollers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class BidListRestController {
    private final BidService bidService;

    @Autowired
    public BidListRestController(BidService bidService) {
        this.bidService = bidService;
    }

    @GetMapping("bidLists")
    public List<BidList> getBidLists() {
        return bidService.getAll();
    }

    @GetMapping("bidList/{id}")
    public BidList getBidListById(@PathVariable("id") Integer id) {
        return bidService.getById(id);
    }

    @PostMapping("bidList")
    public void addBidList(@RequestBody @Valid BidList bidList) {
        bidService.add(bidList);
    }

    @PutMapping("bidList")
    public void updateBidList(@RequestBody @Valid BidList bidList) {
        bidService.update(bidList, bidList.getBidListId());
    }

    @DeleteMapping("bidList/{id}")
    public void deleteBidList(@PathVariable("id") Integer id) {
        bidService.delete(id);
    }
}
