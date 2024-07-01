package com.hangman.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

// There are many ways to work with Selenium.
// This example uses "Selenide" to make the tests easy to write.
// The documentation for Selenide library is here:
// https://selenide.org/documentation.html
public class FancierTest {
    //I'm not really using this but it was in the sample code.
    //seems to be a way to extract out element selectors nicely.

    HangmanGameElements gamePage = new HangmanGameElements();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        Configuration.browserCapabilities = new ChromeOptions().addArguments("--remote-allow-origins=*");
        String pageUnderTest = "https://hangman-game-solution.netlify.app/?testWord=mississippi";
        open(pageUnderTest);
    }

    @Test
    public void playGameToVictory() {
        // Play a game of hangman from start to finish, winning.

        // Verify initial state

        // Here's three ways to do the same thing.
        // Which way do you prefer to read?
        $("[data-testid='miss-count']").shouldHave(text("0"));
        $(byAttribute("data-testid", "miss-count")).shouldHave(text("0"));
        gamePage.missCount.shouldHave(exactText(("0")));

        gamePage.winMessage.shouldNotBe(visible);
        gamePage.lossMessage.shouldNotBe(visible);
        gamePage.letterBoard.shouldHave(exactText("_ _ _ _ _ _ _ _ _ _ _"));

        //Start interacting with the game

        // Click 'X' - this will be a miss
        gamePage.letterButtonX.click();

        // Verify misses increment
        gamePage.missCount.shouldHave(exactText("1"));

        // Click 'I'
        gamePage.letterButtonI.click();

        // Verify letter board updates and 'I' is disabled

        gamePage.letterBoard.shouldHave(exactText("_ i _ _ i _ _ i _ _ i"));
        gamePage.letterButtonI.shouldBe(disabled);

        // Click 'M' and 'S'
        gamePage.letterButtonM.click();
        gamePage.letterButtonS.click();

        // Verify game is not over
        gamePage.winMessage.shouldNotBe(visible);

        // Click 'P'
        gamePage.letterButtonP.click();

        // Verify win message and no loss message
        gamePage.winMessage.shouldBe(visible);
        gamePage.lossMessage.shouldNotBe(visible);
        gamePage.letterBoard.shouldHave(exactText("m i s s i s s i p p i"));
    }
}