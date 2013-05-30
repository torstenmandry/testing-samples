package de.javandry.testingsamples.testdata;

import de.javandry.testingsamples.app.Person;
import de.javandry.testingsamples.app.Project;

import java.util.Date;

@SuppressWarnings("UnusedDeclaration")
public class ConfigurableProject extends Project {

  public ConfigurableProject(String name) {
    super(name);
  }

  public ConfigurableProject withName(String name) {
    this.setName(name);
    return this;
  }

  public ConfigurableProject withStartDate(Date startDate) {
    this.setStartDate(startDate);
    return this;
  }

  public ConfigurableProject withEndDate(Date endDate) {
    this.setEndDate(endDate);
    return this;
  }

  public ConfigurableProject withController(Person controller) {
    this.setController(controller);
    return this;
  }

  public ConfigurableProject withManager(Person manager) {
    this.setManager(manager);
    return this;
  }
}
