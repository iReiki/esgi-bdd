@default_properties
Feature: Error

  Scenario Outline: Retrieve an error when the currency is unavailable from external API
    Given a wallet is containing Stocks
      | type	| value		| share |
      | "USD"	| 0.83		| 18	|
      | "BTC"	| 31791.54	| 0.5	|
      | "PLTR"	| 28.15		| 1	    |
    And the <currency> rate from external API does not exist
    When EUR is converted to <currency>
    Then error should be <error>
    And the value should be <value>
    Examples:
      | currency  | value | error                           |
      | "TEST"	  | 0     | "Currency TEST doesn't exist."  |
      |	""        | 0     | "Currency  doesn't exist."       |