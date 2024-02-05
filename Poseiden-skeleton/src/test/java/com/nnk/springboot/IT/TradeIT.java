package com.nnk.springboot.IT;

import com.nnk.springboot.IT.pages.AddTradePage;
import com.nnk.springboot.IT.pages.HomePage;
import com.nnk.springboot.IT.pages.LoginPage;
import com.nnk.springboot.IT.pages.TradePage;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import io.github.bonigarcia.wdm.WebDriverManager;
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
public class TradeIT {
    @LocalServerPort
    private Integer port;
    private WebDriver webDriver = null;
    private String baseUrl;

    @Autowired
    private TradeRepository tradeRepository;

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
    void itShouldAddANewTrade() {
        // Given
        webDriver.get(baseUrl);

        // When
        final LoginPage loginPage = new LoginPage(webDriver);
        HomePage homePage = loginPage.login("leoM", "Welcome123");

        TradePage tradePage = homePage.navigateToTrade();

        AddTradePage addTradePage = tradePage.addNewTrade();
        TradePage backTradePage = addTradePage.addNewTrade(
                "account",
                "type",
                "10",
                "11",
                "12",
                "13",
                "benchmark",
                "commentary",
                "security",
                "status",
                "trader",
                "book",
                "creationName",
                "revisionName",
                "dealName",
                "dealType",
                "sourceListId",
                "side"
        );

        // Then
        assertThat(backTradePage.isTradePageDisplayed()).isTrue();
        Trade actualTrade = tradeRepository.findById(1).orElse(null);
        assertThat(actualTrade).isNotNull();
        assertThat(actualTrade.getAccount()).isEqualTo("account");
        assertThat(actualTrade.getType()).isEqualTo("type");
        assertThat(actualTrade.getBuyQuantity()).isEqualTo(10d);
        assertThat(actualTrade.getSellQuantity()).isEqualTo(11d);
        assertThat(actualTrade.getBuyPrice()).isEqualTo(12d);
        assertThat(actualTrade.getSellPrice()).isEqualTo(13d);
        assertThat(actualTrade.getBenchmark()).isEqualTo("benchmark");
        assertThat(actualTrade.getCommentary()).isEqualTo("commentary");
        assertThat(actualTrade.getSecurity()).isEqualTo("security");
        assertThat(actualTrade.getStatus()).isEqualTo("status");
        assertThat(actualTrade.getTrader()).isEqualTo("trader");
        assertThat(actualTrade.getBook()).isEqualTo("book");
        assertThat(actualTrade.getCreationName()).isEqualTo("creationName");
        assertThat(actualTrade.getRevisionName()).isEqualTo("revisionName");
        assertThat(actualTrade.getDealName()).isEqualTo("dealName");
        assertThat(actualTrade.getDealType()).isEqualTo("dealType");
        assertThat(actualTrade.getSourceListId()).isEqualTo("sourceListId");
        assertThat(actualTrade.getSide()).isEqualTo("side");

    }
}
