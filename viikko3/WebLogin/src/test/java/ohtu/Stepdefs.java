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

    @When("incorrect username {string} and password {string} are given")
    public void incorrectUsernameAndpasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }

    @Given("command new user is selected")
    public void commandNewUserIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }

    @When("a valid username {string} and password {string} and matching password confirmation are entered")
    public void validUsernameAndPasswordAndConfirmation(String username, String password) {
        signupWith(username, password, password);
    } 

    @Then("a new user is created")
    public void newUserIsCreated() {
        pageHasContent("Welcome to Ohtu Application!");
    }

    @When("an invalid username {string} and password {string} and matching password confirmation are entered")
    public void invalidUsernameAndPasswordAndConfirmation(String username, String password) {
        signupWith(username, password, password);
    } 

    @When("a valid username {string} but invalid password {string} and matching password confirmation are entered")
    public void validUsernameButInvalidPasswordAndConfirmation(String username, String password) {
        signupWith(username, password, password);
    } 

    @When("a valid username {string} and valid password {string} but invalid confirmation {string} are entered")
    public void validUsernameAndvalidPasswordButInvalidConfirmation(String username, String password, String confirmation) {
        signupWith(username, password, confirmation);
    } 

    @Then("user is not created and error {string} is reported")
    public void newUserIsNotCreated(String val) {
        pageHasContent(val);
    }

    @Given("user with username {string} with password {string} is successfully created")
    public void userWithNameAndPasswordCreated(String username, String password) throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
        signupWith(username, password, password);
        logoutFromSignup();
    }

    @Given("user with username {string} and password {string} is tried to be created")
    public void userWithNameAndPasswordIsTriedToBeCreated(String username, String password) throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
        signupWith(username, password, password);
    }
    
    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */

    private void logoutFromSignup() {
        assertTrue(driver.getPageSource().contains("Welcome to Ohtu Application!"));
        WebElement el = driver.findElement(By.linkText("continue to application mainpage"));
        el.click();
        el = driver.findElement(By.linkText("logout"));
        el.click();
    }
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }

    private void signupWith(String username, String password, String confirmation) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement el = driver.findElement(By.name("username"));
        el.sendKeys(username);
        el = driver.findElement(By.name("password"));
        el.sendKeys(password);
        el = driver.findElement(By.name("passwordConfirmation"));
        el.sendKeys(confirmation);
        el = driver.findElement(By.name("signup"));
        el.submit();
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
}
