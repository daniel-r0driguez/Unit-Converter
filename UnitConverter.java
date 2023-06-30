// Daniel Rodriguez - CS003B
// Assignment 5: Chapter 5 (P5.5)

/**
 * This class allows for conversions between different units of measurement.
 */
public class UnitConverter
{
    // Volume Units represented as Strings
    private static final String ML = "ml";
    private static final String L = "l";
    private static final String FL_OZ = "fl. oz";
    private static final String GAL = "gal";

    // Mass Units represented as Strings
    private static final String G = "g";
    private static final String KG = "kg";
    private static final String OZ = "oz";
    private static final String LB = "lb";

    // Length Units represented as Strings
    private static final String MM = "mm";
    private static final String CM = "cm";
    private static final String M = "m";
    private static final String KM = "km";
    private static final String IN = "in";
    private static final String FT = "ft";
    private static final String MI = "mi";

    // Volume Units conversion table
    private static final double [][] VOLUME_CONVERSIONS = {
            //    ml    |     l    |  fl. oz |     gal     |
            {          1,     0.001, 0.033814, 0.000264172}, // ml
            {       1000,         1,   33.814,    0.264172}, // l
            {    29.5735, 0.0295735,        1,    0.0078125}, // fl. oz
            {3785.411784,    3.7854,      128,           1}}; // gal

    // Mass Units conversion table
    private static final double [][] MASS_CONVERSIONS = {
            //  g   |    kg    |   oz    |     lb     |
            {      1,     0.001, 0.035274, 0.00220462}, // g
            {   1000,         1,   35.274,    2.20462}, // kg
            {28.3495, 0.0283495,        1,     0.0625}, // oz
            {453.592,  0.453592,       16,          1}}; // lb

    // Length Units conversion table
    private static final double [][] LENGTH_CONVERSIONS = {
            //  mm  |   cm   |   m   |    km    |    in    |     ft    |       mi      |
            {      1,    0.1,   0.001,  0.000001, 0.0393701, 0.00328084, 0.00000062137}, // mm
            {     10,      1,    0.01,   0.00001, 0.393701,   0.0328084,  0.0000062137}, // cm
            {   1000,    100,       1,     0.001,  39.3701,     3.28084,   0.000621371}, // m
            {1000000, 100000,    1000,         1,  39370.1,     3280.84,      0.621371}, // km
            {   25.4,   2.54,  0.0254, 0.0000254,        1,      1/12.0,     1/63360.0}, // in
            {  304.8,  30.48,  0.3048, 0.0003048,       12,           1,   0.000189394}, // ft
            {1609000, 160934, 1609.34,   1.60934,    63360,        5280,             1}}; // mi

    // No default constructor since this is a static utility class.
    private UnitConverter(){}

    // Public static class functions (2)
    /**
     * Converts from one unit of measurement to another unit of measurement.
     * Checking to see if the conversion is compatible should be done before calling this function.
     * @param frmUnit the initial unit of measurement
     * @param toUnit the desired unit of measurement that the value should be converted to
     * @param value the value of measurement
     * @return the value converted to the requested unit of measurement
     */
    public static double convert(String frmUnit, String toUnit, double value)
    {
        // Format the Strings.
        String formattedFrm = frmUnit.strip().toLowerCase();
        String formattedTo = toUnit.strip().toLowerCase();

        // Get the index of the passed in Units.
        int frmIndex = findUnitIndex(formattedFrm);
        int toIndex = findUnitIndex(formattedTo);

        // Acquire the correct scalar to apply to the value.
        double scalar = switch (formattedFrm) {
            // Scalar is a Volume conversion.
            case ML, L, FL_OZ, GAL -> VOLUME_CONVERSIONS[frmIndex][toIndex];
            // Scalar is a Mass conversion.
            case G, KG, OZ, LB -> MASS_CONVERSIONS[frmIndex][toIndex];
            // Scalar is a Length conversion.
            case MM, CM, M, KM, IN, FT, MI -> LENGTH_CONVERSIONS[frmIndex][toIndex];
            default -> 0;
        };
        return value * scalar;
    }

    /**
     * Function which checks if the units of measurements are compatible and convertible.
     * @param frm the initial unit of measurement
     * @param to the desired unit of measurement
     * @return true if the units are compatible, false if they are not
     */
    public static boolean isLegalConversion(String frm, String to)
    {
        // Format the Strings.
        String formattedFrm = frm.strip().toLowerCase();
        String formattedTo = to.strip().toLowerCase();
        return switch (formattedFrm) {
            case ML, L, FL_OZ, GAL ->
                // Return true if it is a Volume conversion.
                    formattedTo.equals(ML) || formattedTo.equals(L) ||
                            formattedTo.equals(FL_OZ) || formattedTo.equals(GAL);
            case G, KG, OZ, LB ->
                // Return true if it is a Mass conversion.
                    formattedTo.equals(G) || formattedTo.equals(KG) ||
                            formattedTo.equals(OZ) || formattedTo.equals(LB);
            case MM, CM, M, KM, IN, FT, MI ->
                // Return true if it is a Length conversion.
                    formattedTo.equals(MM) || formattedTo.equals(CM) ||
                            formattedTo.equals(M) || formattedTo.equals(KM)
                            || formattedTo.equals(IN) || formattedTo.equals(FT) ||
                            formattedTo.equals(MI);
            default ->
                // Return false since it is not a compatible conversion.
                    false;
        };
    }

    // Private static helper functions (1)
    /**
     * Helper function which returns an index when given a legal and expected measurement unit...
     * ...represented as a String.
     * @param unit the String variable which contains the unit (Ex. "cm", "in", "fl. oz", etc...)
     * @return the index that belongs to the unit in relation to its table...
     * ...which are: VOLUME_CONVERSIONS, MASS_CONVERSIONS, or LENGTH_CONVERSIONS.
     */
    private static int findUnitIndex(String unit)
    {
        return switch (unit) {
            case ML, G, MM -> 0;
            case L, KG, CM -> 1;
            case FL_OZ, OZ, M -> 2;
            case GAL, LB, KM -> 3;
            case IN -> 4;
            case FT -> 5;
            case MI -> 6;
            default -> -1;
        };
    }
}