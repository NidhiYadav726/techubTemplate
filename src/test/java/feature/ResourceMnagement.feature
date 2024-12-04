Feature: AI-Based Test Environment Management

  Scenario: Predict optimal instances and execute tests
    Given AI model is loaded successfully
    When optimal browser instances are predicted
    Then drivers are preloaded based on the prediction
    And tests are executed on the preloaded drivers
    And all drivers are closed after test execution
