package com.hangman.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

// There are many ways to work with Selenium.
// This example uses "Selenide" to make the tests easy to write.
// The documentation for Selenide library is here:
// https://selenide.org/documentation.html
public class SimplestTest {

    @BeforeEach
    public void setUp() {
        Configuration.browserSize = "1280x800";
    }

    @Test
    public void playGameToVictory() {
        //This test automates the playing of a game of hangman from start to finish, winning.

        //pseudocode:
        // open the browser at the hangman game, forcing it to use the target word "mississippi"
        open("https://hangman-game-solution.netlify.app/?testWord=mississippi");

        // Verify initial state
        $("[data-testid='miss-count']").shouldHave(exactText("0"));
        $("[data-testid='loss-message']").shouldNotBe(visible);
        $("[data-testid='win-message']").shouldNotBe(visible);
        $("[data-testid='letter-board']").shouldHave(exactText("_ _ _ _ _ _ _ _ _ _ _"));

        // Click 'X' - this will be a miss
        $("[data-testid='letter-button-x']").click();

        // Verify miss-count increments
        $("[data-testid='miss-count']").shouldHave(exactText("1"));

        // Click 'I'
        $("[data-testid='letter-button-i']").click();

        // Verify letter board updates and 'I' is disabled
        $("[data-testid='letter-board']").shouldHave(exactText("_ i _ _ i _ _ i _ _ i"));
        $("[data-testid='letter-button-i']").shouldBe(disabled);

        // Click 'M' and 'S'
        $("[data-testid='letter-button-m']").click();
        $("[data-testid='letter-button-s']").click();

        // Verify game is not over
        $("[data-testid='win-message']").shouldNotBe(visible);

        // Click 'P'
        $("[data-testid='letter-button-p']").click();

        // Verify win message and no loss message
        $("[data-testid='win-message']").shouldBe(visible);
        $("[data-testid='loss-message']").shouldNotBe(visible);
        $("[data-testid='letter-board']").shouldHave(exactText("m i s s i s s i p p i"));
    }

    @Test
    public void playGameToLossYouWriteThisTest() {
        //Task for the reader: design and write a test which plays the hangman game and loses.
        //The test must be completely reliable -
        // i.e. it should not have ANY possibility of winning (not, say, 1 in 10,000 times)

        //open the browser automatically to this url.
        open("https://hangman-game-solution.netlify.app/?testWord=potato");

        //This test will fail - we don't really expect miss-count to be 99 at the start of a game!
        //Delete this line once you've designed your test and are ready to start writing it
        $("[data-testid='miss-count']").shouldHave(exactText("99"));

        //You have to write the rest here
    }
}