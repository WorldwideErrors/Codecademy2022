//Deze Unittest is niet gebruikt, aangezien wij binnen de app een spinner gebruiken
//
//
//
//
//
//import org.junit.Test;
//import static org.junit.Assert.*;
//
//public class NumericRangeToolsTest {
//
//    /**
//     * @author WorldWideErrors aka Jeffrey van Tillo
//     */
//
//    public NumericRangeToolsTest() {
//    }
//
//    /**
//     * @desc Validates if the input is within range of 0-100%
//     *
//     * @subcontract value within valid range {
//     * @requires 0 <= percentage <= 100;
//     *   @
//     * ensures \result = true; }
//     */
//    @Test
//    public void testValidatePercentage_betweenZeroAndHundred() {
//        int number = 58;
//        Boolean result = NumericRangeTools.isValidPercentage(number);
//        assertEquals(true, result);
//    }
//
//    /**
//     * @subcontract value out of range low {
//     * @requires percentage < 0;
//     * @ensures \result = false; }
//     */
//    @Test
//    public void testValidatePercentage_lowOutOfRange() {
//        int number = -58;
//        Boolean result = NumericRangeTools.isValidPercentage(number);
//        assertEquals(false, result);
//    }
//
//    /**
//     * @subcontract value out of range high {
//     * @requires percentage > 100;
//     * @signals \result = false; }
//     */
//    @Test
//    public void testValidatePercentage_highOutOfRange() {
//        int number = 118;
//        Boolean result = NumericRangeTools.isValidPercentage(number);
//        assertEquals(false, result);
//    }
//
//}
