package de.javandry.testingsamples.testdata;

import de.javandry.testingsamples.app.InvestmentPosition;
import de.javandry.testingsamples.app.Project;

import java.math.BigDecimal;
import java.util.Date;

@SuppressWarnings("UnusedDeclaration")
public class ConfigurableInvestmentPosition extends InvestmentPosition {

  public ConfigurableInvestmentPosition(String name) {
    super(name);
  }

  public ConfigurableInvestmentPosition withAmount(double amount) {
    this.setAmount(BigDecimal.valueOf(amount));
    return this;
  }

  public ConfigurableInvestmentPosition withInitiationDate(Date initiationDate) {
    this.setInitiationDate(initiationDate);
    return this;
  }

  public ConfigurableInvestmentPosition withInitiationDate(int day, int month, int year) {
    this.setInitiationDate(DateBuilder.givenDate(day, month, year).toDate());
    return this;
  }

  public InvestmentPosition withInitiationDate(String initiationDate) {
    this.setInitiationDate(DateBuilder.parse(initiationDate).toDate());
    return this;
  }

  public ConfigurableInvestmentPosition withCostCenter(String costCenter) {
    this.setCostCenter(costCenter);
    return this;
  }

  public ConfigurableInvestmentPosition withProject(Project project) {
    this.setProject(project);
    return this;
  }

}
