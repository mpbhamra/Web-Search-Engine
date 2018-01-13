package advancedComputing.webSearch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;

/**
 * Servlet implementation class ServletMain
 */
@WebServlet("/ServletMain")
public class ServletMain extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletMain() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doOptions(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, OPTIONS, DELETE");
		response.addHeader("Access-Control-Allow-Headers", "Content-Type");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String queryString = request.getParameter("query");

		File wenPageFolder = new File(
				"E:\\University\\Semester 3\\Adv Computing Concepts\\Project\\finalProject\\web-pages");

		File[] listOfHTMLFiles = wenPageFolder.listFiles();

		System.out.println(listOfHTMLFiles.length);

		for (int i = 0; i < listOfHTMLFiles.length; i++) {
			String source = "E:\\University\\Semester 3\\Adv Computing Concepts\\Project\\finalProject\\web-pages\\"
					+ listOfHTMLFiles[i].getName();

			String destination = "E:\\University\\Semester 3\\Adv Computing Concepts\\Project\\finalProject\\text\\"
					+ listOfHTMLFiles[i].getName().replace(".htm", ".txt");

			File input = new File(source);

			File output = new File(destination);

			if (!output.exists()) {
				output.createNewFile();

				String doc = Jsoup.parse(input, "UTF-8").text().toString(); // this line is responsible for converting
																			// html to text file

				FileWriter wr = new FileWriter(output);

				wr.write(doc);

				wr.close();

			}
		}
		DictionaryValidation dictionaryValidation = new DictionaryValidation();
		Hashtable<String, Data> hashtableSD = dictionaryValidation.dictionary();
		ArrayList<String> list = new ArrayList<String>();

		try {
			InvertIdx ie = new InvertIdx();
			list = ie.get(hashtableSD, queryString);

		} catch (IOException e) {
			e.printStackTrace();
		}
		response.setContentType("application/json; charset=UTF-8");

		JSONObject JObject = new JSONObject();
		JObject.put("status", "200");
		JObject.put("result", list);
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.getWriter().write(JObject.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
