package com.nnk.springboot.controllers.restcontrollers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class RuleNameRestController {
    private final RuleService ruleService;

    @Autowired
    public RuleNameRestController(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    @GetMapping("ruleNames")
    public List<RuleName> getRuleNames() {
        return ruleService.getAll();
    }

    @GetMapping("ruleName/{id}")
    public RuleName getRuleNameById(@PathVariable("id") Integer id) {
        return ruleService.getById(id);
    }

    @PostMapping("ruleName")
    public void addRuleName(@RequestBody @Valid RuleName ruleName) {
        ruleService.add(ruleName);
    }

    @PutMapping("ruleName")
    public void updateRuleName(@RequestBody @Valid RuleName ruleName) {
        ruleService.update(ruleName, ruleName.getId());
    }

    @DeleteMapping("ruleName/{id}")
    public void deleteRuleName(@PathVariable("id") Integer id) {
        ruleService.delete(id);
    }

}
