package ir.assignments.two.c;

import ir.assignments.two.a.Frequency;
import ir.assignments.two.a.Utilities;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * Count the total number of 2-grams and their frequencies in a text file.
 */
public final class TwoGramFrequencyCounter {
	/**
	 * This class should not be instantiated.
	 */
	private TwoGramFrequencyCounter() {}
	
	/**
	 * Takes the input list of words and processes it, returning a list
	 * of {@link Frequency}s.
	 * 
	 * This method expects a list of lower-case alphanumeric strings.
	 * If the input list is null, an empty list is returned.
	 * 
	 * There is one frequency in the output list for every 
	 * unique 2-gram in the original list. The frequency of each 2-grams
	 * is equal to the number of times that two-gram occurs in the original list. 
	 * 
	 * The returned list is ordered by decreasing frequency, with tied 2-grams sorted
	 * alphabetically. 
	 * 
	 * 
	 * 
	 * Example:
	 * 
	 * Given the input list of strings 
	 * ["you", "think", "you", "know", "how", "you", "think"]
	 * 
	 * The output list of 2-gram frequencies should be 
	 * ["you think:2", "how you:1", "know how:1", "think you:1", "you know:1"]
	 *  
	 * @param words A list of words.
	 * @return A list of two gram frequencies, ordered by decreasing frequency.
	 */

	private static Comparator<Frequency> compare = new Comparator<Frequency>() {
        @Override
        public int compare(Frequency x, Frequency y) {
            if (x.getFrequency() < y.getFrequency()) {
            	return 1;
            }
            else if (x.getFrequency() > y.getFrequency()) {
                return -1;
            }
            else {
                return x.getText().compareTo(y.getText());
            }
        }
    };
    
	private static List<Frequency> computeTwoGramFrequencies(ArrayList<String> words) {
		List<Frequency> frequencies = new ArrayList<Frequency>();
		HashMap<String, Integer> kv = new HashMap<String, Integer>();
		String str1, str2, twoGram = null;
		int twoGram_count = 0;
		
		if (words.isEmpty())
			return frequencies;
		
		for (int i = 0; i < words.size()-1; ++i) {
            str1 = words.get(i);
            str2 = words.get(i+1);
            twoGram = str1 + " " + str2;
            twoGram_count = kv.containsKey(twoGram) ? kv.get(twoGram) : 0;
            kv.put(twoGram, twoGram_count+1);
		}
		
		for (String k : kv.keySet()) {
			Frequency freq = new Frequency(k, kv.get(k));
			frequencies.add(freq);
		}
		
		Collections.sort(frequencies, compare);
		
		return frequencies;
	};

	/**
	 * Runs the 2-gram counter. The input should be the path to a text file.
	 * 
	 * @param args The first element should contain the path to a text file.
	 */
	public static void main(String[] args) {
		File file = new File(args[0]);
		ArrayList<String> words = Utilities.tokenizeFile(file);
		List<Frequency> frequencies = computeTwoGramFrequencies(words);
		Utilities.printFrequencies(frequencies);
	}
}
