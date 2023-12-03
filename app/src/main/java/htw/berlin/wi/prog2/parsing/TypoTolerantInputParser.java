package htw.berlin.wi.prog2.parsing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypoTolerantInputParser implements ExtendableInputParser {


    /**
     *
     * @param wordList
     * @param keywordsToIds
     * @return a list of words free of misspoken words
     */

    public List<String> correctList (List<String> wordList, Map<String, Long> keywordsToIds) {
        List<String> correctedList = wordList;

        for (String keywordWithId : keywordsToIds.keySet()) {
            for (String inputWord : wordList) {
                if (calculateLevenshteinDistance(inputWord, keywordWithId) < 3) {
                    correctedList.set(wordList.indexOf(inputWord), keywordWithId); // Von Copilot geschrieben
                }
            }
        }
        return correctedList;
    }


    @Override
    public Map<Long, Integer> idsAndCountFromInput(String inputLine, Map<String, Long> keywordsToIds) {

        // for schleife bei der jedes wort aus der Liste mit jedem Wort aus der Map verglichen wird und wenn die Levenshtein Distanz kleiner als 3 ist wird das Wort mit dem Wort aus der Map ersetzt

        Map<Long, Integer> idsAndCount = new HashMap<>();
        List<String> wordList = Arrays.asList(inputLine.split("[\\s,\\.]+"));
        List<String> correctList = correctList(wordList, keywordsToIds);


        for (String keywordWithId : keywordsToIds.keySet()) {
            for (String inputWord : correctList) {
                if (inputWord.equals(keywordWithId)) {
                    Long valueOfKeyString = keywordsToIds.get(keywordWithId);
                    idsAndCount.put(valueOfKeyString, calcAmount(correctList, keywordWithId));
                }
            }
        }

        return idsAndCount;
    }

    /**
     *
     * @param wordList
     * @param keywordWithId
     * @return amount of times the ingredient name occurs in the input line
     */

    public int calcAmount (List<String> wordList, String keywordWithId) {
        int counter = 0;

        for (int i = 0; i < wordList.size(); i++) {
            if (wordList.get(i).equals(keywordWithId)) {
                counter++;
            }
        }

        return counter;

    }

    /**
     * Calculates the Levenshtein distance between two strings.
     * @param s1
     * @param s2
     * @return the Levenshtein distance between s1 and s2
     */

    public static int calculateLevenshteinDistance(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    int substitutionCost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                    dp[i][j] = min(
                            dp[i - 1][j] + 1,           // Deletion
                            dp[i][j - 1] + 1,           // Insertion
                            dp[i - 1][j - 1] + substitutionCost  // Substitution
                    );
                }
            }
        }

        return dp[s1.length()][s2.length()];
    }

    /**
     * Returns the minimum of three integers.
     * @param a
     * @param b
     * @param c
     * @return the minimum of a, b and c
     */
    private static int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }


}
