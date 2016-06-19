package ir.assignments.two.a;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A collection of utility methods for text processing.
 */
public class Utilities {
	/**
	 * Reads the input text file and splits it into alphanumeric tokens.
	 * Returns an ArrayList of these tokens, ordered according to their
	 * occurrence in the original text file.
	 * 
	 * Non-alphanumeric characters delineate tokens, and are discarded.
	 *
	 * Words are also normalized to lower case. 
	 * 
	 * Example:
	 * 
	 * Given this input string
	 * "An input string, this is! (or is it?)"
	 * 
	 * The output list of strings should be
	 * ["an", "input", "string", "this", "is", "or", "is", "it"]
	 * 
	 * @param input The file to read in and tokenize.
	 * @return The list of tokens (words) from the input file, ordered by occurrence.
	 */
	
	static ArrayList<String> list = new ArrayList<String>();
	static int total_count = 0;
	static int unique_count = 0;
	static String file_name = "//Users//feifeipapaioio//Desktop//test1.txt";
	
	public static ArrayList<String> tokenizeFile(File input) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(input);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (scanner.hasNext())
			list.add(scanner.next().replaceAll(("[^a-zA-Z0-9]"),"").toLowerCase());
		scanner.close();
		return list;
	}
	
	/**
	 * Takes a list of {@link Frequency}s and prints it to standard out. It also
	 * prints out the total number of items, and the total number of unique items.
	 * 
	 * Example one:
	 * 
	 * Given the input list of word frequencies
	 * ["sentence:2", "the:1", "this:1", "repeats:1",  "word:1"]
	 * 
	 * The following should be printed to standard out
	 * 
	 * Total item count: 6
	 * Unique item count: 5
	 * 
	 * sentence	2
	 * the		1
	 * this		1
	 * repeats	1
	 * word		1
	 * 
	 * 
	 * Example two:
	 * 
	 * Given the input list of 2-gram frequencies
	 * ["you think:2", "how you:1", "know how:1", "think you:1", "you know:1"]
	 * 
	 * The following should be printed to standard out
	 * 
	 * Total 2-gram count: 6
	 * Unique 2-gram count: 5
	 * 
	 * you think	2
	 * how you		1
	 * know how		1
	 * think you	1
	 * you know		1
	 * 
	 * @param frequencies A list of frequencies.
	 */
	
	public static void printFrequencies(List<Frequency> frequencies) {
		if (frequencies != null) {
			for (int i = 0; i < frequencies.size(); ++i) {
				total_count += frequencies.get(i).getFrequency();
			}
			
			System.out.println("\nTotal item count: " + total_count);
			
			for (int i = 0; i < frequencies.size(); ++i) {
				if (frequencies.get(i).getFrequency() == 1)
					unique_count++;
			}
			
			System.out.println("Unique item count: " + unique_count + "\n");
			
			for (int i = 0; i < frequencies.size(); ++i) {
				System.out.println(frequencies.get(i).getText() 
						+ "  " + frequencies.get(i).getFrequency());
			}
		}
	}
}