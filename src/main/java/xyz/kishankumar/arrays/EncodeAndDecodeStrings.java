package xyz.kishankumar.arrays;

import java.util.Arrays;
import java.util.List;

public class EncodeAndDecodeStrings {
    private static final String DELIMITER = "::-::";

    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        int n = strs.size();
        if (n < 1)
            return "";
        for (int i = 0; i < n - 1; ++i) {
            sb.append(strs.get(i));
            sb.append(DELIMITER);
        }
        sb.append(strs.get(n - 1));
        return sb.toString();
    }

    public List<String> decode(String str) {
        if (str == null)
            return List.of();
        if (str.isEmpty())
            return List.of("");
        return Arrays.asList(str.split(DELIMITER));
    }

    public static void main(String[] args) {
        EncodeAndDecodeStrings solution = new EncodeAndDecodeStrings();
        List<String> words = List.of("We", "don't", "know", "this", "world", "yet");
        String encoded = solution.encode(words);
        List<String> decoded = solution.decode(encoded);
        for (int i = 0; i < words.size(); ++i) {
            if (!words.get(i).equals(decoded.get(i))) {
                System.out.println("Failed");
                break;
            }
        }

        words = List.of("");
        encoded = solution.encode(words);
        decoded = solution.decode(encoded);
        System.out.println(encoded);
        assert words.size() != decoded.size();
        for (int i = 0; i < words.size(); ++i) {
            if (!words.get(i).equals(decoded.get(i))) {
                System.out.println("Failed");
                break;
            }
        }
    }
}
