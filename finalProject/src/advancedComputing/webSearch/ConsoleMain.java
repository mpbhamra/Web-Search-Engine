/*
 * 
 * 
 * THIS IS BASICALLY FOR TESTING
 * 
 * FOR BETTER EXPERIENCE USE SERVLETMAIN.JAVA FILE WHICH PROVIDES USER INTERFACE USING LOCAL TOMCAT SERVER
 * 
 * 
 * 
 * 

 */
package advancedComputing.webSearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class ConsoleMain {
	public static void main(String[] args) throws IOException, InterruptedException {

		DictionaryValidation dictionaryValidation = new DictionaryValidation();
		HTMLtoTEXT jsoup = new HTMLtoTEXT();
		Hashtable<String, Data> hashtableSD = dictionaryValidation.dictionary();
		Scanner scanner = new Scanner(System.in);
		String exit = "";

		System.out.println(
				"\n-------------------------------WELCOME TO OUR SEARCH ENGINE--------------------------------");

		while (!exit.equals("exit")) {

		
			System.out.println(
					"\n------------------------------- ENTER YOUR OPTION ---------------------------------------");
			System.out.println(
					"-------------------------------------------------------------------------------------------");
			
			System.out.println(
					"-----------------------------------1. START SEARCH-----------------------------------------");
			System.out.println(
					"---------------------------------------2. EXIT---------------------------------------------");
			System.out.println(
					"-------------------------------------------------------------------------------------------");

			int val = scanner.nextInt();

			System.out.println("You Entered: " + val);
			switch (val) {
			case 1:
				System.out.print("Enter a String or Word : ");
				String nul1 = scanner.nextLine();
				String input = scanner.nextLine();
				InvertIdx ie = new InvertIdx();
				ie.get(hashtableSD, input);
				break;
			case 2:
				exit = "exit";
				System.out.println(
						"---------------------------------------------------------------------------------------------");
				System.out.println(
						"---------------------------------!!! USE ME AGAIN !!!----------------------------------------");
				System.out.println(
						"----------------------------------------------------------------------------------------------");
				break;
			}

		}
		scanner.close();
	}

}
