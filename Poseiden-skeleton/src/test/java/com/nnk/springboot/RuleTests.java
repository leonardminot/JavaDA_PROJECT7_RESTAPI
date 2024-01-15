package com.nnk.springboot;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RuleTests {

	@Autowired
	private RuleNameRepository ruleNameRepository;

	@Test
	public void ruleTest() {
		RuleName rule = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");

		// Save
		rule = ruleNameRepository.save(rule);
		assertThat(rule.getId()).isNotNull();
		assertThat(rule.getName()).isEqualTo("Rule Name");

		// Update
		rule.setName("Rule Name Update");
		rule = ruleNameRepository.save(rule);
		assertThat(rule.getName()).isEqualTo("Rule Name Update");

		// Find
		List<RuleName> listResult = ruleNameRepository.findAll();
		assertThat(listResult.size()).isGreaterThan(0);

		// Delete
		Integer id = rule.getId();
		ruleNameRepository.delete(rule);
		Optional<RuleName> ruleList = ruleNameRepository.findById(id);
		assertThat(ruleList).isEmpty();
	}
}
