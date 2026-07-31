package com.parabank.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransferFundsTest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        if (System.getenv("CI") != null) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        }
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("http://localhost:8080/parabank/index.htm");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("john", "demo");
    }

    @Test
    void transferShowsCompleteMessage() {
        driver.findElement(By.linkText("Transfer Funds")).click();

        TransferFundsPage transferFundsPage = new TransferFundsPage(driver);
        transferFundsPage.transfer("50");

        String pageSource = driver.getPageSource();
        assertTrue(pageSource.contains("Transfer Complete!"));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
