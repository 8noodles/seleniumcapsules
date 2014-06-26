Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: scenario description
Given I am on jQuery Calendar page using <browser>
When I pick <month>, <day>, <year> from a datepicker
Then result is <date>
And I close browser

Examples:

|browser     |month    |day|year|date
|CHROME      |September|19 |2014|09/19/2014
|FIREFOX     |Aug      |5  |2010|08/05/2010
|SAFARI      |July     |4  |2014|07/04/2014
|HEADLESS_MAC|Sep      |12 |2014|09/12/2014
