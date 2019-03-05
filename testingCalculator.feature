Feature: Currency convertion calculator

  @multiple
  Scenario Outline: CCY to EUR (selling)
    Given I open firefox browser
    When I navigate to Luminor webpage
    And I provide CCY as "<currency_sell>" and amount as "<amount>"
    Then sell amount should be calculated correctly using "<currency_sell>" and "<amount>"

    Examples:
      | currency_sell | amount |
      | AUD           | 60.00  |
      | DKK           | 34.21  |
      | SGD           | 0.10   |
      | JPY           | 6.8    |
      | NOK           | 10     |

  @multiple
  Scenario Outline: EUR to CCY (buying)
    Given I open firefox browser
    When I navigate to Luminor webpage
    And I provide EUR as "<currency_sell>" and amount as "<amount>"
    And I provide "<currency_buy>" as buying CCY
    Then buy amount should be calculated correctly using "<currency_buy>" and "<amount>"

    Examples:
      | currency_sell | amount | currency_buy|
      | EUR           | 60.00  | AUD         |
      | EUR           | 44.07  | USD         |
      | EUR           | 0.20   | DKK         |
      | EUR           | 6.09   | JPY         |
      | EUR           | 70     | RUB         |