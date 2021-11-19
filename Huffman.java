import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Huffman {
    /***
     *    @param fn: the filename for the text you are compressing
     ***/
    public Huffman(String fn) {
        /* Read in file, for each character add to hashtable, once whole file has been read
        * convert hashtable to minPQ, construct the tree with minPQ
        */
        try {
            File input = new File(fn);
            Scanner fileReader = new Scanner(input);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error opening and reading file.");
            e.printStackTrace();
        }
    }

    /***
     * encode the original text and return the encoded String
     ***/
    public String encode() {
	//TO BE IMPLEMENTED
    }


    /***
     * decode the string passed in and return the decoded String
     ***/
    public String decode(String str) {
	//TO BE IMPLEMENTED
    }
}
