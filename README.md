
### Allure Report Overview
<img width="1882" height="1017" alt="image" src="https://github.com/user-attachments/assets/84cca2f2-096d-420d-8732-b45e4c7938de" />

### Test Execution Details
<img width="1892" height="1062" alt="image" src="https://github.com/user-attachments/assets/6a496690-46e7-4e2a-82ca-a727e7e324fa" />

## Allure Test Report

This project uses **Allure Report** to generate a detailed and visual test execution report.

The Allure report provides information about:

* Total number of test cases executed
* Passed, failed, broken, and skipped test cases
* Test execution duration
* Test suites and test behaviors
* Detailed information for each test case
* Error messages and failure details


### Generate Allure Report

Run the automated tests:

```bash
gradle clean test
```

Generate and open the Allure report:

```bash
allure generate allure-results --clean -output
allure serve allure-results
```

The Allure report helps to easily analyze the automation test results and identify passed, failed, broken, or skipped test cases.


## Test Cases

The standard manual test cases for this automation project, including positive and negative test scenarios, are available here:

https://docs.google.com/spreadsheets/d/1k0dQ6SwiAG9tR9RhpnfNf0z6ETeQXfyXOQBxhdltXk8/edit?usp=sharing

