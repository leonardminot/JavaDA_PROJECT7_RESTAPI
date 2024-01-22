package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
@Tag("UnitTest")
public class RuleServiceTest {
    @Nested
    @DisplayName("Rule: It should add a Rule")
    class AddRuleName {
        private RuleService ruleService;
        @Mock
        private RuleNameRepository ruleNameRepository;
        @Captor
        private ArgumentCaptor<RuleName> ruleNameArgumentCaptor;

        @BeforeEach
        void setUp() {
            ruleService = new RuleService(ruleNameRepository);
        }

        @Test
        void itShouldAddANewRuleIntoRepository() {
            // Given
            RuleName ruleNameToAdd = new RuleName("name", "description", "json", "template", "SELECT *", "sqlPart");

            // When
            RuleName savecRuleName = ruleService.add(ruleNameToAdd);

            // Then
            then(ruleNameRepository).should().save(ruleNameArgumentCaptor.capture());
            assertThat(ruleNameArgumentCaptor.getValue().getName()).isEqualTo("name");
            assertThat(ruleNameArgumentCaptor.getValue().getDescription()).isEqualTo("description");
            assertThat(ruleNameArgumentCaptor.getValue().getJson()).isEqualTo("json");
            assertThat(ruleNameArgumentCaptor.getValue().getTemplate()).isEqualTo("template");
            assertThat(ruleNameArgumentCaptor.getValue().getSqlStr()).isEqualTo("SELECT *");
            assertThat(ruleNameArgumentCaptor.getValue().getSqlPart()).isEqualTo("sqlPart");
        }
    }

    @Nested
    @DisplayName("Rule: it should return all the Rules")
    class DisplayCurvePoints {
        private RuleService ruleService;
        @Mock
        private RuleNameRepository ruleNameRepository;
        @Captor
        private ArgumentCaptor<RuleName> ruleNameArgumentCaptor;

        @BeforeEach
        void setUp() {
            ruleService = new RuleService(ruleNameRepository);
        }

        @Test
        void itShouldReturnAllTheRules() {
            // Given
            List<RuleName> ruleNames = List.of(
                    new RuleName("name", "description", "json", "template", "SELECT *", "sqlPart"),
                    new RuleName("name2", "description2", "json2", "template2", "SELECT *2", "sqlPart2")
            );
            given(ruleNameRepository.findAll()).willReturn(ruleNames);

            // When
            List<RuleName> actualRuleNames = ruleService.getAll();

            // Then
            assertThat(actualRuleNames).hasSameElementsAs(ruleNames);
        }

        @Test
        void itShouldReturnRuleNameByItsId() {
            // Given
            given(ruleNameRepository.findById(2)).willReturn(Optional.of(new RuleName("name2", "description2", "json2", "template2", "SELECT *2", "sqlPart2")));
            // When
            RuleName actualRuleName = ruleService.getById(2);
            // Then
            assertThat(actualRuleName.getName()).isEqualTo("name2");
            assertThat(actualRuleName.getDescription()).isEqualTo("description2");
            assertThat(actualRuleName.getJson()).isEqualTo("json2");
            assertThat(actualRuleName.getTemplate()).isEqualTo("template2");
            assertThat(actualRuleName.getSqlStr()).isEqualTo("SELECT *2");
            assertThat(actualRuleName.getSqlPart()).isEqualTo("sqlPart2");
        }
    }

    @Nested
    @DisplayName("Rule: it should update or Delete Rule")
    class UpdateOrDeleteRule {
        private RuleService ruleService;
        @Mock
        private RuleNameRepository ruleNameRepository;
        @Captor
        private ArgumentCaptor<RuleName> ruleNameArgumentCaptor;

        @BeforeEach
        void setUp() {
            ruleService = new RuleService(ruleNameRepository);
        }

        @Test
        void itShouldUpdateARule() {
            // Given
            RuleName currentRule = new RuleName("name", "description", "json", "template", "SELECT *", "sqlPart");
            RuleName expectedRule = new RuleName("name2", "description2", "json", "template", "SELECT *", "sqlPart");
            given(ruleNameRepository.findById(1)).willReturn(Optional.of(currentRule));
            // When
            RuleName actualRuleName = ruleService.update(expectedRule, 1);
            // Then
            then(ruleNameRepository).should().save(ruleNameArgumentCaptor.capture());
            assertThat(actualRuleName.getId()).isEqualTo(currentRule.getId());
            assertThat(actualRuleName.getName()).isEqualTo("name2");
            assertThat(actualRuleName.getDescription()).isEqualTo("description2");
            assertThat(actualRuleName.getJson()).isEqualTo("json");
            assertThat(actualRuleName.getTemplate()).isEqualTo("template");
            assertThat(actualRuleName.getSqlStr()).isEqualTo("SELECT *");
            assertThat(actualRuleName.getSqlPart()).isEqualTo("sqlPart");
        }

        @Test
        void itShouldDeleteARule() {
            // Given
            // When
            ruleService.delete(1);
            // Then
            then(ruleNameRepository).should().deleteById(1);
        }
    }

}
