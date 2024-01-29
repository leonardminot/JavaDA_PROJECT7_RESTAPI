package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
public class RuleNameController {
    private final RuleService ruleService;

    @Autowired
    public RuleNameController(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    @GetMapping("/ruleName/list")
    public String home(Model model, Principal principal)
    {
        model.addAttribute("ruleNames", ruleService.getAll());
        model.addAttribute("connectedUser", principal.getName());
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(Model model) {
        model.addAttribute("ruleName", new RuleName());
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@ModelAttribute @Valid RuleName ruleName, BindingResult result) {
        if (result.hasErrors()) {
            return "ruleName/add";
        } else {
            ruleService.add(ruleName);
            return "redirect:/ruleName/list";
        }
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        if (!model.containsAttribute("ruleName")) {
            model.addAttribute("ruleName", ruleService.getById(id));
        } else {
            RuleName existingRuleName = (RuleName) model.getAttribute("ruleName");
            assert existingRuleName != null;
            existingRuleName.setId(id);
        }
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id,@ModelAttribute("ruleName") @Valid RuleName ruleName,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "/ruleName/update";
        } else {
            ruleService.update(ruleName, id);
            return "redirect:/ruleName/list";
        }
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id) {
        ruleService.delete(id);
        return "redirect:/ruleName/list";
    }
}
