package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleService {
    private final RuleNameRepository ruleNameRepository;

    @Autowired
    public RuleService(RuleNameRepository ruleNameRepository) {
        this.ruleNameRepository = ruleNameRepository;
    }

    @Transactional
    public RuleName add(RuleName ruleName) {
        return ruleNameRepository.save(ruleName);
    }

    public List<RuleName> getAll() {
        return ruleNameRepository.findAll();
    }

    public RuleName getById(int id) {
        return ruleNameRepository.findById(id).orElse(null);
    }

    @Transactional
    public RuleName update(RuleName rule, int id) {
        ruleNameRepository.findById(id)
                .ifPresentOrElse(
                        r -> {
                            r.setName(rule.getName());
                            r.setDescription(rule.getDescription());
                            r.setJson(rule.getJson());
                            r.setTemplate(rule.getTemplate());
                            r.setSqlStr(rule.getSqlStr());
                            r.setSqlPart(rule.getSqlPart());
                            ruleNameRepository.save(r);
                        },
                        () -> {
                            throw new RuntimeException("Rule Name not found");
                        }
                );
        return rule;
    }

    @Transactional
    public void delete(int id) {
        ruleNameRepository.deleteById(id);
    }
}
