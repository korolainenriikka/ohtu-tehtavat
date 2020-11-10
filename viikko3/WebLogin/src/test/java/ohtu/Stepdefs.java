package ohtu;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.Random;

public class Stepdefs {
    //WebDriver driver = new ChromeDriver();
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";
    
    @Given("login is selected")
    public void loginIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click();   
    }    
    
    @When("correct username {string} and password {string} are given")
    public void correctUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    
    
    @Then("user is logged in")
    public void userIsLoggedIn() {
        pageHasContent("Ohtu Application main page");
    }    
 
    @When("correct username {string} and incorrect password {string} are given")
    public void correctUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }

    @When("nonexistent username {string} and password {string} are given")
    public void nonExistentUsernameGiven(String username, String password) {
        logInWith(username, password);
    }

    @Then("user is not logged in and error message is given")
    public void userIsNotLoggedInAndErrorMessageIsGiven() {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }    
    
    @When("username {string} and password {string} are given")
    public void usernameAndPasswordAreGiven(String username, String password) throws Throwable {
        logInWith(username, password);
    }   
    
    @Then("system will respond {string}")
    public void systemWillRespond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }

    @Given("command new user is selected")
    public void newUserIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }

    @When("a valid username {string} and password {string} and matching password confirmation are entered")
    public void validUsernamePasswordAndPasswordConfirmationEntered(String username, String password){
        Random r = new Random();
        createUserWith(username, password, password);
    }

    @When("a too short username {string} and password {string} and matching password confirmation are entered")
    public void tooShortUsernamePasswordAndPasswordConfirmationEntered(String username, String password){
        createUserWith(username, password, password);
    }

    @When("a valid username {string} and a too short password {string} and matching password confirmation are entered")
    public void validUsernamePasswordAndTooShortPasswordConfirmationEntered(String username, String password){
        createUserWith(username, password, password);
    }

    @When("a valid usename {string} and password {string} and password confirmation {string} are entered")
    public void validUsernamePasswordAndNonValidPasswordConfirmationEntered(String username, String password, String passwordConfirmation){
        createUserWith(username, password, passwordConfirmation);
    }

    @When("a valid username {string} and a password without numbers {string} and matching password confirmation are entered")
    public void validUsernameAndPasswordWithoutNumbersEntered(String username, String password) {
        createUserWith(username, password, password);
    }

    @Then("a new user is created")
    public void aNewUserIsCreated() throws Throwable {
        assertTrue(driver.getPageSource().contains("continue to application mainpage"));
    }

    @Then("user is not created and error {string} is reported")
    public void UserNotCreatedAndErrorReported(String errorMessage) throws Throwable {
        assertTrue(driver.getPageSource().contains(errorMessage));
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Given("user with username {string} and password {string} is successfully created")
    public void userWithUsernameAndPasswordSuccessfullyCreated(String username, String password){
        newUserIsSelected();
        createUserWith(username, password, password);
    }

    @Given("user with username {string} and password {string} is tried to be created")
    public void userWithUsernameAndPasswordIsTriedToBeCreated(String username, String password){
        newUserIsSelected();
        createUserWith(username, password, password);
    }
        
    /* helper methods */
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
        
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    }

    private void createUserWith(String username, String password, String passwordConfirmation){
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(passwordConfirmation);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }
}
