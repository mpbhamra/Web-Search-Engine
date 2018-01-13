package advancedComputing.webSearch;

import java.io.*;
import java.util.*;

public class InvertIdx {

	static LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();

	static ArrayList<String> counterVar = new ArrayList<>();
	static TreeMap<String, Integer> wordCount = new TreeMap<String, Integer>();

	public ArrayList<String> get(Hashtable<String, Data> hashtable, String input) throws IOException {

		ArrayList<String> correct = new ArrayList<>();
		ArrayList<String> arrayList = new ArrayList<String>();
		String inputVal[] = input.split(" ");
		for (int i = 0; i < inputVal.length; i++)
			arrayList.add(inputVal[i]);
		for (String in : arrayList) {

			if (hashtable.containsKey(in)) {
				correct.add(in);
				calculate(in, hashtable);
			} else {

				Hashtable<String, Integer> edit = new Hashtable<>();
				Set set = hashtable.entrySet();
				// Get an iterator
				Iterator i = set.iterator();
				// Display elements
				int editDistance = 101;
				int editDistance2 = 101;
				String wordKey = "";
				while (i.hasNext()) {
					Map.Entry me = (Map.Entry) i.next();

					editDistance = EditDistAddOn.editDistance(in, me.getKey().toString());
					if (editDistance2 > editDistance) {
						editDistance2 = editDistance;
						wordKey = me.getKey().toString();
					}
				}
				correct.add(wordKey);
				calculate(wordKey, hashtable);

			}
		}

		ArrayList<String> wordCount1 = sortByComparator(wordCount);
		wordCount.clear();

		return wordCount1;
	}

	private static void calculate(String in, Hashtable<String, Data> hashtable) {
		Data value = hashtable.get(in);
		int x = 0;

		// ArrayList<PageMap> al = new ArrayList<>();

		// PageMap p = new PageMap();
		for (String pagename : value.getPageName()) {
			if (x > 5) {

				break;
			} else {
				if (wordCount.containsKey(pagename)) {
					int counter = wordCount.get(pagename);
					counter += countStringInFile(in, pagename);
					wordCount.put(pagename, counter);
				} else {
					int countOfWord = countStringInFile(in, pagename);
					StringBuffer sb = new StringBuffer("");
					sb = sb.append(pagename);
					wordCount.put(sb.toString(), countOfWord);
				}
			}

			x++;
		}

	}

	private static ArrayList<String> sortByComparator(Map<String, Integer> myMap) {

		/*
		 * Converting the Map to list
		 */
		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(myMap.entrySet());

		/*
		 * Sorting the List with comparator, to compare the Map values
		 */
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		/*
		 * Converting the sorted map back to a Map
		 */
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();

		ArrayList<String> display = new ArrayList<>();
		for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext();) {
			Map.Entry<String, Integer> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
			System.out.println(entry.getKey());
			display.add("E:\\University\\Semester 3\\Adv Computing Concepts\\Project\\finalProject\\web-pages\\"
					+ entry.getKey().replace(".txt", ".htm"));
		}
		Collections.reverse(display);

		return display;
	}

	public static int countStringInFile(String stringToLookFor, String fileName) {
		int count = 0;
		try {
			FileInputStream fstream = new FileInputStream("text/" + fileName);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				System.out.println("");
				int startIndex = strLine.toLowerCase().indexOf(stringToLookFor);
				while (startIndex != -1) {
					count++;
					startIndex = strLine.indexOf(stringToLookFor, startIndex + stringToLookFor.length());
				}
			}
			in.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		return count;
	}
}

/*
class TotalPageNane {
	private ArrayList<PageMap> al = new ArrayList<>();

	public ArrayList<PageMap> getAl() {
		return al;
	}

	public void setAl(ArrayList<PageMap> al) {
		this.al = al;
		
		
	}
}
*/