package Accounts;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class to hash passwords using SHA-256
 */
public class HashClass {

    // CREDIT: Inspired by https://www.baeldung.com/sha-256-hashing-java
    /**
     * stringtosha256 returns a byte[] array.
     * This function creates a string out of the bytes.
     * @param bytes hash bytes
     * @return String of hash
     */
    private String byteToString(byte[] bytes) {
        StringBuilder hexStr = new StringBuilder(2 * bytes.length);
        for (byte i : bytes) {
            String hex = Integer.toHexString(0xff & i);
            if (hex.length() == 1)
                hexStr.append('0');
            hexStr.append(hex);
        }
        return hexStr.toString();
    }

    /**
     * Passwords are sha-256 hashed in the database
     * @param text Cleartext
     * @return Hashed password
     */
    public String stringtosha256(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
            return byteToString(encodedhash);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * JPasswordFields return the input in char[] arrays
     * We turn those into a Sha256 string
     * @param text password in char[] array
     * @return Hashed password String
     */
    public String chartosha256(char[] text) {
        String cleartext = new String(text);

        return stringtosha256(cleartext);
    }
}
