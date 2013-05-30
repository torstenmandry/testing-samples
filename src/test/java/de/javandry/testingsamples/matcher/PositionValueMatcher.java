package de.javandry.testingsamples.matcher;

import de.javandry.testingsamples.app.PositionValue;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Pattern;

import static java.lang.String.format;

public class PositionValueMatcher {

  public static Matcher<? super List<PositionValue>> haveConstantAmount(final double expectedAmount) {
    return new TypeSafeMatcher<List<PositionValue>>() {
      private PositionValue currentPositionValue;

      @Override
      protected boolean matchesSafely(List<PositionValue> positionValues) {
        for (PositionValue value : positionValues) {
          currentPositionValue = value;
          if (!currentPositionValue.getAmount().equals(BigDecimal.valueOf(expectedAmount))) {
            return false;
          }
        }
        return true;
      }

      @Override
      public void describeTo(Description description) {
        description.appendText(format("unexpected amount in %02d/%4d: %.2f",
            currentPositionValue.getMonth(),
            currentPositionValue.getYear(),
            currentPositionValue.getAmount()));
      }
    };
  }

  public static Matcher<? super List<PositionValue>> containsValueFor(final int month, final int year) {
    return new TypeSafeMatcher<List<PositionValue>>() {
      @Override
      protected boolean matchesSafely(List<PositionValue> positionValues) {
        for (PositionValue nextValue : positionValues) {
          if (nextValue.getMonth() == month && nextValue.getYear() == year) {
            return true;
          }
        }
        return false;
      }

      @Override
      public void describeTo(Description description) {
        description.appendText(format("does not contain value for %2d/%4d", month, year));
      }
    };
  }

  public static Matcher<? super List<PositionValue>> containsMonthsFromTo(final int fromMonth, final int fromYear,
                                                                          final int toMonth, final int toYear) {
    return new TypeSafeMatcher<List<PositionValue>>() {
      private Calendar currentMonthYear;

      @Override
      protected boolean matchesSafely(List<PositionValue> positionValues) {
        Calendar fromMonthYear = new GregorianCalendar(fromYear, fromMonth - 1, 1);
        Calendar toMonthYear = new GregorianCalendar(toYear, toMonth - 1, 2); // set second day so that we can safely
        // use before method, even for the last month
        for (currentMonthYear = fromMonthYear; currentMonthYear.before(toMonthYear); currentMonthYear.add(Calendar
            .MONTH, 1)) {
          if (!containsValueFor(currentMonthYear.get(Calendar.MONTH) + 1, currentMonthYear.get(Calendar.YEAR))
              .matches(positionValues)) {
            return false;
          }
        }
        return true;
      }

      @Override
      public void describeTo(Description description) {
        description.appendText(format("does not contain value for %2d/%4d", currentMonthYear.get(Calendar.MONTH) + 1, currentMonthYear.get(Calendar.YEAR)));
      }
    };
  }

  public static Matcher<? super List<PositionValue>> containsMonthsFromTo(final String fromMonthYear,
                                                                          final String toMonthYear) {
    Pattern pattern = Pattern.compile("^(\\d+)/(\\d+)$");

    java.util.regex.Matcher fromMonthYearMatcher = pattern.matcher(fromMonthYear);
    if(!fromMonthYearMatcher.matches()) {
      throw new IllegalArgumentException("fromMonthYear: " + fromMonthYear);
    }
    java.util.regex.Matcher toMonthYearMatcher = pattern.matcher(toMonthYear);
    if(!toMonthYearMatcher.matches()) {
      throw new IllegalArgumentException("toMonthYear: " + toMonthYear);
    }

    return containsMonthsFromTo(
        Integer.valueOf(fromMonthYearMatcher.group(1)), Integer.valueOf(fromMonthYearMatcher.group(2)),
        Integer.valueOf(toMonthYearMatcher.group(1)), Integer.valueOf(toMonthYearMatcher.group(2)));
  }
}
