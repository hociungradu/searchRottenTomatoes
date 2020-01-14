package searchRottenTomatoes;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchGoogleTest {

    @Test
    public void lauchWebDriver() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com");

        String queryWords = "Rotten Tomatoes API";
        String urlToMatch = "https://developer.fandango.com/rotten_tomatoes";
        // search page
        WebDriverWait wait = new WebDriverWait(driver, 20);

        String cssOfInputField = "input[name='q']";     //locator of input field from www.google.com (get with inspector on web page)
        WebElement inputField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssOfInputField)));
        inputField.sendKeys(queryWords);        // insert string to be searched

        String cssOfSearchButton = "input[name='btnK']";		// locator of the search button
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssOfSearchButton)));
        searchButton.click();

        // search results page
        String matchingUrl="";

        List<WebElement> resultEntries = driver.findElements(By.cssSelector("div.r > a"));  //create a list with all found results
        System.out.println(resultEntries.size());       //just displayed the size of results list
        for(WebElement element : resultEntries){

            String href = element.getAttribute("href");
            String title = element.getText();

            if(href.contains(urlToMatch)){
                System.out.println("****FOUND MATCH*** on page ");
                matchingUrl = href;
                System.out.println(matchingUrl);
            }
        }

        driver.close();
        driver.quit();
    }

}