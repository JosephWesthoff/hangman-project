import java.util.ArrayList;
import java.util.Scanner;

public class LetterHandler {

    public void mainRunner(String word){
        ArrayList<String> guessedLetters = new ArrayList<>();
        LetterHandler letterHandler = new LetterHandler();
        StringBuilder wordProgress = new StringBuilder();
        wordProgress.append("_".repeat(word.length()));
        System.out.println(wordProgress);

        int count = 1;
        while (count < 7){
             String letterGuess = letterHandler.letterSelect();

             if (!guessedLetters.contains(letterGuess)){
                 letterHandler.letterCheck(word, letterGuess, count);
                 guessedLetters.add(letterGuess);
                 for (int i=0; i<word.length(); i++){
                     if (letterGuess.charAt(0)==word.charAt(i)){
                         wordProgress.replace(i,i+1,letterGuess);
                     }
                 }
                 System.out.println(wordProgress);
                 String wordProg = wordProgress.toString();
                 if (word.equals(wordProg)){
                     System.out.println("Congratulations, you won!");
                     return;
                 }
                 if (!word.contains(letterGuess)){
                     count++;
                 }
             }

             else{
                 System.out.println("You already guessed that.");
             }
        }
    }

    public void letterCheck(String word, String letter, int count){
        letter=letter.toLowerCase();
        word=word.toLowerCase();
        if (word.contains(letter)){
            System.out.println("Good guess!");
        } else{
            switch (count) {
                case 1 -> {
                    System.out.println(" ------ ");
                    System.out.println(" |    |");
                    System.out.println(" |    O");
                    System.out.println(" | ");
                    System.out.println(" | ");
                    System.out.println(" | ");
                    System.out.println("----- ");
                }
                case 2 -> {
                    System.out.println(" ------ ");
                    System.out.println(" |    |");
                    System.out.println(" |    O");
                    System.out.println(" |    |");
                    System.out.println(" | ");
                    System.out.println(" | ");
                    System.out.println("----- ");
                }
                case 3 -> {
                    System.out.println(" ------ ");
                    System.out.println(" |    |");
                    System.out.println(" |    O");
                    System.out.println(" |   -|");
                    System.out.println(" | ");
                    System.out.println(" | ");
                    System.out.println("----- ");
                }
                case 4 -> {
                    System.out.println(" ------ ");
                    System.out.println(" |    |");
                    System.out.println(" |    O");
                    System.out.println(" |   -|-");
                    System.out.println(" | ");
                    System.out.println(" | ");
                    System.out.println("----- ");
                }
                case 5 -> {
                    System.out.println(" ------ ");
                    System.out.println(" |    |");
                    System.out.println(" |    O");
                    System.out.println(" |   -|-");
                    System.out.println(" |   |");
                    System.out.println(" | ");
                    System.out.println("----- ");
                }
                case 6 -> {
                    System.out.println(" ------ ");
                    System.out.println(" |    |");
                    System.out.println(" |    O");
                    System.out.println(" |   -|-");
                    System.out.println(" |   | |");
                    System.out.println(" | ");
                    System.out.println("----- ");
                    System.out.println("Sorry, you lost. Better luck next time!");
                    System.out.println("The correct word was " + word);
                }
            }
        }
    }

    public String letterSelect(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Guess a letter: ");
        String guess = scanner.nextLine();
        if(validate(guess)){
            return guess;
        }
        else{
            System.out.println("Bad input");
            return "";
        }
    }

    public boolean validate (String string){
        if(string.length() != 1){
            return false;
        }
        char testCharacter = Character.toLowerCase(string.charAt(0));
        if(testCharacter>='a'){
            return testCharacter <= 'z';
        }else {
            return false;
        }
    }
}
