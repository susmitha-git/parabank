package com.parabank.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class OpenAccountPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By accountTypeDropdown = By.id("type");
    private By fromAccountDropdown = By.id("fromAccountId");
    private By openAccountButton = By.xpath("//input[@value='Open New Account']");
    private By resultPanel = By.id("openAccountResult");

    public OpenAccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openAccount(String accountTypeValue) {
        Select typeDropdown = new Select(driver.findElement(accountTypeDropdown));
        typeDropdown.selectByValue(accountTypeValue);

        Select fromDropdown = new Select(driver.findElement(fromAccountDropdown));
        fromDropdown.selectByIndex(0);

        driver.findElement(openAccountButton).click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(resultPanel, "Account Opened!"));
    }
}
