package com.nnk.springboot.IT;

import com.nnk.springboot.IT.pages.AddRatingPage;
import com.nnk.springboot.IT.pages.HomePage;
import com.nnk.springboot.IT.pages.LoginPage;
import com.nnk.springboot.IT.pages.RatingPage;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
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
public class RatingIT {
    @LocalServerPort
    private Integer port;
    private WebDriver webDriver = null;
    private String baseUrl;

    @Autowired
    private RatingRepository ratingRepository;

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
    void itShouldAddANewRating() {
        // Given
        webDriver.get(baseUrl);

        // When
        final LoginPage loginPage = new LoginPage(webDriver);
        HomePage homePage = loginPage.login("leoM", "Welcome123");

        RatingPage ratingPage = homePage.navigateToRatingPage();

        AddRatingPage addRatingPage = ratingPage.addNewRating();
        RatingPage backRatingPage = addRatingPage.addNewRating(
                "moodys",
                "sandP",
                "fitch",
                "10"
        );

        // Then
        assertThat(backRatingPage.isRatingPageDisplayed()).isTrue();
        Rating actualRating = ratingRepository.findById(1).orElse(null);
        assertThat(actualRating).isNotNull();
        assertThat(actualRating.getMoodysRating()).isEqualTo("moodys");
        assertThat(actualRating.getSandPRating()).isEqualTo("sandP");
        assertThat(actualRating.getFitchRating()).isEqualTo("fitch");
        assertThat(actualRating.getOrderNumber()).isEqualTo(10);

    }
}
