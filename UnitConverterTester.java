// Daniel Rodriguez - CS003B
// Assignment 5: Chapter 5 (P5.5)
import java.util.Scanner;

/**
 * This tester class extensively tests the UnitConverter class's static method.
 * Testing is done through user input as well as some static sample test cases as well.
 */
public class UnitConverterTester
{
    public static void main(String[] args)
    {
        String fromUnit;
        String toUnit;
        double value;
        double convertedValue;
        String userResponse;
        System.out.print("Unit Converter Program\n=============================");
        do
        {
            // Print out the current accepted units.
            printCurrentUnits();
            // Get the initial unit of measurement.
            System.out.print("Convert from?: ");
            fromUnit = getLine();
            // Get the desired unit of measurement.
            System.out.print("Convert to?: ");
            toUnit = getLine();
            // Ensure that the units are compatible.
            if (UnitConverter.isLegalConversion(fromUnit, toUnit))
            {
                // Get the value of the unit.
                System.out.print("Value?: ");
                value = getDouble();
                // Get the converted value.
                convertedValue = UnitConverter.convert(fromUnit, toUnit, value);
                // Print the result.
                printResult(fromUnit, toUnit,  value, convertedValue);
            }
            else
            {
                System.out.println("That is an incompatible conversion!");
            }
            // Get the user's response.
            System.out.print("Want to make another conversion? (Y/N): ");
            userResponse = getLine();
        } while (userResponse.equalsIgnoreCase("Y"));

        // Print some sample test cases.
        System.out.println("\nSome Sample Volume Test Cases\n==================================");
        // Fl. oz conversion tests
        staticTestCase("fl. oz", "gal", 29, "0.226563");
        staticTestCase("fl. oz", "ml", -123, "-3637.54");
        staticTestCase("fl. oz", "l", 169, "4.99793");
        // Gal conversion tests
        staticTestCase("gal", "fl. oz", 58, "7424");
        staticTestCase("gal", "ml", 180, "681,374");
        staticTestCase("gal", "l", 14,"52.9958");
        // Ml conversion tests
        staticTestCase("ml", "fl. oz", 29, "0.980607");
        staticTestCase("ml", "gal", 500, "0.132086");
        staticTestCase("ml", "l", 4213, "4.213");
        // L conversion tests
        staticTestCase("l", "fl. oz", 169, "5714.57");
        staticTestCase("l", "gal", 199, "52.5702");
        staticTestCase("l", "ml", 43, "43000");
    }

    // Helper functions (5)
    /**
     * Simply prints out the current supported Units of measurements.
     */
    public static void printCurrentUnits()
    {
        System.out.println("\nVolume Units: \"ml\", \"l\", \"fl. oz\", \"gal\"\n" +
                           "Mass Units: \"g\", \"kg\", \"oz\", \"lb\"\n" +
                           "Length Units: \"mm\", \"cm\", \"m\", \"km\", \"in\", \"ft\", \"mi\"\n");
    }

    /**
     * Retrieves the entire line of the user's input.
     * @return the entire line of the user's input
     */
    public static String getLine()
    {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    /**
     * Helper function which handles input mismatches for the double data type.
     * @return the user's double input. If an unexpected value is entered, a default value of 0.0 is returned.
     */
    public static double getDouble()
    {
        Scanner in = new Scanner(System.in);
        double input = 0;
        if (in.hasNextDouble())
        {
            input = in.nextDouble();
        }
        return input;
    }

    /**
     * Template print statement which prints out the results of a Unit conversion.
     * @param fromUnit the initial Unit of measurement
     * @param toUnit the desired Unit of measurement
     * @param value the original value
     * @param convertedValue the value after it has been converted to the new Unit of measurement
     */
    public static void printResult(String fromUnit, String toUnit, double value, double convertedValue)
    {
        System.out.println("--------------------------------------");
        System.out.printf("%f %s converted to %s is: %.10f %s\n", value, fromUnit, toUnit,
                        convertedValue, toUnit);
    }

    /**
     * Executes a test case and prints out the results.
     * @param frm the initial Unit of measurement
     * @param to the desired Unit of measurement
     * @param value the original value
     * @param expected the expected output
     */
    public static void staticTestCase(String frm, String to, double value, String expected)
    {
        if (UnitConverter.isLegalConversion(frm, to))
        {
            double convertedValue = UnitConverter.convert(frm, to, value);
            System.out.println("--------------------------------------");
            System.out.printf("%f %s converted to %s is: %.10f %s\n", value, frm, to, convertedValue, to);
            System.out.printf("(Expected ~Approximately~: %s)\n", expected);
        }
        else
        {
            System.out.println("That is an incompatible conversion!");
            System.out.printf("(Expected: %s)\n", expected);
        }
    }
}