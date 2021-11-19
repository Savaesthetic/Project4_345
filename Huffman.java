import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Huffman {
    /***
     *    @param fn: the filename for the text you are compressing
     ***/
    public Huffman(String fn) {
        /* Read in file, for each character add to hashtable, once whole file has been read
        * convert each hashtable element to a one node tree and add to minPQ, construct the tree with minPQ
        */
        Hashtable<Character, Integer> frequency = new Hashtable<Character, Integer>();
        try {
            File input = new File(fn);
            Scanner fileReader = new Scanner(input);
            while (fileReader.hasNextLine()) {
                String word = fileReader.next();
                Integer val;
                for (int i = 0; i < word.length(); i++) {
                    if ((val = frequency.get((Character) word.charAt(i))) == null) {
                        frequency.put((Character) word.charAt(i), 1);
                    } else {
                        frequency.put((Character) word.charAt(i), val + 1);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error opening and reading file.");
            e.printStackTrace();
        }
        Pair<Character, Integer>[] frequencyArr = frequency.getArray();
    }

    /***
     * encode the original text and return the encoded String
     ***/
    public String encode() {
	//TO BE IMPLEMENTED
        return null;
    }


    /***
     * decode the string passed in and return the decoded String
     ***/
    public String decode(String str) {
        return null;
	//TO BE IMPLEMENTED
    }

    private class Tree<T extends Comparable<T>> {
        public Node root;
        public Integer totalFrequency;

        public Tree() {
            // TODO
        }
    }
    private class Node {
        public Character c;
        public Integer frequency;

        public Node(Character c, Integer frequency) {
            this.c = c;
            this.frequency = frequency;
        }
    }
}
