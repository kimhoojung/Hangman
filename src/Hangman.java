import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hangman {
    public static final String DICTIONARY_FILE = "C:\\Users\\김호정\\Documents\\Hangman\\src\\dictionary.txt";
    public static final boolean SHOW_COUNT = false;

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Welcome to the cse143 hangman game.");
        System.out.println();


        Scanner input = new Scanner(new File(DICTIONARY_FILE));
        List<String> dictionary = new ArrayList<>();
        while (input.hasNext()) {
            dictionary.add(input.next().toLowerCase());
        }


        Scanner console = new Scanner(System.in);
        System.out.print("What length word do you want to use? ");
        int length = console.nextInt();
        System.out.print("How many wrong answers allowed? ");
        int max = console.nextInt();
        System.out.println();
    }
}
