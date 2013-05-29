package de.javandry.bddsamples.junit;

import de.javandry.bddsamples.app.InvestmentPosition;
import de.javandry.bddsamples.app.PositionValue;
import de.javandry.bddsamples.app.Project;
import de.javandry.bddsamples.app.ProjectManagementApp;
import de.javandry.bddsamples.testdata.InvestmentPositionBuilder;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;

public class InvestmentArmortisationTest {

  @Test
  public void calculateArmortisationTest() {
    // Gegeben sei eine Investition
    InvestmentPosition investment = new InvestmentPosition("any position name");
    // im Wert von 600 TEUR
    investment.setAmount(BigDecimal.valueOf(600000));
    // aktiviert am 01.01.2012
    investment.setInitiationDate(new GregorianCalendar(2012, 1 - 1, 1).getTime());

    // und eine Abschreibungsdauer von 60 Monaten
    ProjectManagementApp.getInstance().setArmortisationMonths(60);

    // und noch einiges mehr...
    Project project = new Project("any project name");
    project.addPosition(investment);
    project.setStartDate(new GregorianCalendar(2012, 1 - 1, 1).getTime());
    project.setEndDate(new GregorianCalendar(2013, 12 - 1, 31).getTime());


    // wenn die Abschreibung berechnet wird
    investment.calculateArmortisation();


    // dann folgt daraus eine monatliche Abschreibung in Höhe von 10 TEUR
    // in der Zeit von 01/2012 bis 12/2016
    assertEquals("number of armortisation values", 60, investment.getArmortisation().size());
    for (int i = 0; i < 60; i++) {
      PositionValue value = investment.getArmortisation().get(i);

      Calendar expectedMonthYear = new GregorianCalendar(2012, 1 - 1, 1);
      expectedMonthYear.add(Calendar.MONTH, i);

      assertEquals(format("value %d - month", i), expectedMonthYear.get(Calendar.MONTH) + 1, value.getMonth());
      assertEquals(format("value %d - year", i), expectedMonthYear.get(Calendar.YEAR), value.getYear());
      assertEquals(format("value %d - amount", i), BigDecimal.valueOf(10000), value.getAmount());
    }
  }

  @Test
  public void betterCalculateArmortisationTest() {
    // Gegeben sei eine Investition
    // im Wert von 600 TEUR
    // aktiviert am 01.01.2012
    InvestmentPosition investment = InvestmentPositionBuilder.createDefault()
        .withAmount(600000)
        .withInitiationDate(1, 1, 2012);
    // und eine Abschreibungsdauer von 60 Monaten
    ProjectManagementApp.getInstance().setArmortisationMonths(60);

    // wenn die Abschreibung berechnet wird
    investment.calculateArmortisation();

    // dann folgt daraus eine monatliche Abschreibung in Höhe von 10 TEUR
    // in der Zeit von 01/2012 bis 12/2016
    assertEquals("number of armortisation values", 60, investment.getArmortisation().size());
    for (int i = 0; i < 60; i++) {
      PositionValue value = investment.getArmortisation().get(i);

      Calendar expectedMonthYear = new GregorianCalendar(2012, 1 - 1, 1);
      expectedMonthYear.add(Calendar.MONTH, i);

      assertEquals(format("value %d - month", i), expectedMonthYear.get(Calendar.MONTH) + 1, value.getMonth());
      assertEquals(format("value %d - year", i), expectedMonthYear.get(Calendar.YEAR), value.getYear());
      assertEquals(format("value %d - amount", i), BigDecimal.valueOf(10000d), value.getAmount());
    }
  }
}
