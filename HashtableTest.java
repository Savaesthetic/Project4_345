import java.util.HashMap;
import java.util.Random;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HashtableTest {
    private static Hashtable<String, Integer> table;
    private static HashMap<String, Integer> map;
    private static Random gen;
    private static ArrayList<String> names;
    private static int[] test2Vals = new int[]{5, 1, 2, 8, 12, 0, 23, 45, 11, 68};


    public static void main(String[] args) {
	initialize();
	double score = 0.0;
	score += test1();
	System.out.println("Test 1 Score: " + score + "\n");
	score += test2();
	System.out.println("Total Score: " + score);
    }

    //test 1 tests the functionality of the hashtable
    private static double test1() {
	System.out.println("***** START of Test 1 *****");
	String s = null;
	Integer act = null;
	Integer exp = null;
	double score = 0.0;
	System.out.println("Putting and getting 10 elements...");
	//puts 10 items
	for(int i = 0; i < 10; i++) {
	    s = names.get(i);
	    int num = gen.nextInt(10);
	    table.put(s, num);
	    map.put(s, num);
	}
	//gets the same 10 items
	for(int i = 0; i < 10; i++) {
	    s = names.get(i);
	    act = table.get(s);
	    exp = map.get(s);
	    if(act.equals(exp)) {
		score+=0.5;
	    } else {
		System.out.println("Mismatch in the elements.");
	    }	    
	}//score = 5.0
	System.out.println("Checking update...");
	//checking update
	s = names.get(0);
	table.put(s, 100);
	map.put(s, 100);
	act = table.get(s);
	exp = table.get(s);
	if(act.equals(exp))
	    score += 1.0;//score = 6.0
	else
	    System.out.println("Mismatch after update.");

	System.out.println("Getting an element that is not in the table...");
	//checking for element not in table
	s = names.get(10);
	act = table.get(s);
	if(act == null)
	    score += 1.0;//score = 7.0
	else
	    System.out.println("That element should not be in the table.");

	System.out.println("Deleting an element...");
	//checking remove
	s = names.get(0);
	act = table.remove(s);
	exp = map.remove(s);
	if(act.equals(exp))
	    score += 1.0;//score = 8.0
	else
	    System.out.println("The removed elements do not match.");
	act = table.remove(s);
	if(act == null)
	    score += 1.0;//score = 9.0
	else
	    System.out.println("The element was not successfully removed.");

	//checking size
	System.out.println("Checking size...");
	if(table.size() == map.size())
	    score += 1.0;//score = 10.0
	else
	    System.out.println("The sizes do not match.");

	System.out.println("Adding more elements...");
	int addNum = gen.nextInt(50) + 1;
	for(int i = 0; i < addNum; i++) {
	    int index = gen.nextInt(names.size());
	    s = names.get(index);
	    int num = gen.nextInt(20) + 10;
	    table.put(s, num);
	    map.put(s, num);
	}
	if(table.size() == map.size())
	    score += 1.0;//11.0
	else
	    System.out.println("The table sizes do not match after the inserts.");

	System.out.println("Deleting more elements...");
	int delNum = gen.nextInt(20) + 1;
	boolean passed = true;
	for(int i = 0; i < delNum; i++) {
	    int index = gen.nextInt(names.size());
	    s = names.get(index);
	    act = table.remove(s);
	    exp = map.remove(s);
	    if(act == null && exp == null || act.equals(exp)) {
		passed = passed && true;
	    } else {
		passed = passed && false;
		System.out.println("Mismatch when removing elements.");
	    }
	}
	if(passed)
	    score += 1.0;//score = 12.0
	if(table.size() == map.size())
	    score += 1.0;//score = 13.0
	else {
	    System.out.println("Sizes do not match after removing elements.");
	}

	//checking get
	for(int i = 0; i < 8; i++) {
	    int index = gen.nextInt(names.size());
	    s = names.get(index);
	    act = table.get(s);
	    exp = table.get(s);
	    if(act == null && exp == null || act.equals(exp))
		score += 0.5;
	    else
		System.out.println("Mismatch when calling get.");
	    //score = 17.0
	}
	System.out.println("***** END of Test 1 *****");
	return score;
    }

    //Test 2 tests the underlying array and linear probing.
    private static double test2() {
	System.out.println("***** START of Test 2 *****");
	double score = 0.0;
	Hashtable<Integer, Integer> ht = new Hashtable<Integer, Integer>();
	System.out.println("Adding 5 elements...");
	for(int i = 0; i < 5; i++) {
	    ht.put(test2Vals[i], test2Vals[i]);
	    //printArray(ht.getArray());
	}
	Pair<Integer, Integer>[] act = ht.getArray();
	Pair<Integer, Integer>[] exp = buildTest2Array(1);
	score += compareArrays(act, exp);
	System.out.println("Adding 5 more elements...");
	for(int i = 5; i < test2Vals.length; i++) {
	    ht.put(test2Vals[i], test2Vals[i]);
	    //printArray(ht.getArray());
	}
	act = ht.getArray();
	exp = buildTest2Array(2);
	score += compareArrays(act, exp);
	System.out.println("Removing 7 elements...");
	for(int i = 0; i < 7; i++) {
	    ht.remove(test2Vals[i]);
	    //printArray(ht.getArray());
	}
	act = ht.getArray();
	score += checkDeletions(act);
	System.out.println("Removing 1 more element...");
	ht.remove(test2Vals[7]);
	//printArray(ht.getArray());
	act = ht.getArray();
	exp = buildTest2Array(3);
	score += compareArrays(act, exp);
	System.out.println("***** END of Test 2 *****");
	return score;
    }

    private static double checkDeletions(Pair<Integer,Integer>[] array) {
	if(array.length != 23) {
	    System.out.println("Your array is the wrong size after these deletions. It should still be 23.");
	    return 0.0;
	}
	int[] deleted = new int[]{0, 1, 2, 3, 5, 8, 12};
	for(int i = 0; i < deleted.length; i++) {
	    if(!array[deleted[i]].removed()) {
		System.out.println("You failed to remove an element that should have been removed.");
		return 0.0;
	    }
	}
	return 2.0;
    }
	    
    //sets up the structures for testing
    private static void initialize() {
	table = new Hashtable<String, Integer>();
	map = new HashMap<String, Integer>();
	gen = new Random(System.currentTimeMillis());
	names = new ArrayList<String>();
	getNames("names.txt");
    }

    private static double compareArrays(Pair<Integer, Integer>[] act, Pair<Integer, Integer>[] exp) {
	if(act.length != exp.length) {
	    System.out.println("Your array is not the right size.");
	    return 0.0;
	}
	for(int i = 0; i < act.length; i++) {
	    if(!(act[i] == null && exp[i] == null || act[i].equals(exp[i]))) {
		System.out.println("Your array is not correct.");
		System.out.println("Expected: ");
		printArray(exp);
		System.out.println("\nActual: ");
		printArray(act);
		System.out.println();
		return 0.0;
	    }
	}
	return 2.0;
    }
	    

    //gets names from a file
    private static void getNames(String fn) {
	BufferedReader br;
	try {
	    br = new BufferedReader(new FileReader(fn));
	    String line = br.readLine();
	    while(line != null) {
		String[] split = line.split(",");
		if(split.length >= 1)
		    names.add(split[0]);
		line = br.readLine();
	    }
	    br.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private static void printArray(Pair[] array) {
	System.out.println();
	for(Pair p: array) {
	    if(p == null)
		System.out.print("null |");
	    else
		System.out.print(p.toString() + "|");
	}
    }
	    

    private static Pair<Integer, Integer>[] buildTest2Array(int num) {
	Pair[] array = null;
	if(num == 1) {
	    array = new Pair[11];
	    array[1] = new Pair(1, 1);
	    array[2] = new Pair(2, 2);
	    array[3] = new Pair(12, 12);
	    array[5] = new Pair(5, 5);
	    array[8] = new Pair(8, 8);
	} else if (num == 2) {
	    array = new Pair[23];
	    array[0] = new Pair(0, 0);
	    array[1] = new Pair(1, 1);
	    array[2] = new Pair(2, 2);
	    array[3] = new Pair(23, 23);
	    array[4] = new Pair(68, 68);
	    array[5] = new Pair(5, 5);
	    array[8] = new Pair(8, 8);
	    array[11] = new Pair(11, 11);
	    array[12] = new Pair(12, 12);
	    array[22] = new Pair(45, 45);
	} else if(num == 3) {
	    array = new Pair[11];
	    array[0] = new Pair(11, 11);
	    array[2] = new Pair(68, 68);
	}	    
	return array;
    }
}
	
    
