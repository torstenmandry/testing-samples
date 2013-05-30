Feature: calculate armortisation of an investment position
  As a user
  I want to calculate the armortisation of an investment position
  So that I can see the monthly armortisation amounts

  Scenario Outline: calculate armortisation
    Given an investment position with amount <amount> EUR initiated on <initialion_date>
    Given an armortisation time of <armortisation_time> months
    When I calculate the armortisation of the investment position
    Then the monthly armortisation amount is <monthly_armortisation_amount> EUR
    And the armortisation runs from <armortisation_begin> to <armortisation_end>

  Examples:
    | amount | initialion_date | armortisation_time | monthly_armortisation_amount | armortisation_begin | armortisation_end |
    | 600000 | 01.01.2012      | 60                 | 10000                        | 01/2012             | 12/2016           |
    | 30000  | 01.01.2012      | 24                 | 1250                         | 01/2012             | 12/2013           |
    | 60000  | 01.07.2012      | 12                 | 5000                         | 07/2012             | 06/2013           |
