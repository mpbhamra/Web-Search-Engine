package advancedComputing.webSearch;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Editdistance {

	private static Map<String, Integer> sortComparator(Map<String, Integer> myMap) {

		/*
		 * Converting the Map to list
		 */
		List<Map.Entry<String, Integer>> listSI = new LinkedList<Map.Entry<String, Integer>>(myMap.entrySet());

		/*
		 * Sorting the List with comparator, to compare the Map values
		 */
		Collections.sort(listSI, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		/*
		 * Converting the sorted map back to a Map
		 */
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Iterator<Map.Entry<String, Integer>> it = listSI.iterator(); it.hasNext();) {
			Map.Entry<String, Integer> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

	public static void printMap(Map<String, Integer> map) {
		for (Map.Entry<String, Integer> entry : map.entrySet()) {

			System.out.println(
					"Matched strings : " + entry.getKey() + " || Distance among Strings : " + entry.getValue());
		}

	}

}
