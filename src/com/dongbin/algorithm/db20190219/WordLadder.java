package com.dongbin.algorithm.db20190219;

import java.util.List;
import java.util.Map;

public class WordLadder {

    public static List<List<String>> findLadder(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() < 3) {
            return null;
        }
        wordList.add(beginWord);
        Map<String, List<String>> nexts = getNexts(wordList);
        return null;
    }

    private static Map<String, List<String>> getNexts(List<String> wordList) {
        return null;
    }
}
