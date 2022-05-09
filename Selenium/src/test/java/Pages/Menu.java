package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Menu extends PageObject {

    @FindBy(id = "btnAddPlayerToList")
    private WebElement btnAddPlayer;

    @FindBy(id = "btnStartNewGame")
    private WebElement btnStartNewGame;

    @FindBy(css = "#navigation-handle > span")
    private WebElement btnExpandMenu;

    @FindBy(id = "btn-close-nav")
    private WebElement btnCloseMenu;

    public Menu(WebDriver driver) {
        super(driver);
    }

    public void addPlayers(String[] players) {
        for (var player: players) {
            this.btnAddPlayer.click();
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(100));
            setLastPlayerName(player);
        }
    }

    public void pressNewGameButton() {
        this.btnStartNewGame.click();
    }

    public String[] getPlayerNames(){
        var selector = "#playernames-inputs > div > input";
        var playerInputs = driver.findElements(By.cssSelector(selector));
        var playerNames = new String[playerInputs.size()];
        for (int i = 0; i < playerInputs.size(); i++) {
            playerNames[i] = playerInputs.get(i).getAttribute("value");
        }
        return playerNames;
    }

    private void setLastPlayerName(String name) {
        var selector = "#playernames-inputs > div:last-of-type > input[type=text]";
        var playerInput = driver.findElement(By.cssSelector(selector));
        playerInput.sendKeys(name);
    }
}
