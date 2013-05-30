package de.javandry.testingsamples.app;

import java.math.BigDecimal;

@SuppressWarnings("UnusedDeclaration")
public class Position {
  protected PositionType type;
  protected String name;
  protected Project project;
  protected BigDecimal amount;
  protected String costCenter;

  protected Position(PositionType type, String name) {
    this.type = type;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public String getCostCenter() {
    return costCenter;
  }

  public void setCostCenter(String costCenter) {
    this.costCenter = costCenter;
  }
}
