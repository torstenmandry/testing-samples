package de.javandry.testingsamples.cucumber;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import de.javandry.testingsamples.app.InvestmentPosition;
import de.javandry.testingsamples.app.ProjectManagementApp;
import de.javandry.testingsamples.testdata.InvestmentPositionFactory;

import static de.javandry.testingsamples.matcher.PositionValueMatcher.containsMonthsFromTo;
import static de.javandry.testingsamples.matcher.PositionValueMatcher.hasConstantAmount;
import static org.junit.Assert.assertThat;

@SuppressWarnings("UnusedDeclaration")
public class CalculateArmortisationSzenario {

  private InvestmentPosition investment;

  @Given("an investment position with amount (\\d+) EUR initiated on (\\d{2}\\.\\d{2}\\.\\d{4})$")
  public void setupInvestmentPosition(double amount, String initiationDate) {
    investment = InvestmentPositionFactory.createDefault()
        .withAmount(amount)
        .withInitiationDate(initiationDate);
  }

  @Given("an armortisation time of (\\d+) months")
  public void setupArmortisationPeriod(int months) {
    ProjectManagementApp.getInstance().setArmortisationMonths(months);
  }

  @When("I calculate the armortisation of the investment position")
  public void calculateArmortisation() {
    investment.calculateArmortisation();
  }

  @Then("the monthly armortisation amount is (\\d+) EUR")
  public void assertMonthlyArmortisationAmount(double amount) {
    assertThat(investment.getArmortisation(), hasConstantAmount(amount));
  }

  @And("the armortisation runs from (\\d{2}\\/\\d{4}) to (\\d{2}\\/\\d{4})$")
  public void assertArmortisationRunsFromTo(String armortisationBegin, String armortisationEnd) {
    assertThat(investment.getArmortisation(), containsMonthsFromTo(armortisationBegin, armortisationEnd));
  }
}

