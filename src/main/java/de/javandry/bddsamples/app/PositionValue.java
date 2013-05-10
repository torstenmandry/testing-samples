package de.javandry.bddsamples.app;

import java.math.BigDecimal;

@SuppressWarnings("UnusedDeclaration")
public class PositionValue {

  private Position position;
  private int month;
  private int year;
  private BigDecimal amount;

  public PositionValue(Position position, int month, int year, BigDecimal amount) {
    this.position = position;
    this.month = month;
    this.year = year;
    this.amount = amount;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  public int getMonth() {
    return month;
  }

  public void setMonth(int month) {
    this.month = month;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }
}
