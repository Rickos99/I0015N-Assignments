package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Scorecard extends PageObject {

    @FindBy(className = "player-header")
    private List<WebElement> players;

    @FindBy(css = "#scorecard > tfoot > tr > td:not(:first-child)")
    private List<WebElement> playerScores;

    public Scorecard(WebDriver driver) {
        super(driver);
    }

    public String[] getPlayerNames() {
        var playerNames = new String[players.size()];
        for (int i = 0; i < players.size(); i++) {
            playerNames[i] = players.get(i).getText();
        }
        return playerNames;
    }

    public void enterValue(int row, int column, String value){
        var tRow = row + 1;
        var tCol = column + 2;
        var selector = String.format("#scorecard > tbody > tr:nth-child(%d) > td:nth-child(%d) > input", tRow, tCol);
        driver.findElement(By.cssSelector(selector)).sendKeys(value);
    }

    public int[] getScores(){
        var scores = new int[players.size()];
        for (int i = 0; i < players.size(); i++) {
            scores[i] = getScore(i);
        }
        return scores;
    }

    public int getScore(int player){
        return Integer.parseInt(playerScores.get(player).getText());
    }
}
