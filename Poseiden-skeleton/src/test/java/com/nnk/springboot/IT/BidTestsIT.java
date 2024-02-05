package com.nnk.springboot.IT;

import com.nnk.springboot.IT.pages.AddBidListPage;
import com.nnk.springboot.IT.pages.BidListPage;
import com.nnk.springboot.IT.pages.HomePage;
import com.nnk.springboot.IT.pages.LoginPage;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
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
public class BidTestsIT {
    @LocalServerPort
    private Integer port;
    private WebDriver webDriver = null;
    private String baseUrl;

    @Autowired
    private BidListRepository bidListRepository;

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
    void itShouldAddANewBidList() {
        // Given
        webDriver.get(baseUrl);

        // When
        final LoginPage loginPage = new LoginPage(webDriver);
        HomePage homePage = loginPage.login("leoM", "Welcome123");

        BidListPage bidListPage = homePage.navigateToBidList();

        AddBidListPage addBidListPage = bidListPage.addNewBidList();
        BidListPage backBidListPage = addBidListPage.addNewBidList(
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
        assertThat(backBidListPage.isBidListPageDisplayed()).isTrue();
        BidList actualBidList = bidListRepository.findById(1).orElse(null);
        assertThat(actualBidList).isNotNull();
        assertThat(actualBidList.getAccount()).isEqualTo("account");
        assertThat(actualBidList.getType()).isEqualTo("type");
        assertThat(actualBidList.getBidQuantity()).isEqualTo(10d);
        assertThat(actualBidList.getAskQuantity()).isEqualTo(11d);
        assertThat(actualBidList.getBid()).isEqualTo(12d);
        assertThat(actualBidList.getAsk()).isEqualTo(13d);
        assertThat(actualBidList.getBenchmark()).isEqualTo("benchmark");
        assertThat(actualBidList.getCommentary()).isEqualTo("commentary");
        assertThat(actualBidList.getSecurity()).isEqualTo("security");
        assertThat(actualBidList.getStatus()).isEqualTo("status");
        assertThat(actualBidList.getTrader()).isEqualTo("trader");
        assertThat(actualBidList.getBook()).isEqualTo("book");
        assertThat(actualBidList.getCreationName()).isEqualTo("creationName");
        assertThat(actualBidList.getRevisionName()).isEqualTo("revisionName");
        assertThat(actualBidList.getDealName()).isEqualTo("dealName");
        assertThat(actualBidList.getDealType()).isEqualTo("dealType");
        assertThat(actualBidList.getSourceListId()).isEqualTo("sourceListId");
        assertThat(actualBidList.getSide()).isEqualTo("side");
    }
}
