package com.example.demo

import com.codeborne.selenide.Condition.attribute
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selectors.*
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.open
import com.codeborne.selenide.logevents.SelenideLogger
import io.qameta.allure.selenide.AllureSelenide
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.*

class MainPageTest {
    private val mainPage = MainPage()

    @BeforeEach
    fun setUpAll() {
        Configuration.browserSize = "1280x800"
        SelenideLogger.addListener("allure", AllureSelenide())
    }

    @BeforeEach
    fun setUp() {
        open("https://www.jetbrains.com/")
    }

    @Test
    fun search() {
        mainPage.searchButton.click()

        element("#header-search").sendKeys("Selenium")
        element(byXpath("//button[@type='submit' and text()='Search']")).click()

        element(".js-search-input").shouldHave(attribute("value", "Selenium"))
    }

    @Test
    fun toolsMenu() {
        mainPage.toolsMenu.hover()

        element(".menu-main__popup-wrapper").shouldBe(visible)
    }

    @Test
    fun navigationToAllTools() {
        mainPage.seeAllToolsButton.click()

        element(".products-list").shouldBe(visible)

        assertEquals("All Developer Tools and Products by JetBrains", Selenide.title())
    }
}
