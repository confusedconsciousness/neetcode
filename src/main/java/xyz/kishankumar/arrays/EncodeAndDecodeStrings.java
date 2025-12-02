package xyz.kishankumar.arrays;

import java.util.ArrayList;
import java.util.List;

public class EncodeAndDecodeStrings {
    // let's put a constraint that you are only allowed to use a single character,
    // delimiter that would make this problem more interesting
    private static final char DELIMITER = '#';

    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        int n = strs.size();
        // the array is empty: []
        if (n < 1)
            return null;
        for (String str : strs) {
            // we will encode using the below
            // lengthOfWord + delimiter + word
            sb.append(str.length());
            sb.append(DELIMITER);
            sb.append(str);
        }
        return sb.toString();
    }

    public List<String> decode(String str) {
        if (str == null)
            return List.of(); // empty case
        int n = str.length();
        List<String> words = new ArrayList<>();
        // now we will first try to reach to the first delimiter
        int l = 0, r = 0; // we will use two pointer approach, the r will keep on incrementing till it reaches a
        // delimiter
        int wordLen = 0;
        // there will be case where the word itself will be delimiter
        while (r < n) {
            if (str.charAt(r) == DELIMITER) {
                wordLen = Integer.parseInt(str.substring(l, r));
                StringBuilder sb = new StringBuilder();
                for (int c = r + 1; c < r + 1 + wordLen; ++c) {
                    sb.append(str.charAt(c));
                }
                words.add(sb.toString());
                l = r + 1 + wordLen;
                r = l;
            } else {
                r += 1;
            }
        }
        return words;
    }

    public static void main(String[] args) {
        EncodeAndDecodeStrings solution = new EncodeAndDecodeStrings();

        // TEST CASE 1
        List<String> words = List.of("We", "don't", "know", "this", "world", "yet");
        // the whole solution will fail if we choose a word that is a delimiter. other than that the above solution
        // will work
        String encoded = solution.encode(words);
        System.out.println(encoded);
        List<String> decoded = solution.decode(encoded);
        for (int i = 0; i < words.size(); ++i) {
            if (!words.get(i).equals(decoded.get(i))) {
                System.out.println("Failed");
                break;
            }
        }

        // TEST CASE 2 (this will fail if we simply rely on delimiters)
        // - a more rigorous test cases might be to only allow one word delimiter so
        words = List.of("We", "don't", "know", "this", "world", "yet", "3", "#");
        encoded = solution.encode(words);
        decoded = solution.decode(encoded);
        System.out.println(words.size() == decoded.size() ? "PASSED" : "FAILED");
    }
}
