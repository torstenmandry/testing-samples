package de.javandry.bddsamples.app;

import java.math.BigDecimal;
import java.util.*;

public class InvestmentPosition extends Position {

  private Date initiationDate;
  private List<PositionValue> armortisation;

  public InvestmentPosition(String name) {
    super(PositionType.INVESTMENT, name);
    armortisation = new ArrayList<PositionValue>();
  }

  public void calculateArmortisation() {
    if (initiationDate == null)
      throw new IllegalStateException("initiation date not set");
    if (amount == null)
      throw new IllegalStateException("amount not set");
    if (project == null)
      throw new IllegalStateException("project not set");
    if (project.getStartDate() == null || project.getEndDate() == null)
      throw new IllegalStateException("project start/end date not set");

    armortisation.clear();

    int armortisationMonths = ProjectManagementApp.getInstance().getArmortisationMonths();
    BigDecimal monthlyAmount = amount.divide(BigDecimal.valueOf(armortisationMonths), BigDecimal.ROUND_HALF_UP);
    for (int i = 0; i < armortisationMonths; i++) {
      Calendar monthYear = new GregorianCalendar();
      monthYear.setTime(initiationDate);
      monthYear.add(Calendar.MONTH, i);

      int month = monthYear.get(Calendar.MONTH) + 1;
      int year = monthYear.get(Calendar.YEAR);
      armortisation.add(new PositionValue(this, month, year, monthlyAmount));
    }
  }

  @SuppressWarnings("UnusedDeclaration")
  public Date getInitiationDate() {
    return this.initiationDate;
  }

  public void setInitiationDate(Date initiationDate) {
    this.initiationDate = initiationDate;
  }

  public List<PositionValue> getArmortisation() {
    return armortisation;
  }
}
