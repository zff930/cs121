package ir.assignments.two.b;

import ir.assignments.two.a.Frequency;
import ir.assignments.two.a.Utilities;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Counts the total number of words and their frequencies in a text file.
 */

public final class WordFrequencyCounter {
	
	/**
	 * This class should not be instantiated.
	 */
	
	private WordFrequencyCounter() {}
	
	/**
	 * Takes the input list of words and processes it, returning a list
	 * of {@link Frequency}s.
	 * 
	 * This method expects a list of lower-case alphanumeric strings.
	 * If the input list is null, an empty list is returned.
	 * 
	 * There is one frequency in the output list for every 
	 * unique word in the original list. The frequency of each word
	 * is equal to the number of times that word occurs in the original list. 
	 * 
	 * The returned list is ordered by decreasing frequency, with tied words sorted
	 * alphabetically.
	 * 
	 * The original list is not modified.
	 * 
	 * Example:
	 * 
	 * Given the input list of strings 
	 * ["this", "sentence", "repeats", "the", "word", "sentence"]
	 * 
	 * The output list of frequencies should be 
	 * ["sentence:2", "the:1", "this:1", "repeats:1",  "word:1"]
	 *  
	 * @param words A list of words.
	 * @return A list of word frequencies, ordered by decreasing frequency.
	 */
	
	private static Comparator<Frequency> compare = new Comparator<Frequency>() {
        @Override
        public int compare(Frequency x, Frequency y) {
            // Order by frequency (descending)
            if (x.getFrequency() < y.getFrequency()) {
            	return 1;
            }
            else if (x.getFrequency() > y.getFrequency()) {
                return -1;
            }
            // Order by alphabet (ascending)
            else {
                return x.getText().compareTo(y.getText());
            }
        }
    };
    
	public static List<Frequency> computeWordFrequencies(List<String> words) {
		List<Frequency> frequencies = new ArrayList<Frequency>();
		Set<String> item = new LinkedHashSet<String>();
		int occurrences = 0;
		
		if (words.isEmpty())
			return frequencies;
		
		for (String word: words) {
			occurrences = Collections.frequency(words, word);
			if (item.add(word))
				frequencies.add(new Frequency(word, occurrences));
		}
		
		Collections.sort(frequencies, compare);
		
		return frequencies;
	}
	
	/**
	 * Runs the word frequency counter. The input should be the path to a text file.
	 * 
	 * @param args The first element should contain the path to a text file.
	 */
	public static void main(String[] args) {
		File file = new File(args[0]);
		System.out.println(new Date().toString());
		List<String> words = Utilities.tokenizeFile(file);
		List<Frequency> frequencies = computeWordFrequencies(words);
		Utilities.printFrequencies(frequencies);
		System.out.println(new Date().toString());
	}
}
