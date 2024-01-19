package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
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
}
