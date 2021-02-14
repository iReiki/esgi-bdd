@default_properties
Feature: Wallet total value

  Default value of a stock is in EUR.

  Scenario Outline: Get total value of the wallet in default currency
    Given a wallet is containing Stocks
      | type | value    | share |
      | USD  | 0.83     | 18    |
      | BTC  | 31791.54 | 0.5   |
      | PLTR | 28.15    | 1     |
    When the total is calculating in <currency>
    Then the total should be <value>
    Examples:
      | currency  | value    |
      | "EUR"     | 15938.86 |