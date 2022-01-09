import java.util.*;

public class HangmanManager {
    private SortedSet<String> wordSet;
    private SortedSet<Character> lettersGuessed;
    private int remainingGuesses;
    private String pattern;


    public HangmanManager(List<String> dictionary, int length, int max) {
        if (length < 1 || max < 0) {
            throw new IllegalArgumentException
                    ("Check : length: " + length + ", max: " + max);
        }
        remainingGuesses = max;
        wordSet = new TreeSet<>();
        lettersGuessed = new TreeSet<>();
        pattern = "";
        for (int i = 0; i < length; i++) {
            pattern += "-";
        }
        for (String word : dictionary) {
            if (word.length() == length) {
                wordSet.add(word);
            }
        }
    }


    Set<String> words() {
        return wordSet;
    }

    int guessesLeft() {
        return remainingGuesses;
    }

    SortedSet<Character> guesses() {
        return lettersGuessed;
    }


    String pattern() {
        if (wordSet.isEmpty()) {
            throw new IllegalStateException("Word set is empty!");
        }
        String gamePattern = String.valueOf(pattern.charAt(0));
        for (int i = 1; i < pattern.length(); i++) {
            gamePattern += (" " + pattern.charAt(i));
        }
        return gamePattern;
    }


    public TreeMap<String, SortedSet<String>> patternsList(char guess) {
        TreeMap<String, SortedSet<String>> patternSet = new TreeMap<>();
        for (String word : wordSet) {
            String gPattern = "";
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == guess) {
                    gPattern += guess;
                } else {
                    gPattern += pattern.charAt(i);
                }
            }

            if (!patternSet.containsKey(gPattern)) {
                patternSet.put(gPattern, new TreeSet<>());
            }

            patternSet.get(gPattern).add(word);
        }
        return patternSet;
    }
}