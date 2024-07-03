                    CalculatorAutomation
This project demonstrates test automation using Selenium WebDriver and JUnit 5 for the website https://web2.0calc.com/. The tests are designed to perform basic mathematical calculations and validate the calculator's functionality. The Page Object Model (POM) design pattern is utilized for improved code structure and maintenance.

Prerequisites
Java 8 or higher
Maven or Gradle
WebDriver compatible with your browser (e.g., ChromeDriver, GeckoDriver for Firefox)

Setup
Clone this repository to your local machine.
Ensure you have Java and Gradle installed.
Download the appropriate web browser and its WebDriver executable (e.g., ChromeDriver, GeckoDriver) and place it in your system's PATH or project directory.
Open the project in your preferred IDE.

Add the browser path in the test file on line 55 by setting the system property

Running the Tests
Navigate to the project directory in your terminal or command prompt.

Run the following command in your terminal to execute the tests:

bash
gradle clean test

Test Overview
CalculatorPage : Contains methods to interact with the calculator web elements.
CalculatorTest : Contains separate tests for addition, subtraction, multiplication, sine function, and history validation.
   History Validation : Ensures that the history displays the correct formulas after performing calculations.

Project Structure :

|-- src
|   |-- main
|   |   `-- java
|   |       `-- com
|   |           `-- Zoominfo
|   |               `-- Calculator
|   |                   |-- CalculatorPage.java
|   |                   
|   |-- test
|   |   `-- java
|   |       `-- com
|   |           `-- Zoominfo
|   |               `-- Calculator
|   |                   `-- CalculatorTest.java
|   └── resources
│           └── geckodriver (Place your geckodriver executable here)
|-- build.gradle
|-- README.md   

Notes
Make sure to replace the WebDriver executable path in the CalculatorPage class with your actual driver path.