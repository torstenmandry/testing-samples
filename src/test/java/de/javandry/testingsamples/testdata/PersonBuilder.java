package de.javandry.testingsamples.testdata;

@SuppressWarnings("UnusedDeclaration")
public class PersonBuilder {

  public static final String DEFAULT_FIRST_NAME = "Peter";
  public static final String DEFAULT_LAST_NAME = "Pan";
  public static final String DEFAULT_EMAIL = "peter.pan@nimmerland.net";

  public static ConfigurablePerson createDefault() {
    return new ConfigurablePerson()
        .withFirstName(DEFAULT_FIRST_NAME)
        .withLastName(DEFAULT_LAST_NAME)
        .withEMail(DEFAULT_EMAIL);
  }

  public static ConfigurablePerson create(String firstName, String lastName, String eMail) {
    return new ConfigurablePerson()
        .withFirstName(firstName)
        .withLastName(lastName)
        .withEMail(eMail);
  }
}
