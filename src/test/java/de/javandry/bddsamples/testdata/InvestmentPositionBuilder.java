package de.javandry.bddsamples.testdata;

import de.javandry.bddsamples.app.Project;

import java.util.Date;

public class InvestmentPositionBuilder {

  private static final String DEFAULT_NAME = "any position name";
  private static final double DEFAULT_AMOUNT = 100000;
  private static final Date DEFAULT_INITIATION_DATE = DateBuilder.givenDate(1, 1, 2012).toDate();
  private static final String DEFAULT_COST_CENTER = "66666";

  public static ConfigurableInvestmentPosition createDefault() {
    ConfigurableInvestmentPosition investmentPosition = new ConfigurableInvestmentPosition(DEFAULT_NAME)
        .withAmount(DEFAULT_AMOUNT)
        .withInitiationDate(DEFAULT_INITIATION_DATE)
        .withCostCenter(DEFAULT_COST_CENTER);
    Project project = ProjectBuilder.createDefault();
    project.addPosition(investmentPosition);
    return investmentPosition;
  }

}
