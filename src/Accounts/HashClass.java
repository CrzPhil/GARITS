package Accounts;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashClass {
    // TODO: Revise whether to implement char[]
/*    private String clearText;
    private char[] clearTextChars;

    // In case String is passed (not recommended as String is stored in memory; cleartext password will be in memory)
    public HashClass(String text) {
        this.clearText = text;
    }

    // passwordField.getPassword returns char[] instead of String for security reasons
    public HashClass(char[] text) {
        this.clearTextChars = text;
    }*/

    // TODO: Inspired by https://www.baeldung.com/sha-256-hashing-java
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

    // Passwords are sha-256 encoded in the database
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

    // Passwords are sha-256 encoded in the database
    public String chartosha256(char[] text) {
        String cleartext = new String(text);

        return stringtosha256(cleartext);
    }
}
