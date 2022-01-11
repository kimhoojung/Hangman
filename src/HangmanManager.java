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

    public void refreshPattern(TreeMap<String, SortedSet<String>> patternList){
        pattern = "";
        int count = 0;
        for (String patternK : patternList.keySet()){
            if (patternList.get(patternK).size() > count){
                pattern = patternK;
                count = patternList.get(patternK).size();
            }
        }
    }


    public void updWordSet(TreeMap<String, SortedSet<String>> patternList){
        int pSize = 0;
        for (String patternK : patternList.keySet()){
            if (patternList.get(patternK).size() > pSize){
                pSize = patternList.get(patternK).size();
                wordSet = patternList.get(patternK);
            }
        }
    }

    int record(char guess){
        if (remainingGuesses < 1 || wordSet.isEmpty()){
            throw new IllegalStateException("");
        }else if (!wordSet.isEmpty() && lettersGuessed.contains(guess)){
            throw new IllegalArgumentException("");
        }

        TreeMap<String, SortedSet<String>> patternAnalysis = patternsList(guess);
        refreshPattern(patternAnalysis);
        updWordSet(patternAnalysis);

        lettersGuessed.add(guess);

        return guessCalc(guess);
    }


    public int guessCalc(char guess){
        int correctGuesses = 0;
        for (int i = 0; i < pattern.length(); i++){
            if (pattern.charAt(i) == guess){
                correctGuesses++;
            }
        }
        if (correctGuesses == 0){
            remainingGuesses--;
        }
        return correctGuesses;
    }
}