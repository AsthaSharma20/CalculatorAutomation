package com.Zoominfo.Calculator;

import com.Zoominfo.Calculator.CalculatorPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Set the test method order based on @Order annotations
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CalculatorTest {
    private static WebDriver driver;
    private CalculatorPage calculatorPage;

    // Formulas used in the tests
    private static final String FORMULA_ADDITION = "2 + 3";
    private static final String FORMULA_SUBTRACTION = "10 - 2";
    private static final String FORMULA_MULTIPLICATION = "(10 - 2) * 2";
    private static final String FORMULA_SINE = "sin(30)";

    // Expected results corresponding to the formulas
    private static final String RESULT_ADDITION = "5";
    private static final String RESULT_SUBTRACTION = "8";
    private static final String UNEXPECTED_MULTIPLICATION_RESULT = "20";
    private static final String RESULT_SINE = "0.5";

    // List of expected formulas for the history test
    List<String> expectedFormulas = List.of(
            FORMULA_SINE,
            FORMULA_MULTIPLICATION,
            FORMULA_SUBTRACTION,
            FORMULA_ADDITION);

    // URL of the calculator page
    private static final String URL = "https://web2.0calc.com/";

    // Set up WebDriver and open the calculator page before all tests
    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.firefox.bin", "/Applications/Firefox.app/Contents/MacOS/firefox");
        System.setProperty("webdriver.gecko.driver",
                System.getProperty("user.dir") + "/src/test/resources/geckodriver");
        driver = new FirefoxDriver();
        driver.get(URL);
    }

    // Initialize CalculatorPage instance before each test
    @BeforeEach
    public void setupClass() {
        calculatorPage = new CalculatorPage(driver);
    }

    // Quit WebDriver after all tests are executed
    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    // Test addition functionality
    @Test
    @Order(1)
    public void testAddition() {
        calculatorPage.enterFormula(FORMULA_ADDITION);
        assertEquals(RESULT_ADDITION, calculatorPage.getDisplayResult());
        System.out.println("ADDITION TEST");
    }

    // Test subtraction functionality
    @Test
    @Order(2)
    public void testSubtraction() {
        calculatorPage.enterFormula(FORMULA_SUBTRACTION);
        assertEquals(RESULT_SUBTRACTION, calculatorPage.getDisplayResult());
        System.out.println("SUBTRACTION TEST");
    }

    // Test multiplication functionality
    @Test
    @Order(3)
    public void testMultiplication() {
        calculatorPage.enterFormula(FORMULA_MULTIPLICATION);
        assertNotEquals(UNEXPECTED_MULTIPLICATION_RESULT, calculatorPage.getDisplayResult());
        System.out.println("MULTIPLY TEST");
    }

    // Test sine functionality
    @Test
    @Order(4)
    public void testSine() {
        calculatorPage.enterFormula(FORMULA_SINE);
        assertEquals(RESULT_SINE, calculatorPage.getDisplayResult());
        System.out.println("SIN TEST");
    }

    // Test history functionality
    @Test
    @Order(5)
    public void testHistory() {
        // Get the actual history from the calculator
        String actualHistory = calculatorPage.getHistory().trim();

        // Filter out the result lines and keep only the formulas
        List<String> actualFormulas = Stream.of(actualHistory.split("\n"))
                .map(String::trim)
                .filter(line -> expectedFormulas.stream().anyMatch(line::contains))
                .collect(Collectors.toList());
        // Assert that all expected formulas are found in the history
        assertTrue(actualFormulas.containsAll(expectedFormulas), "All expected formulas not found in history");
    }
}
