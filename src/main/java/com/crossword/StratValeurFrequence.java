package com.crossword;

import java.util.*;

public class StratValeurFrequence implements IChoixValeur {
    @Override
    public List<String> orderValues(ICSP problem, IVariable variable) {
        List<String> values = variable.getDomain();

        // 计算字母频率
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (String word : values) {
            for (char c : word.toCharArray()) {
                frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
            }
        }

        // 根据频率对值排序
        values.sort((word1, word2) -> {
            int score1 = calculateScore(word1, frequencyMap);
            int score2 = calculateScore(word2, frequencyMap);
            return Integer.compare(score1, score2);
        });

        return values;
    }

    private int calculateScore(String word, Map<Character, Integer> frequencyMap) {
        int score = 0;
        for (char c : word.toCharArray()) {
            score += frequencyMap.getOrDefault(c, 0);
        }
        return score;
    }
}
