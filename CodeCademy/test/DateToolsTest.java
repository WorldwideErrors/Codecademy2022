//
//import org.junit.Test;
//import static org.junit.Assert.*;
//
//public class DateToolsTest {
//
//    /**
//     * @author WorldWideErrors aka Jeffrey van Tillo
//     */
//    public DateToolsTest() {
//    }
//
//    /**
//     * @desc Validates is a given date in the form of day, month and year is
//     * valid.
//     *
//     * @subcontract 31 days in month {
//     * @requires (month == 1 || month == 3 || month == 5 || month == 7 || month
//     * == 8 || month == 10 || month == 12) && 1 <= day <= 31;
//     * @ensures \result = true; }
//     */
//    @Test
//    public void testValidateDate_31daysInAMonth() {
//        int day = 31;
//        int month = 3;
//        int year = 2001;
//        Boolean result = DateTools.ValidateDate(day, month, year);
//        assertEquals(true, result);
//    }
//
//    /**
//     *
//     * @subcontract 30 days in month {
//     * @requires (month == 4 || month == 6 || month == 9 || month == 11) && 1 <=
//     * day <= 30;
//     * @ensures \result = true; }
//     */
//    @Test
//    public void testValidateDate_30daysInAMonth() {
//        int day = 30;
//        int month = 4;
//        int year = 2001;
//        Boolean result = DateTools.ValidateDate(day, month, year);
//        assertEquals(true, result);
//    }
//
//    /**
//     *
//     * @subcontract 29 days in month {
//     * @requires month == 2 && 1 <= day <= 29 && (year % 4 == 0 && (year % 100
//     * != 0 || year % 400 == 0)); @ensures \result = true; }
//     *
//     */
//    @Test
//    public void testValidateDate_29daysInAMonth() {
//        int day = 29;
//        int month = 2;
//        int year = 2001;
//        Boolean result = DateTools.ValidateDate(day, month, year);
//        assertEquals(true, result);
//    }
//
//    /**
//     * @subcontract 28 days in month {
//     * @requires month == 2 && 1 <= day <= 28 && (year % 4 != 0 || (year % 100
//     * == 0 && year % 400 != 0)); @ensures \result = true; }
//     *
//     */
//    @Test
//    public void testValidateDate_28daysInAMonth() {
//        int day = 28;
//        int month = 4;
//        int year = 2001;
//        Boolean result = DateTools.ValidateDate(day, month, year);
//        assertEquals(true, result);
//    }
//
//    /**
//     * @subcontract all other cases {
//     * @requires no other accepting precondition;
//     * @ensures \result = false; }
//     *
//     */
//    @Test
//    public void testValidateDate_31daysInAMonth() {
//        int day = 32;
//        int month = 3;
//        int year = 2001;
//        Boolean result = DateTools.ValidateDate(day, month, year);
//        assertEquals(false, result);
//    }
//
//}
