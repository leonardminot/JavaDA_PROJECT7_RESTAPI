package com.nnk.springboot.IT;

import com.nnk.springboot.IT.pages.AddRulePage;
import com.nnk.springboot.IT.pages.HomePage;
import com.nnk.springboot.IT.pages.LoginPage;
import com.nnk.springboot.IT.pages.RulePage;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RuleIT {
    @LocalServerPort
    private Integer port;
    private WebDriver webDriver = null;
    private String baseUrl;

    @Autowired
    private RuleNameRepository ruleNameRepository;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeEach
    void setUp() {
        webDriver = new FirefoxDriver();
        baseUrl = "http://localhost:" + port;
    }

    @AfterEach
    void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Test
    void itShouldAddANewRule() {
        // Given
        webDriver.get(baseUrl);

        // When
        final LoginPage loginPage = new LoginPage(webDriver);
        HomePage homePage = loginPage.login("leoM", "Welcome123");

        RulePage rulePage = homePage.navigateToRulePage();

        AddRulePage addRulePage = rulePage.addNewRule();

        RulePage backRulePage = addRulePage.addNewRule(
                "name",
                "description",
                "json",
                "template",
                "sql",
                "sqlPart"
        );

        // Then
        assertThat(backRulePage.isRulePageDisplayed()).isTrue();
        RuleName actualRule = ruleNameRepository.findAll().stream().findFirst().orElse(null);
        assertThat(actualRule).isNotNull();
        assertThat(actualRule.getName()).isEqualTo("name");
        assertThat(actualRule.getDescription()).isEqualTo("description");
        assertThat(actualRule.getJson()).isEqualTo("json");
        assertThat(actualRule.getTemplate()).isEqualTo("template");
        assertThat(actualRule.getSqlStr()).isEqualTo("sql");
        assertThat(actualRule.getSqlPart()).isEqualTo("sqlPart");
    }
}
