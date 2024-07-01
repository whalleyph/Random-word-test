package com.hangman.test;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;

//A place to declare some named elements for convenience.
// Not actually needed for selenium tests unless you WANT to use it.
public class HangmanGameElements
{
    //Find and store the page element which has an attribute "data-testid" with value "letter-board"
    public SelenideElement letterBoard = $(byAttribute("data-testid", "letter-board"));

    public SelenideElement missCount = $(byAttribute("data-testid", "miss-count"));

    public SelenideElement winMessage = $(byAttribute("data-testid", "win-message"));

    public SelenideElement lossMessage = $(byAttribute("data-testid", "loss-message"));

    //I haven't created a member variable for every letter button in the game, but you certainly could.
    public SelenideElement letterButtonM = $(byAttribute("data-testid", "letter-button-m"));
    public SelenideElement letterButtonI = $(byAttribute("data-testid", "letter-button-i"));
    public SelenideElement letterButtonP = $(byAttribute("data-testid", "letter-button-p"));
    public SelenideElement letterButtonS = $(byAttribute("data-testid", "letter-button-s"));
    public SelenideElement letterButtonX = $(byAttribute("data-testid", "letter-button-x"));
}

//A note about the optional "Selenide" library:
// There are many ways to work with Selenium.
// This example uses "Selenide" to make the selectors easy to write.
// The documentation for Selenide library is here:
// https://selenide.org/documentation.html
