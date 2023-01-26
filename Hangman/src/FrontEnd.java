import java.io.IOException;
import java.util.Scanner;

public class FrontEnd {
    public static void main(String[] args) throws IOException {
        FrontEnd frontEnd = new FrontEnd();
        frontEnd.guessHandler(frontEnd.startUp());
    }

    public String startUp() throws IOException {
        WordHandler wordHandler = new WordHandler();
        System.out.println("####### Welcome to my Hangman game #######");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to choose a word (1) or would you like to use a random one (2)? ");
        String choice = scanner.nextLine();
        String word;
        if (choice.equals("1")){
            word = wordHandler.chooseWordManual();
        }
        else if (choice.equals("2")) {
            word = wordHandler.chooseWordRandom();
        }
        else{
            System.out.println("Bad input, a word will be chosen for you");
            word = wordHandler.chooseWordRandom();
        }
        System.out.println(" ------ ");
        System.out.println(" |    |");
        System.out.println(" | ");
        System.out.println(" | ");
        System.out.println(" | ");
        System.out.println(" | ");
        System.out.println("----- ");
        return word;
    }

    public void guessHandler(String word) throws IOException {
        FrontEnd frontEnd = new FrontEnd();
        LetterHandler letterHandler = new LetterHandler();
        letterHandler.mainRunner(word);
        frontEnd.restart();
    }

    public void restart() throws IOException {
        FrontEnd frontEnd = new FrontEnd();
        WordHandler wordHandler = new WordHandler();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to play again? Y or N:");
        String restart = scanner.nextLine();
        if (restart.equalsIgnoreCase("Y")){
            System.out.println("Would you like to choose a word (1) or would you like to use a random one (2)? ");
            String choice = scanner.nextLine();
            if (choice.equals("1")){
                frontEnd.guessHandler(wordHandler.chooseWordManual());
            }
            else if (choice.equals("2")) {
                frontEnd.guessHandler(wordHandler.chooseWordRandom());
            }
            else{
                System.out.println("Bad input, a word will be chosen for you");
                frontEnd.guessHandler(wordHandler.chooseWordRandom());
            }
        }
        else if (restart.equalsIgnoreCase("N")){
            System.out.println("Ok, goodbye");
        }else {
            System.out.println("Bad input");
            frontEnd.restart();
        }
    }
}
