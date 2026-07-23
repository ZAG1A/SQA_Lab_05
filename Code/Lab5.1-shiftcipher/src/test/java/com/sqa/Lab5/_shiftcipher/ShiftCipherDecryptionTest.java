package com.sqa.Lab5._shiftcipher;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ShiftCipherDecryptionTest {

    private final ShiftCipher shiftCipher = new ShiftCipher();

    // Decryption Test Cases

    /**
     * Valid Equivalence Classes:
     * TC001 (EC01, EC04) - cipherText A-Z, key 0-25
     * TC002 (EC02, EC05) - cipherText a-z, key negative/>=26
     * TC003 (EC03, EC04) - cipherText mixed case, key 0-25
     */
    @ParameterizedTest(name = "{0}: decrypt(\"{1}\", {2}) = \"{3}\"") // <-- Annotation Attribute (Display Name Template/Format) ไม่ต้องมีก็ได้ 
    																  // แต่มันช่วยให้การแสดงผลรายงานผลการทดสอบ (Test Report) ให้ดูง่าย ชัดเจน และตรงกับเอกสาร Test Plan
    @CsvSource({
        "TC001, VTD,  3, SQA",   // EC01, EC04
        "TC002, pnx, -3, SQA",   // EC02, EC05
        "TC003, SqA,  0, SQA"    // EC03, EC04
    })
    void testDecrypt_validEquivalenceClasses(String tcId, String cipherText, int key, String expected) {
        assertEquals(expected, shiftCipher.decrypt(cipherText, key));
    }

    /**
     * Invalid Equivalence Classes:
     * TC004 (EC06, EC04) - cipherText = null
     * TC005 (EC07, EC04) - cipherText = "" (empty)
     * TC006 (EC08, EC04) - cipherText contains digits
     * TC007 (EC09, EC04) - cipherText contains symbols
     * TC008 (EC10, EC04) - cipherText contains space
     */
    @ParameterizedTest(name = "{0}: decrypt({1}, {2}) throws IllegalArgumentException") // <-- Annotation Attribute 
    @CsvSource(value = {
        "TC004, NULL,    3",   // EC06, EC04 - null cipherText
        "TC005, '',      3",   // EC07, EC04 - empty string
        "TC006, SQA1,    3",   // EC08, EC04 - digits
        "TC007, SQA!,    3",   // EC09, EC04 - symbols
        "TC008, 'SQA A', 3"    // EC10, EC04 - space
    }, nullValues = "NULL")
    void testDecrypt_invalidCipherText(String tcId, String cipherText, int key) {
        assertThrows(IllegalArgumentException.class, () -> shiftCipher.decrypt(cipherText, key));
    }
}