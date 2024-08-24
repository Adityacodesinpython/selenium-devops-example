import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ChromeDriverTest {

    private String testUrl;
    private WebDriver driver;

    @Before
    public void prepare() {
        // Set up ChromeDriver
        System.setProperty(
                "webdriver.chrome.driver",
                "C:/path/to/chromedriver.exe");  // Use the absolute path

        testUrl = "https://www.youtube.com/";

        // Initialize ChromeDriver
        driver = new ChromeDriver();

        // Maximize window
        driver.manage().window().maximize();

        // Navigate to test URL
        driver.get(testUrl);
    }

    @Test
    public void testTitle() throws IOException {
        // Find elements by attribute lang="READ_MORE_BTN"
        List<WebElement> elements = driver
                .findElements(By.cssSelector("[lang=\"READ_MORE_BTN\"]"));

        // Click the selected button
        elements.get(0).click();

        // Wait for the page title to change and assert it
        assertTrue("The page title should be changed as expected",
                (new WebDriverWait(driver, 5))
                        .until(new ExpectedCondition<Boolean>() {
                            public Boolean apply(WebDriver d) {
                                return d.getTitle().equals("我眼中软件工程人员该有的常识");
                            }
                        })
        );
    }

    @After
    public void teardown() throws IOException {
        // Check if driver is not null before quitting
        if (driver != null) {
            driver.quit();
        }
    }
}
