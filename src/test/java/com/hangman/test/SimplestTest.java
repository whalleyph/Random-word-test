package com.hangman.test;

import com.codeborne.selenide.Condition;
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
        open("https://random-picker-paul.netlify.app/");

        // Verify initial state
        $("[id='root'] > h1").shouldHave(exactText("Random Picker"));
        $("[class='text--area'] > textarea").shouldHave(exactText("player1 player2 player3 player4"));
        $("[class='button'] > button").shouldHave(exactText("Random"));
        $("[class='picked--word'] > p").shouldHave(exactText("Random Word"));

        // Click to randomise word
        $("[class='button'] > button").click();

        // Verify random word
        Condition randomPlayerCondition = Condition.or("player1 or player2 or player3 or player4",
                exactText("player1"),
                exactText("player2"),
                exactText("player3"),
                exactText("player4"));
        $("[class='picked--word'] > p").shouldHave(randomPlayerCondition);

        $("textarea").clear();
        $("textarea").setValue("This is a new message");

        // Check new message is in box
        $("textarea").shouldHave(value("This is a new message"));

        // Random word should still be the old word
        $("[class='picked--word'] > p").shouldHave(randomPlayerCondition);

        // Click to randomise word
        $("[class='button'] > button").click();

        Condition newMessageCondition = Condition.or("new message",
                exactText("This"),
                exactText("is"),
                exactText("a"),
                exactText("new"),
                exactText("message"));
        $("[class='picked--word'] > p").shouldHave(newMessageCondition);
    }
}