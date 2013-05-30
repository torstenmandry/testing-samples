package de.javandry.testingsamples.testdata;

import de.javandry.testingsamples.app.Person;

public class ConfigurablePerson extends Person {

  public ConfigurablePerson withFirstName(String firstName) {
    this.setFirstName(firstName);
    return this;
  }

  public ConfigurablePerson withLastName(String lastName) {
    this.setLastName(lastName);
    return this;
  }

  public ConfigurablePerson withEMail(String eMail) {
    this.setEMail(eMail);
    return this;
  }
}
