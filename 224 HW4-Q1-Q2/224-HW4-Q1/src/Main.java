//-----------------------------------------------------------------------------------------------------------------------------------
// Title: TrieNode class
// Author: Begüm Þara Ünal - Giray Berk Kuþhan
// ID: 10096206194 - 10889878942
// Section: 4
// Assignment: 4 - Q1

import java.util.*;

public class Main {

	public static class TrieNode {
		private boolean wordsEnd;
		private int control;
		private final Map<Character, TrieNode> children = new HashMap<>();
		/*
		 * indicates that the children variable is a final variable and cannot be
		 * reassigned once initialized This part specifies the type of the children
		 * variable. It is a map that associates characters (Character type) with trie
		 * nodes (TrieNode type).
		 */

		public Map<Character, TrieNode> getChildren() {
			return children;
		}
		/*
		 * initializes the children variable as a new instance of a HashMap object. The
		 * HashMap is used to store the mappings between characters and trie nodes.
		 */

		public void setCount(int count) {
			this.control = count;
		}

		public boolean checkWordsEnd() {
			return wordsEnd;
		}

		public int getCount() {
			return control;
		}

		public void setWordsEnd(boolean wordsEnd) {
			this.wordsEnd = wordsEnd;
		}

	}

	public static class Trie {

		private final List<String> insOrderWrds = new ArrayList<>();
		/*
		 * declares a private final variable named insOrderWrds and initializes it as an
		 * empty ArrayList of Strings. It is used to store the words in the order they
		 * were inserted into the Trie.
		 */
		private final Map<String, Integer> pFixCmap = new HashMap<>();
		/*
		 * declares a private final variable named pFixCmap and initializes it as an
		 * empty HashMap that maps Strings to Integers. It is used to keep track of the
		 * count of each prefix in the Trie.
		 */
		private final TrieNode rootAsTrie;
		/*
		 * declares a private final variable named rootAsTrie of type TrieNode. It
		 * represents the root node of the Trie.
		 */

		public Trie() {
			rootAsTrie = new TrieNode();
		}
		/*
		 * This is the constructor of the Trie class. It initializes the rootAsTrie
		 * variable by creating a new instance of TrieNode and assigning it to
		 * rootAsTrie. This ensures that every Trie object has its own root node.
		 */

		public void add(String word) {
			TrieNode crntNode = rootAsTrie;
			/*
			 * initializes a TrieNode variable crntNode with the value of rootAsTrie, which
			 * represents the root node of the Trie.
			 */
			insOrderWrds.add(word);
			/*
			 * adds the word to the insOrderWrds list, which keeps track of the words in the
			 * order they are inserted into the Trie.
			 */

			for (char l : word.toCharArray()) {
				TrieNode nd = crntNode.getChildren().get(l); // Node to new object
				if (nd == null) {
					nd = new TrieNode();
					crntNode.getChildren().put(l, nd);
				}

				crntNode = nd;
				crntNode.setCount(crntNode.getCount() + 1);
			}
			/*-----------------------------------------------------------------------------
			 * retrieves the child node nd corresponding to the character l from the current
			 * node's children. If the child node nd is null, it means the character is not
			 * present as a child node, so a new TrieNode is created and added as a child of
			 * the current node with the character l. The crntNode is updated to nd, and the
			 * count of crntNode is incremented by 1 to keep track of the number of times
			 * the node has been visited during insertion.
			 * ----------------------------------------------------------------------------
			 */
			crntNode.setWordsEnd(true);
			/*
			 * marks the current node crntNode as the end of a word by setting its wordsEnd
			 * field to true.
			 */
			crntNode = rootAsTrie;
			int i = 0;
			while (i < word.length()) {
				crntNode = crntNode.getChildren().get(word.charAt(i));
				pFixCmap.put(word.substring(0, i + 1), crntNode.getCount());
				i++;
			}
			/*-------------------------------------------------------------------------
			 * adds an entry to the pFixCmap map, where the key is the substring of word
			 * from index 0 to i+1, and the value is the count of the current node crntNode.
			 * This is used to store the counts of each prefix in the Trie.
			 * --------------------------------------------------------------------------
			 */

		}

		public boolean lookUp(String word) {
			TrieNode crntNode = rootAsTrie;
			char[] letters = word.toCharArray();
			int index = 0;
			/*-----------------------------------------------------------------------------
			 * initializes a TrieNode variable crntNode with the value of rootAsTrie, which
			 * represents the root node of the Trie. and retrieves the character at the
			 * current index in the letters array.
			 * ----------------------------------------------------------------------------
			 */
			while (index < letters.length) {
				char l = letters[index];
				TrieNode node = crntNode.getChildren().get(l);
				if (node == null) {
					return false;
				}
				crntNode = node;
				index++;
			}
			/*----------------------------------------------------------------------------
			 * retrieves the child node node corresponding to the character l from the
			 * current node's children. If the child node node is null, it means the
			 * character is not present as a child node, so the method returns false. The
			 * crntNode is updated to node, and the index is incremented to move to the next
			 * character.
			 * ---------------------------------------------------------------------------
			 */

			return crntNode.checkWordsEnd();
			/*
			 * returns the value of the wordsEnd field of the current node crntNode. If it
			 * is true, it means the given word exists in the Trie; otherwise, it does not
			 * exist.
			 */
		}

		public void PrefixCounter() {
			for (String w : insOrderWrds) {
				System.out.print((pFixCmap.get(w) - 1) + " ");
			}
			System.out.println();
		}

		/*------------------------------------------------------------------------------
		 * Iterate over each word w in the insOrderWrds list, which contains the words
		 * in the order they were inserted into the Trie.
		 * 
		 * For each word w, retrieve its corresponding count from the pFixCmap map using
		 * pFixCmap.get(w).
		 * 
		 * Subtract 1 from the retrieved count, as the current word itself is not
		 * considered a prefix of itself.
		 * 
		 * Print the modified count for each word, separated by a space, using
		 * System.out.print((pFixCmap.get(w) - 1) + " ").
		 * 
		 * After printing all the counts, move to the next line using
		 * System.out.println() to create a new line in the output.
		 * ----------------------------------------------------------------------------
		 */
		public void reverseFind(String suffix) {
			LinkedList<String> results = new LinkedList<>();
			for (String word : insOrderWrds) {
				if (word.endsWith(suffix)) {
					results.add(word);
				}
			}
			if (results.isEmpty()) {
				System.out.println("No result");
			} else {
				/*
				 * If the results list is not empty, sort the list in lexicographic order using
				 * Collections.sort(results).
				 */
				Collections.sort(results);
				Iterator<String> iterator = results.iterator();
				if (iterator.hasNext()) {
					do {
						String result = iterator.next();
						System.out.println(result);
					} while (iterator.hasNext());
				}
			}
		}
		/*
		 * searches for words that end with a specified suffix and prints them in
		 * lexicographic order. If no matching words are found, it prints "No result".
		 */

	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Trie trie = new Trie();
		int count = scanner.nextInt();
		scanner.nextLine();

		int i = 0;
		while (i < count) {
			trie.add(scanner.nextLine());
			i++;
		}
		/*
		 * Create a Trie object to store the words. 
		 * Read the number of words to be added
		 * from the input. Use a while loop to add each word to the Trie.
		 */

		while (scanner.hasNextInt()) {
			int preference = scanner.nextInt();
			scanner.nextLine();
			System.out.println("Choose the function you want to use:");
			System.out.println("1) Search");
			System.out.println("2) Count Prefix");
			System.out.println("3) Find Reverse");

			switch (preference) {
			case 1:
				System.out.println(trie.lookUp(scanner.nextLine()));
				break;
			case 2:
				trie.PrefixCounter();
				break;
			case 3:
				trie.reverseFind(scanner.nextLine());
				break;
			default:
				System.out.println("Invalid preference! Please choose 1, 2, or 3.");
				break;
			}
		}
		/*-----------------------------------------------------------------------------
		 * Based on the user preference, execute one of the following operations:
		 * 
		 * If the preference is 1, perform a search operation using trie.lookUp. If the
		 * preference is 2, perform a count prefix operation using trie.PrefixCounter.
		 * If the preference is 3, perform a find reverse operation using
		 * trie.reverseFind. If the preference is invalid, print an error message.
		 * ----------------------------------------------------------------------------
		 */

		scanner.close();
	}
}
