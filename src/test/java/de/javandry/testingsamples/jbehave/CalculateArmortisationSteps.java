package de.javandry.testingsamples.jbehave;

import de.javandry.testingsamples.app.InvestmentPosition;
import de.javandry.testingsamples.app.ProjectManagementApp;
import de.javandry.testingsamples.testdata.InvestmentPositionBuilder;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static de.javandry.testingsamples.matcher.PositionValueMatcher.containsMonthsFromTo;
import static de.javandry.testingsamples.matcher.PositionValueMatcher.haveConstantAmount;
import static org.junit.Assert.assertThat;

@SuppressWarnings("UnusedDeclaration")
public class CalculateArmortisationSteps {

  private InvestmentPosition investment;

  @Given("an investment position with amount $amount EUR initiated on $initiationDate")
  public void setupInvestmentPosition(double amount, String initiationDate) {
    investment = InvestmentPositionBuilder.createDefault()
        .withAmount(amount)
        .withInitiationDate(initiationDate);
  }

  @Given("an armortisation period of $months months")
  public void setupArmortisationPeriod(int months) {
    ProjectManagementApp.getInstance().setArmortisationMonths(months);
  }

  @When("I calculate the armortisation of the investment position")
  public void calculateArmortisation() {
    investment.calculateArmortisation();
  }

  @Then("the monthly armortisation amount is $amount EUR")
  public void assertMonthlyArmortisationAmount(double amount) {
    assertThat(investment.getArmortisation(), haveConstantAmount(amount));
  }

  @Then("the armortisation runs from $startMonthYear to $endMonthYear")
  public void assertArmortisationRunsFromTo(String startMonthYear, String endMonthYear) {
    assertThat(investment.getArmortisation(), containsMonthsFromTo(startMonthYear, endMonthYear));
  }
}

