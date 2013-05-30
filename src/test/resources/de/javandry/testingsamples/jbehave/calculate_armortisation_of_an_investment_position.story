Narrative:
As a user
I want to calculate the armortisation of an investment position
So that I can see the monthly armortisation amounts

Scenario: calculate armortisation for 600000 EUR and 60 months
Given an investment position with amount 600000 EUR initiated on 01.01.2012
And an armortisation time of 60 months
When I calculate the armortisation of the investment position
Then the monthly armortisation amount is 10000 EUR
And the armortisation runs from 01/2012 to 12/2016

Scenario: calculate armortisation for 30000 EUR and 24 months
Given an investment position with amount 30000 EUR initiated on 01.01.2012
And an armortisation time of 24 months
When I calculate the armortisation of the investment position
Then the monthly armortisation amount is 1250 EUR
And the armortisation runs from 01/2012 to 12/2013