import Pages.Scorecard;
import Pages.Menu;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestPlan {
    private static WebDriver driver;

    @BeforeSuite
    public static void prepare() {
        System.setProperty("webdriver.chrome.driver", Utils.CHROME_DRIVER_LOCATION);
        driver = new ChromeDriver();
    }

    @BeforeClass
    public static void navigateToPage() {
        driver.get(Utils.BASE_URL);
    }

    @AfterSuite
    public static void cleanUp() {
        driver.manage().deleteAllCookies();
        driver.close();
    }

    @Test(groups = {"Prepare"}, priority = 1, testName = "Create a new game")
    public static void createNewGame() {
        Menu menu = new Menu(driver);
        Scorecard scorecard = new Scorecard(driver);
        String[] expected = new String[]{"John", "Jane", "Janie"};

        menu.addPlayers(expected);
        menu.pressNewGameButton();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(200));

        var actual = scorecard.getPlayerNames();
        Assert.assertEquals(actual, expected);
    }

    @Test(dependsOnGroups = {"Prepare"}, groups = {"Score"}, priority = 2, testName = "Enter score and calculate")
    public static void enterScore() {
        Scorecard scorecard = new Scorecard(driver);
        var expected = new int[]{24, 20, 9};

        // Round 1
        scorecard.enterValue(0, 0, "-");
        scorecard.enterValue(0, 1, "20");
        scorecard.enterValue(0, 2, "-5");
        // Round 2
        scorecard.enterValue(1, 0, "15");
        scorecard.enterValue(1, 1, "-");
        scorecard.enterValue(1, 2, "5");
        scorecard.enterValue(1, 6, "9");

        var actual = scorecard.getScores();
        Assert.assertEquals(actual, expected);
    }

    @Test(dependsOnGroups = {"Prepare"}, groups = {"Score"}, priority = 3, testName = "Save and load score from LocalStorage")
    public static void localStorageScore(){
        Scorecard scorecard = new Scorecard(driver);
        var expected = new int[]{24, 20, 9};

        var beforeRefresh = scorecard.getScores();
        driver.navigate().refresh();
        var afterRefresh = scorecard.getScores();

        Assert.assertEquals(beforeRefresh, expected);
        Assert.assertEquals(afterRefresh, expected);
    }
}
