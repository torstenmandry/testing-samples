package de.javandry.testingsamples.concordion;

import de.javandry.testingsamples.app.InvestmentPosition;
import de.javandry.testingsamples.app.PositionValue;
import de.javandry.testingsamples.app.ProjectManagementApp;
import de.javandry.testingsamples.jbehave.CalculateArmortisationSteps;
import de.javandry.testingsamples.testdata.InvestmentPositionBuilder;
import org.concordion.integration.junit4.ConcordionRunner;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.util.*;

import static de.javandry.testingsamples.matcher.PositionValueMatcher.containsMonthsFromTo;
import static de.javandry.testingsamples.matcher.PositionValueMatcher.haveConstantAmount;
import static java.lang.String.format;
import static org.junit.Assert.assertThat;

@RunWith(ConcordionRunner.class)
public class CalculateArmortisationOfAnInvestmentPositionFixture {

  public InvestmentPosition setupInvestmentPosition(double amount, String initiationDate) {
    return InvestmentPositionBuilder.createDefault()
        .withAmount(amount)
        .withInitiationDate(initiationDate);
  }

  public void setupArmortisationTime(int months) {
    ProjectManagementApp.getInstance().setArmortisationMonths(months);
  }

  public void calculateArmortisation(InvestmentPosition investment) {
    investment.calculateArmortisation();
  }

  public String monthlyArmortisationAmount(InvestmentPosition investment) {
    Set<BigDecimal> uniqueAmounts = new HashSet<BigDecimal>();
    for(PositionValue value : investment.getArmortisation()) {
      uniqueAmounts.add(value.getAmount());
    }

    if (uniqueAmounts.size() == 1) { // expected
      return format("%.2f", uniqueAmounts.iterator().next());
    }

    StringBuilder variousAmounts = new StringBuilder("various: [");
    boolean first = true;
    for (BigDecimal amount : uniqueAmounts) {
      if (!first) variousAmounts.append(", ");
      variousAmounts.append(amount.toString());
    }
    variousAmounts.append("]");
    return variousAmounts.toString();
  }

  public String armortisationStart(InvestmentPosition investment) {
    PositionValue firstValue = getSortedPositionValues(investment).get(0);
    return format("%02d/%4d", firstValue.getMonth(), firstValue.getYear());
  }

  public String armortisationEnd(InvestmentPosition investment) {
    List<PositionValue> values = getSortedPositionValues(investment);
    PositionValue lastValue = values.get(values.size() - 1);
    return format("%02d/%4d", lastValue.getMonth(), lastValue.getYear());
  }

  public boolean isContinousArmortisation(InvestmentPosition investment) {
    return containsMonthsFromTo(armortisationStart(investment), armortisationEnd(investment)).matches(investment.getArmortisation());
  }

  private List<PositionValue> getSortedPositionValues(InvestmentPosition investment) {
    List<PositionValue> values = investment.getArmortisation();
    Collections.sort(values, new Comparator<PositionValue>() {
      @Override
      public int compare(PositionValue val1, PositionValue val2) {
        return format("%4d/%2d", val1.getYear(), val1.getMonth()).compareTo(format("%4d/%2d", val2.getYear(),
            val2.getMonth()));
      }
    });
    return values;
  }
}
