package es.udc.paproject.e2etests;

import static java.time.Duration.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.Select;


import java.time.Duration;
class AppTest {

    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:5174");
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }
    private void login(String username, String password) {

        WebElement loginLink = driver.findElement(By.id("loginLink"));
        loginLink.click();

        WebElement usernameInput = driver.findElement(By.id("userName"));
        usernameInput.sendKeys(username);

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys(password);

        WebElement loginButton = driver.findElement(By.id("loginButton"));
        loginButton.click();

        driver.manage().timeouts().implicitlyWait(ofSeconds(1));

    }

    private void seeSportEvent(){
        login("testparticipant1", "pa2324");

        WebElement typeSelector = driver.findElement(By.id("typeId"));
        typeSelector.sendKeys("Running");


        WebElement provinceSelector = driver.findElement(By.id("provinceId"));
        provinceSelector.sendKeys("A Coruña");

        WebElement startDate = driver.findElement(By.id("startDate"));
        startDate.clear();
        startDate.sendKeys("16/06/2028");

        WebElement endDate = driver.findElement(By.id("endDate"));
        endDate.clear();
        endDate.sendKeys("20/06/2031");

        WebElement submitButton = driver.findElement(By.id("findSubmit"));
        submitButton.click();

        driver.manage().timeouts().implicitlyWait(ofSeconds(1));

        WebElement link = driver.findElement(By.id("event-5"));
        link.click();

        driver.manage().timeouts().implicitlyWait(ofSeconds(1));

    }


    @Test
    void testLogin() {
        login("testparticipant1", "pa2324");
        WebElement loginDis = driver.findElement(By.id("loginDisplay"));
        assertTrue(loginDis.getText().contains("testparticipant1"));
    }

    @Test
    void testSeeSportEvent(){
        seeSportEvent();

        WebElement eventName = driver.findElement(By.id("eventName"));
        assertTrue(eventName.isDisplayed());
        assertTrue(eventName.getText().contains("Sporting Event Test"));
        WebElement type = driver.findElement(By.id("type"));
        assertTrue(type.isDisplayed());
        WebElement description = driver.findElement(By.id("description"));
        assertTrue(description.isDisplayed());
        WebElement valoration = driver.findElement(By.id("valoration"));
        assertTrue(valoration.isDisplayed());
        WebElement location = driver.findElement(By.id("location"));
        assertTrue(location.isDisplayed());
        WebElement province = driver.findElement(By.id("province"));
        assertTrue(province.isDisplayed());
        WebElement date = driver.findElement(By.id("date"));
        assertTrue(date.isDisplayed());
        WebElement price = driver.findElement(By.id("price"));
        assertTrue(price.isDisplayed());
        WebElement spots = driver.findElement(By.id("spots"));
        assertTrue(spots.isDisplayed());
        WebElement participants = driver.findElement(By.id("participants"));
        assertTrue(participants.isDisplayed());
        WebElement registrationForm = driver.findElement(By.id("registrationForm"));
        assertTrue(registrationForm.isDisplayed());
    }

    @Test
    void testInscribeSportEvent(){
        seeSportEvent();

        WebElement creditcard = driver.findElement(By.id("creditCard"));
        creditcard.sendKeys("1111222233334444");

        WebElement inscribeButton = driver.findElement(By.id("submitButton"));
        inscribeButton.click();

        driver.manage().timeouts().implicitlyWait(ofSeconds(1));

        String eventName = driver.findElement(By.id("eventName")).getText();
        String inscriptionId = driver.findElement(By.id("inscriptionId")).getText();
        String dorsal = driver.findElement(By.id("dorsal")).getText();

        WebElement inscriptionsLink = driver.findElement(By.id("seeInscriptions"));
        inscriptionsLink.click();

        driver.manage().timeouts().implicitlyWait(ofSeconds(1));

        WebElement eventNameInscription = driver.findElement(By.id("event-5"));
        assertEquals(eventName, eventNameInscription.getText());

        WebElement inscriptionIdHistory = driver.findElement(By.id("id-2"));
        assertEquals(inscriptionId, inscriptionIdHistory.getText());

        WebElement dorsalHistory = driver.findElement(By.id("dorsal-2"));
        assertEquals(dorsal, dorsalHistory.getText());

    }
}