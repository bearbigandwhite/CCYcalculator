package com.jcg.StepDefinitions;

import com.jcg.PageObjectModel.DriverInitializer;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.text.DecimalFormat;

import static org.assertj.core.api.Assertions.assertThat;

public class TestingSteps {

    WebDriver webDriver = null;

    @Given("^I open firefox browser$")
    public void iOpenFirefoxBrowser() throws Throwable {
        webDriver = DriverInitializer.getDriver("firefox");
    }

    @When("^I navigate to Luminor webpage$")
    public void iNavigateToLuminorWebPage() throws Throwable {
        webDriver.get(DriverInitializer.getProperty("web.url"));
    }

    @When("^I provide CCY as \"([^\"]*)\" and amount as \"([^\"]*)\"$")
    public void iProvideCCYSellAsAndAmountAs(String currency_sell, String amount) throws Throwable {
        Select drpCurrencySellSelect = new Select(webDriver.findElement(By.id("currency-sell-select")));
        drpCurrencySellSelect.selectByVisibleText(currency_sell);
        WebElement inputAmount = webDriver.findElement(By.xpath("//input[@id=\"currency-sell-textfield\"]"));
        inputAmount.click();
        inputAmount.sendKeys(Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE);
        inputAmount.sendKeys(amount);
    }

    @When("^I provide EUR as \"([^\"]*)\" and amount as \"([^\"]*)\"$")
    public void iProvideEURasSellAndAmountAs(String currency_sell, String amount) throws Throwable {
        Select drpCurrencySellSelect = new Select(webDriver.findElement(By.id("currency-sell-select")));
        drpCurrencySellSelect.selectByVisibleText(currency_sell);
        WebElement inputAmount = webDriver.findElement(By.xpath("//input[@id=\"currency-sell-textfield\"]"));
        inputAmount.click();
        inputAmount.sendKeys(Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE);
        inputAmount.sendKeys(amount);
    }

    @When("^I provide \"([^\"]*)\" as buying CCY$")
    public void iProvideCCYasBuy(String currency_buy) throws Throwable {
        Select drpCurrencySellSelect = new Select(webDriver.findElement(By.id("currency-buy-select")));
        drpCurrencySellSelect.selectByVisibleText(currency_buy);
    }


    @Then("^sell amount should be calculated correctly using \"([^\"]*)\" and \"([^\"]*)\"$")
    public void amountSellingIsCalculatedCorrectly(String currency_sell, String amount) throws Throwable {
        double value = Double.parseDouble(webDriver.findElement(By.xpath("//tr/td[contains(text(), '"+ currency_sell +"')]/../td[3]")).getText());
        double currency_sell_amount = Double.parseDouble(amount);
        double currency_buy_amount = currency_sell_amount / value;
        currency_buy_amount = Math.round(currency_buy_amount*100) / 100.0;
        DecimalFormat toTheFormat = new DecimalFormat("#0.00");
        String currency_buy_amount_string = String.valueOf(toTheFormat.format(currency_buy_amount));
        WebElement webElement = webDriver.findElement(By.xpath("//input[@id=\"currency-buy-textfield\"]"));
        try {
            assertThat(webElement.getAttribute("value")).isEqualTo(currency_buy_amount_string);

        } finally {
            webDriver.quit();
        }
    }

    @Then("^buy amount should be calculated correctly using \"([^\"]*)\" and \"([^\"]*)\"$")
    public void amountBuyingIsCalculatedCorrectly(String currency_buy, String amount) throws Throwable {
        double value = Double.parseDouble(webDriver.findElement(By.xpath("//tr/td[contains(text(), '"+ currency_buy +"')]/../td[2]")).getText());
        double currency_sell_amount = Double.parseDouble(amount);
        double currency_buy_amount = currency_sell_amount * value;
        currency_buy_amount = Math.round(currency_buy_amount*100) / 100.0;
        DecimalFormat toTheFormat = new DecimalFormat("#0.00");
        String currency_buy_amount_string = String.valueOf(toTheFormat.format(currency_buy_amount));
        WebElement webElement = webDriver.findElement(By.xpath("//input[@id=\"currency-buy-textfield\"]"));
        try {
            assertThat(webElement.getAttribute("value")).isEqualTo(currency_buy_amount_string);

        } finally {
            webDriver.quit();
        }
    }
}