package de.javandry.testingsamples.app;

public class ProjectManagementApp {

  private static ProjectManagementApp instance;
  private int armortisationMonths;

  public static ProjectManagementApp getInstance() {
    if (instance == null)
      instance = new ProjectManagementApp();
    return instance;
  }

  public void setArmortisationMonths(int armortisationMonths) {
    this.armortisationMonths = armortisationMonths;
  }

  public int getArmortisationMonths() {
    return armortisationMonths;
  }
}
