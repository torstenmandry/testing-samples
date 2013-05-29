package de.javandry.bddsamples.testdata;

import de.javandry.bddsamples.app.Person;

import java.util.Date;

public class ProjectBuilder {

  public static final String DEFAULT_NAME = "any project name";
  public static final Date DEFAULT_START_DATE = DateBuilder.givenDate(1, 1, 2012).toDate();
  public static final Date DEFAULT_END_DATE = DateBuilder.givenDate(31, 12, 2013).toDate();
  public static final Person DEFAULT_CONTROLLER = PersonBuilder.create("Donald", "Duck", "donald.duck@entenhausen.net");
  public static final Person DEFAULT_MANAGER = PersonBuilder.create("Micky", "Mouse", "micky.mouse@entenhausen.net");

  public static ConfigurableProject createDefault() {
    return new ConfigurableProject(DEFAULT_NAME)
        .withStartDate(DEFAULT_START_DATE)
        .withEndDate(DEFAULT_END_DATE)
        .withController(DEFAULT_CONTROLLER)
        .withManager(DEFAULT_MANAGER);
  }
}
