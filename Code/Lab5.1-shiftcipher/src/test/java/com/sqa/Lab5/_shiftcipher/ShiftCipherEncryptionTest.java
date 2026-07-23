package com.sqa.Lab5._shiftcipher;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ShiftCipherEncryptionTest {

    private final ShiftCipher shiftCipher = new ShiftCipher();

    // Encryption Test Cases

    /**
     * Valid Equivalence Classes:
     * TC001 (EC01, EC04) - plainText A-Z, key 0-25
     * TC002 (EC02, EC05) - plainText a-z, key negative/>=26
     * TC003 (EC03, EC04) - plainText mixed case, key 0-25
     */
    @ParameterizedTest(name = "{0}: encrypt(\"{1}\", {2}) = \"{3}\"")	// <-- Annotation Attribute (Display Name Template/Format) ไม่ต้องมีก็ได้ 
	  																	// แต่มันช่วยให้การแสดงผลรายงานผลการทดสอบ (Test Report) ให้ดูง่าย ชัดเจน และตรงกับเอกสาร Test Plan
    @CsvSource({
        "TC001, SQA,  3, VTD",   // EC01, EC04
        "TC002, sqa, -3, PNX",   // EC02, EC05
        "TC003, SqA,  0, SQA"    // EC03, EC04
    })
    void testEncrypt_validEquivalenceClasses(String tcId, String plainText, int key, String expected) {
        assertEquals(expected, shiftCipher.encrypt(plainText, key));
    }

    /**
     * Invalid Equivalence Classes:
     * TC004 (EC06, EC04) - plainText = null
     * TC005 (EC07, EC04) - plainText = "" (empty)
     * TC006 (EC08, EC04) - plainText contains digits
     * TC007 (EC09, EC04) - plainText contains symbols
     * TC008 (EC10, EC04) - plainText contains space
     */
    @ParameterizedTest(name = "{0}: encrypt({1}, {2}) throws IllegalArgumentException")	// <-- Annotation Attribute
    @CsvSource(value = {
        "TC004, NULL,    3",   // EC06, EC04 - null plainText
        "TC005, '',      3",   // EC07, EC04 - empty string
        "TC006, SQA1,    3",   // EC08, EC04 - digits
        "TC007, SQA!,    3",   // EC09, EC04 - symbols
        "TC008, 'SQA A', 3"    // EC10, EC04 - space
    }, nullValues = "NULL")
    void testEncrypt_invalidPlainText(String tcId, String plainText, int key) {
        assertThrows(IllegalArgumentException.class, () -> shiftCipher.encrypt(plainText, key));
    }
}