package com.nnk.springboot.IT;

import com.nnk.springboot.IT.pages.*;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.repositories.UserRepository;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CurvePointIT {
    @LocalServerPort
    private Integer port;
    private WebDriver webDriver = null;
    private String baseUrl;

    @Autowired
    private CurvePointRepository curvePointRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeEach
    void setUp() {
        webDriver = new FirefoxDriver();
        baseUrl = "http://localhost:" + port;
        userRepository.save(new User("leoM", bCryptPasswordEncoder.encode("Welcome123"), "Léonard MINOT", "ADMIN"));
    }

    @AfterEach
    void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
        userRepository.deleteAll();
        curvePointRepository.deleteAll();
    }

    @Test
    void itShouldAddANewCurvePoint() {
        // Given
        webDriver.get(baseUrl);

        // When
        final LoginPage loginPage = new LoginPage(webDriver);
        HomePage homePage = loginPage.login("leoM", "Welcome123");

        CurvePointPage curvePointPage = homePage.navigateToCurvePoint();

        AddCurvePointPage addCurvePointPage = curvePointPage.addNewCurvePoint();
        CurvePointPage backCurvePointPage = addCurvePointPage.addNewCurvePoint("10", "11", "12");

        // Then
        assertThat(backCurvePointPage.isBidListPageDisplayed()).isTrue();
        CurvePoint actualCurvePoint = curvePointRepository.findAll().stream().findFirst().orElse(null);
        assertThat(actualCurvePoint).isNotNull();
        assertThat(actualCurvePoint.getCurveId()).isEqualTo(10);
        assertThat(actualCurvePoint.getTerm()).isEqualTo(11d);
        assertThat(actualCurvePoint.getValue()).isEqualTo(12d);
    }
}
