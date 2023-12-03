package htw.berlin.wi.prog2.parsing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountingInputParser implements ExtendableInputParser {

    /**
     * @param inputLine input line from user
     * @return a list of words free of special characters
     */

    @Override
    public Map<Long, Integer> idsAndCountFromInput(String inputLine, Map<String, Long> keywordsToIds) {

        Map<Long, Integer> idsAndCount = new HashMap<>();
        List<String> wordList = Arrays.asList(inputLine.split("[\\s,\\.]+"));


        for (String keywordWithId : keywordsToIds.keySet()) {
            for (String inputWord : wordList) {
                if (inputWord.equals(keywordWithId)) {
                    Long valueOfKeyString = keywordsToIds.get(keywordWithId);
                    idsAndCount.put(valueOfKeyString, calcAmount(wordList, keywordWithId));
                }
            }
        }

        return idsAndCount;
    }

    /**

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


}
