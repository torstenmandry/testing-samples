package de.javandry.bddsamples.app;

@SuppressWarnings("UnusedDeclaration")
public class Person {
  private String lastName;
  private String firstName;
  private String eMail;

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String geteMail() {
    return eMail;
  }

  public void setEMail(String eMail) {
    this.eMail = eMail;
  }
}
