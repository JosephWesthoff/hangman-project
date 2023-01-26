import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Stream;

public class WordHandler {
    public String chooseWordRandom() {
        Random num = new Random();
        int randomNum = num.nextInt(370099) + 1;
        String lineRan = null;
        try (Stream<String> lines = Files.lines(Paths.get("src\\words_alpha.txt"))) {
            lineRan = lines.skip(randomNum - 1).findFirst().get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineRan;
    }

    public String chooseWordManual() throws IOException {
        BST tree = new BST();
        Stream<String> lines = Files.lines(Paths.get("src\\words_alpha.txt"));
        String[] linesArray = lines.toArray(String[]::new);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose your word: ");
        String word = scanner.nextLine();

        //Binary Search
//        for (String s : linesArray) {
//            tree.insert(s);
//        }
//        if (tree.search(tree.root, word)) {
//            System.out.println("Good input");
//            return word;
//        }

        //Decrease by constant search
        if(decreaseByConstantValidation(linesArray, word)){
            System.out.println("Good input");
            return word;
        }

        //Brute force search
//        if (bruteForceValidation(word)){
//            System.out.println("Good input");
//            return word;
//        }

        else{
            System.out.println("Typo found");
            char[] chars = new char[word.length()];
            for (int i = 0; i < word.length(); i++) {
                chars[i] = word.charAt(i);
            }
            System.out.println("Typo found. We've autocorrected to a similar word.");
            System.out.println(autoCorrect(linesArray, chars));
            return autoCorrect(linesArray, chars);
        }
    }

    public boolean bruteForceValidation(String word){
        String comparisonWord = null;
        for (int i = 0; i < 370099; i++) {
            try (Stream<String> lines = Files.lines(Paths.get("src\\words_alpha.txt"))) {
                comparisonWord = lines.skip(i).findFirst().get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (word.equals(comparisonWord)) {
                return true;
            }
        }
        return false;
    }

    public static boolean decreaseByConstantValidation(String[] dictionary, String word) {
        int lo = 0, hi = dictionary.length - 1;
        while (lo <= hi) {
            int mid = Math.round((lo + hi) / 2);
            int comp = word.compareToIgnoreCase(dictionary[mid]);

            if (comp == 0) {
                return true;
            }

            if (comp < 0) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return false;
    }

    public static String autoCorrect(String[] dictionary, char[] chars) {
        String suggestion = "";
        int maxVal = 0;
        for (String str : dictionary) {
            int helperMethod = autoCorrectHelperMethod(str.toLowerCase(), chars);
            if (helperMethod > maxVal) {
                maxVal = helperMethod;
                suggestion = str;
            }
        }
        return suggestion;
    }

    public static int autoCorrectHelperMethod(String str, char[] chars) {
        int count = 0;
        for (char c : chars) {
            if (str.contains(String.valueOf(c))) {
                count++;
            }
        }
        return count;
    }

    /*
    Binary Search Tree Classes And Methods
             |
             |
             V
     */
    class Node {
        Node left, right;
        String value;
        Node(String value) {
            this.value = value;
        }
    }

    class BST {
        Node root;
        public void insert(String key) {
            Node node = new Node(key);
            if (root == null) {
                root = node;
                return;
            }
            Node prev = null;
            Node temp = root;
            while (temp != null) {
                if (temp.value.compareToIgnoreCase(key) > 0) {
                    prev = temp;
                    temp = temp.left;
                } else if (temp.value.compareToIgnoreCase(key) < 0) {
                    prev = temp;
                    temp = temp.right;
                }
            }
            if (prev.value.compareToIgnoreCase(key) > 0) {
                prev.left = node;
            } else prev.right = node;
        }

        public boolean search(Node node, String word) {
            if (node == null) {
                return false;
            } else if (node.value.equals(word)) {
                return true;
            } else if (node.value.compareToIgnoreCase(word) > 0) {
                return search(node.left, word);
            }
            return search(node.right, word);
        }
    }
}