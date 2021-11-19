import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Huffman {
    File input;
    Tree encodingTree;
    Hashtable <Character, String> encodings = new Hashtable<Character, String>();
    /***
     *    @param fn: the filename for the text you are compressing
     ***/
    public Huffman(String fn) {
        Hashtable<Character, Integer> frequency = new Hashtable<Character, Integer>();
        try {
            this.input = new File(fn);
            Scanner fileReader = new Scanner(this.input);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                Integer val;
                for (int i = 0; i < line.length(); i++) {
                    if ((val = frequency.get((Character) line.charAt(i))) == null) {
                        frequency.put((Character) line.charAt(i), 1);
                    } else {
                        frequency.put((Character) line.charAt(i), val + 1);
                    }
                }
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error opening and reading file.");
            e.printStackTrace();
        }
        Pair<Character, Integer>[] frequencyArr = frequency.getTable();
        MinPQ<Tree> frequencyQueue = new MinPQ<Tree>(); // Figure this out
        for (Pair<Character, Integer> element : frequencyArr) {
            frequencyQueue.insert(new Tree(element.key(), element.value()));
        }
        while (frequencyQueue.size() > 1) {
            try {
                Tree smaller = frequencyQueue.delMin();
                Tree small = frequencyQueue.delMin();
                Tree combinedTree = new Tree(small, smaller);
                frequencyQueue.insert(combinedTree);
            } catch (EmptyQueueException e) {
                System.out.println("Error in MinPQ class with remove method. Doesn't correctly handle empty remove.");
                e.printStackTrace();
            }
        }
        try {
            encodingTree = frequencyQueue.delMin();
        } catch (EmptyQueueException e) {
            System.out.println("Error in MinPQ class with remove method. Doesn't correctly handle empty remove.");
            e.printStackTrace();
        }

        buildEncodings(encodingTree.root, "");
    }

    /***
     * encode the original text and return the encoded String
     ***/
    public String encode() {
        String encodedFile = "";
        try {
            Scanner fileReader = new Scanner(this.input);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                for (int i = 0; i < line.length(); i++) {
                    encodedFile += encodings.get((Character) line.charAt(i));
                }
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error opening and reading file.");
            e.printStackTrace();
        }
        return encodedFile;
    }


    /***
     * decode the string passed in and return the decoded String
     ***/
    public String decode(String str) {
        StringBuilder decoded = new StringBuilder();
        Character c;
        while ((c = buildDecoding(encodingTree.root, str)) != null) {
            decoded.append(c);
            str = str.replaceFirst(encodings.get(c), "");
        }
        return decoded.toString();
    }

    public Character buildDecoding(Tree.Node root, String encoded) {
        if (encoded.equals("")) {
            if (root.leftChild == null && root.rightChild == null) {
                return root.c;
            }
            return null;
        }
        if (root.leftChild == null && root.rightChild == null) {
            return root.c;
        }
        if (encoded.charAt(0) == '0') {
            return buildDecoding(root.leftChild, encoded.substring(1));
        } else {
            return buildDecoding(root.rightChild, encoded.substring(1));
        }
    }

    public void buildEncodings(Tree.Node root, String soFar) {
        if (root == null) {
            return;
        }
        if (root.leftChild == null && root.rightChild == null) {
            encodings.put(root.c, soFar);
            return;
        }
        if (root.leftChild != null) {
            buildEncodings(root.leftChild, soFar + "0");
        }
        if (root.rightChild != null) {
            buildEncodings(root.rightChild, soFar + "1");
        }
    }

    private class Tree implements Comparable<Tree> {
        public Node root;
        public Integer totalFrequency;

        public Tree(Character c, Integer frequency) {
            this.root = new Node(c, frequency);
            this.totalFrequency = frequency;
        }

        public Tree (Tree small, Tree smaller) {
            this.totalFrequency = small.totalFrequency + smaller.totalFrequency;
            root = new Node(smaller.root, small.root, totalFrequency);
        }

        @Override
        public int compareTo(Tree otherTree) {
            return this.totalFrequency.compareTo(otherTree.totalFrequency);
        }

        private class Node {
            public Character c;
            public Integer frequency;
            public Node leftChild;
            public Node rightChild;
    
            public Node(Character c, Integer frequency) {
                this.c = c;
                this.frequency = frequency;
                this.leftChild = null;
                this.rightChild = null;
            }

            public Node(Node leftChild, Node rightChild, Integer frequency) {
                this.leftChild = leftChild;
                this.rightChild = rightChild;
                this.frequency = frequency;
            }
        }
    }
}
