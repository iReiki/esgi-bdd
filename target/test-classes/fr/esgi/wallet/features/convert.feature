@default_properties
Feature: Convert Wallet

  Default value of a stock is in EUR.

  Scenario Outline: Value the wallet in the targeted currency using exchange rates from external API
    Given a wallet is containing Stocks
      | type  | value	  | share |
      | USD	  | 0.83	  | 18	  |
      | BTC	  | 31791.54  | 0.5	  |
      | PLTR  | 28.15	  | 1	  |
    And exchange rates are from external API
    When EUR is converted to <currency>
    Then the <currency> rate should be <rate>
    And the value should be <value>
    Examples:
      | currency  | rate	| value 	|
      | "USD"	  | 1.2084	| 19286.02  |
      | "GBP"	  | 0.882	| 14026.20  |
