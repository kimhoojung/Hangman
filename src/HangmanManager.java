import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class HangmanManager {
    private SortedSet<String> wordSet;
    private SortedSet<Character> lettersGuessed;
    private int remainingGuesses;
    private String pattern;


    public HangmanManager(List<String> dictionary, int length, int max) {
        if (length < 1 || max < 0){
            throw new IllegalArgumentException
                    ("Check : length: " + length + ", max: " + max);
        }
        remainingGuesses = max;
        wordSet = new TreeSet<>();
        lettersGuessed = new TreeSet<>();
        pattern = "";
        for (int i = 0; i < length; i++){
            pattern += "-";
        }
        for (String word : dictionary){
            if (word.length() == length){
                wordSet.add(word);
            }
        }
    }
}
