package advancedComputing.webSearch;

import java.io.*;
import java.util.*;

public class DictionaryValidation {
	public Hashtable<String, Data> dictionary() throws IOException {

		// LinkedHashSet<String> lhSet=new LinkedHashSet<String>();
		// ArrayList<String> aldup = new ArrayList<>();
		String line;
		// ArrayList<Data> al = new ArrayList<>();

		Hashtable<String, Data> hashtable = new Hashtable<String, Data>();
		// FileOutputStream f = new FileOutputStream("file.txt");

		File folder = new File("E:\\University\\Semester 3\\Adv Computing Concepts\\Project\\finalProject\\text");

		for (File file : folder.listFiles()) {
			System.out.println(file.getName());
			BufferedReader br = new BufferedReader(new FileReader(file));

			while ((line = br.readLine()) != null) {

				StringTokenizer str = new StringTokenizer(line, ",.:;<>/?[)({}]+-*&%=#@^'!|_$`~");

				while (str.hasMoreTokens()) {
					String str1 = str.nextToken().toLowerCase().replaceAll("\"", "");
					String str2[] = str1.split(" ");

					for (int i = 0; i < str2.length; i++) {
						if (!hashtable.containsKey(str2[i])) {

							Data value = new Data();
							ArrayList<String> arrayList = new ArrayList<String>();

							int numberOfTimes = 1;
							arrayList.add(file.getName());
							value.setNumberOfTimes(numberOfTimes);
							value.setPageName(arrayList);
							hashtable.put(str2[i], value);
						} else {

							Data value = hashtable.get(str2[i]);
							ArrayList<String> arrayList = value.getPageName();
							if (!arrayList.contains(file.getName()))
								arrayList.add(file.getName());
							value.setNumberOfTimes(value.getNumberOfTimes() + 1);
							value.setPageName(arrayList);
							hashtable.put(str2[i], value);
						}
					}
				}
			}

			// System.out.println("for loop:"+file.getName());
		}

		return hashtable;
	}

	public void write(ArrayList<String> aldup) throws FileNotFoundException {
		File filedup = new File("dump.txt");
		FileOutputStream outputStream = new FileOutputStream(filedup);
		PrintStream ps = new PrintStream(outputStream);
		System.setOut(ps);

		for (String alprint : aldup) {
			System.out.println(alprint);
		}

	}

}
