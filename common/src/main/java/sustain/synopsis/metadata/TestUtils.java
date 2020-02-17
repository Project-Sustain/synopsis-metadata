package sustain.synopsis.metadata;

import java.util.Random;

public class TestUtils {

    //  https://www.baeldung.com/java-random-string
    public static String generateRandomString(int n) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(n)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    public static String generateRandomEmail() {
        return generateRandomString(10)+"@"+generateRandomString(6)+".edu";
    }

}
