package utils;

import java.util.Random;

public class CustomRandomStringUtils {
    private static final String GEORGIAN_ALPHABET = "აბგდევზთიკლმნოპჟრსტუფქღყშჩცძწჭხჯჰ";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()_+=<>?/|";

    public static String randomGeorgianAlphabetic(int length) {
        return generateRandomString(GEORGIAN_ALPHABET, length);
    }

    public static String randomSpecialCharacters(int length) {
       return generateRandomString(SPECIAL_CHARACTERS, length);
    }

    private static String generateRandomString(String symbols, int length) {
        Random random = new Random();
        StringBuilder result = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(symbols.length());
            result.append(symbols.charAt(index));
        }

        return result.toString();
    }

}
