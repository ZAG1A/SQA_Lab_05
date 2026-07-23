package com.sqa.Lab5._shiftcipher;

/**
 * CP353201 Software Quality Assurance (1/2569)
 * Lab#5.1 – Equivalence class testing
 * Instructor: Asst.Prof. Chitsutha Soomlek
 * 
 * ShiftCipher Class
 * Encryption: ek(x) = (x + k) mod 26
 * Decryption: dk(y) = (y - k) mod 26
 */
public class ShiftCipher {
    
    private static final int ALPHABET_SIZE = 26;
    private static final char BASE_CHAR = 'A';
    
    /**
     * Encryption
     * @param plainText = A-Z without space
     * @param key must be an integer
     * @return Cipher text = A-Z without space
     * @throws IllegalArgumentException when the input is not A-Z or a space
     */
    public String encrypt(String plainText, int key) {
        validateInput(plainText);
        
        int normalizedKey = normalizeKey(key);
        
        StringBuilder cipherText = new StringBuilder();
        String upperText = plainText.toUpperCase();
        
        for (char c : upperText.toCharArray()) {
            int x = c - BASE_CHAR;
            int y = (x + normalizedKey) % ALPHABET_SIZE;
            char encryptedChar = (char) (y + BASE_CHAR);
            cipherText.append(encryptedChar);
        }
        
        return cipherText.toString();
    }
    
    /**
     * Decryption
     * @param cipherText = A-Z without space
     * @param  key must be an integer
     * @return Plain text = A-Z without space
     * @throws IllegalArgumentException when the input is not A-Z or a space
     */
    public String decrypt(String cipherText, int key) {
        validateInput(cipherText);
        
        int normalizedKey = normalizeKey(key);
        
        StringBuilder plainText = new StringBuilder();
        String upperText = cipherText.toUpperCase();
        
        for (char c : upperText.toCharArray()) {
            int y = c - BASE_CHAR;
            int x = ((y - normalizedKey) % ALPHABET_SIZE + ALPHABET_SIZE) % ALPHABET_SIZE;
            char decryptedChar = (char) (x + BASE_CHAR);
            plainText.append(decryptedChar);
        }
        
        return plainText.toString();
    }
    
    /**
     * Verify the input (A-Z only)
     * @param text = input to be checked
     * @throws IllegalArgumentException when the input is not A-Z or a space
     */
    private void validateInput(String text) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Empty string is not accepted!!");
        }
        
        String upperText = text.toUpperCase();
        for (char c : upperText.toCharArray()) {
            if (c < 'A' || c > 'Z') {
                throw new IllegalArgumentException(
                    "The input message must be A-Z without a space: '" + c + "'"
                );
            }
        }
    }
    
    /**
     * Normalize key to Z26 = 0-25 to accept negative key and key >= 26
     * @param key = the original key
     * @return normalized key (0-25)
     */
    private int normalizeKey(int key) {
        return ((key % ALPHABET_SIZE) + ALPHABET_SIZE) % ALPHABET_SIZE;
    }
}