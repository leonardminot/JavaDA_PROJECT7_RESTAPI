package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuleService {
    private final RuleNameRepository ruleNameRepository;

    @Autowired
    public RuleService(RuleNameRepository ruleNameRepository) {
        this.ruleNameRepository = ruleNameRepository;
    }

    public RuleName add(RuleName ruleName) {
        return ruleNameRepository.save(ruleName);
    }
}
