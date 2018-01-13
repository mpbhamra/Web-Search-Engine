
package advancedComputing.webSearch;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
//import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.*;
import org.jsoup.nodes.Document;

public class HTMLtoTEXT {

	public static void JSoupParsing(String urlFile, String destinationFolder, String Txt_filename) throws IOException {

		Document doc;
		String htmlString = "";
		Scanner scannerObj = null;

		File Input_file = new File(urlFile);

		if (Input_file.isFile()) {

			scannerObj = new Scanner(Input_file);

			while (scannerObj.hasNextLine()) {

				htmlString += scannerObj.nextLine();
			}

		}

		scannerObj.close();

		doc = Jsoup.parse(htmlString);

		String text = doc.text();

		String Txt_Filename = "E:\\University\\Semester 3\\Adv Computing Concepts\\Project\\finalProject\\text"
				+ Txt_filename + ".txt";

		PrintWriter pWriter = new PrintWriter(Txt_Filename);

		pWriter.println(text);
		pWriter.close();

	}
}
