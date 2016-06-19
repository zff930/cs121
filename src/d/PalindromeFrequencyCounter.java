package ir.assignments.two.d;

import ir.assignments.two.a.Frequency;
import ir.assignments.two.a.Utilities;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class PalindromeFrequencyCounter {
	/**
	 * This class should not be instantiated.
	 */
	private PalindromeFrequencyCounter() {}
	
	/**
	 * Takes the input list of words and processes it, returning a list
	 * of {@link Frequency}s.
	 * 
	 * This method expects a list of lower-case alphanumeric strings.
	 * If the input list is null, an empty list is returned.
	 * 
	 * There is one frequency in the output list for every 
	 * unique palindrome found in the original list. The frequency of each palindrome
	 * is equal to the number of times that palindrome occurs in the original list.
	 * 
	 * Palindromes can span sequential words in the input list.
	 * 
	 * The returned list is ordered by decreasing size, with tied palindromes sorted
	 * by frequency and further tied palindromes sorted alphabetically. 
	 * 
	 * The original list is not modified.
	 * 
	 * Example:
	 * 
	 * Given the input list of strings 
	 * ["do", "geese", "see", "god", "abba", "bat", "tab"]
	 * 
	 * The output list of palindromes should be 
	 * ["do geese see god:1", "bat tab:1", "abba:1"]
	 *  
	 * @param words A list of words.
	 * @return A list of palindrome frequencies, ordered by decreasing frequency.
	 */
	
	public static boolean isPalindrome(String str) {
		int left = 0;
	    int right = str.length() -1;

	    while(left < right) {
	      if(str.charAt(left) != str.charAt(right))
	        return false;
		  left ++;
		  right --;
	    }
	    return true;
	}
	
	private static Comparator<Frequency> compare = new Comparator<Frequency>() {
		@Override
		public int compare(Frequency x, Frequency y) {
			if (x.getText().length() < y.getText().length())
				return 1;
			else if (x.getText().length() > y.getText().length())
				return -1;
			else {
				if (x.getFrequency() < y.getFrequency())
					return -1;
				else if (x.getFrequency() > y.getFrequency())
					return 1;
				else
					return x.getText().compareTo(y.getText());
			}
		}
	};
	
	private static List<Frequency> computePalindromeFrequencies(ArrayList<String> words) {
		List<Frequency> frequencies = new ArrayList<Frequency>();
		HashMap<String, Integer> kv = new HashMap<String, Integer>();
		int currentFreq = 0;
		
		if (words.isEmpty())
			return frequencies;
		
		for (int i = 0; i < words.size(); i++) {
			String currentWords = "";
			for (int j = i; j < words.size(); j++) {
				currentWords += words.get(j);
				if (isPalindrome(currentWords)) {
					currentFreq = kv.containsKey(currentWords) ? kv.get(currentWords) : 0;
					kv.put(currentWords, currentFreq + 1);
				}
			}
		}

		for (String k : kv.keySet()) {
			Frequency freq = new Frequency(k, kv.get(k));
			frequencies.add(freq);
		}

		Collections.sort(frequencies, compare);

		return frequencies;
	}
	
	/**
	 * Runs the 2-gram counter. The input should be the path to a text file.
	 * 
	 * @param args The first element should contain the path to a text file.
	 */
	public static void main(String[] args) {
		File file = new File(args[0]);
		ArrayList<String> words = Utilities.tokenizeFile(file);
		List<Frequency> frequencies = computePalindromeFrequencies(words);
		Utilities.printFrequencies(frequencies);
	}
}
