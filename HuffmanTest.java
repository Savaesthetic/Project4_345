import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HuffmanTest {
    private static int[] lengths = new int[] {95, 1320, 5248, 6595, 10168};
    
    public static void main(String[] args) {
	double score = 0.0;
	for(int i = 1; i <= 5; i++)
	    score += runTest(i);
	System.out.println("Total Score: " + score);
    }

    private static double runTest(int testNum) {
	System.out.println("\n***** Running Test on input " + testNum + ".txt *****\n");
	double score = 0.0;
	String fn = "input" + testNum + ".txt";
	String text = getText(fn);
	Huffman h = new Huffman(fn);
	String encoded = h.encode();
	if(encoded.length() == lengths[testNum-1]) {
	    score+=4.5;
	    System.out.println("Passed encoding test!");
	}
	else {
	    System.out.println("The length of the encoded message does not match the expected length.");
	    System.out.println("Expected: " + lengths[testNum-1]);
	    System.out.println("Actual: " + encoded.length());
	}
	String decoded = h.decode(encoded);
	if(decoded.equals(text)) {
	    score += 4.5;
	    System.out.println("Passed decoding test!");
	}
	else {
	    System.out.println("The decoded message does not match the original text.");
	    System.out.println("Expected: " + text);
	    System.out.println("Actual: " + decoded);
	}
	return score;
    }
     	
    
    private static String getText(String fn) {
	String text = "";
	BufferedReader br;
	try {
	    br = new BufferedReader(new FileReader(fn));
	    String line = br.readLine();
	    while(line != null) {
		text += line;
		line = br.readLine();
	    }
	    br.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return text;
    }
}
