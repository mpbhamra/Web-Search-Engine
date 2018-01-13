
package advancedComputing.webSearch;

import java.util.Random;

public class EditDistAddOn {

	public static int editDistance(String s1, String s2) {
		int length1 = s1.length();
		int length2 = s2.length();

		// length1+1, length2+1, because finally return dp[length1][length2]
		int[][] dp = new int[length1 + 1][length2 + 1];

		for (int i = 0; i <= length1; i++) {
			dp[i][0] = i;
		}

		for (int j = 0; j <= length2; j++) {
			dp[0][j] = j;
		}

		// iterate though, and check last char
		for (int i = 0; i < length1; i++) {
			char c1 = s1.charAt(i);
			for (int j = 0; j < length2; j++) {
				char c2 = s2.charAt(j);

				// if last two chars equal
				if (c1 == c2) {
					// update dp value for +1 length
					dp[i + 1][j + 1] = dp[i][j];
				} else {
					int replace = dp[i][j] + 1;
					int insert = dp[i][j + 1] + 1;
					int delete = dp[i + 1][j] + 1;

					int min = replace > insert ? insert : replace;
					min = delete > min ? min : delete;
					dp[i + 1][j + 1] = min;
				}
			}
		}

		return dp[length1][length2];
	}

	public static void main(String[] args) {
		
		String s1 = "manpreet";
		String s2 = "manpreetsingh";
		int diffCount = editDistance(s1, s2);
		// System.out.println("Difference in distance of " + s1 + "' and '" + s2 + "'
		// is: " + diffCount);
		long val = 0;
		// random strings
		for (int k = 0; k < 100; k++) {

			Random r = new Random();
			String randomStr1 = "";
			String randomStr2 = "";
			int len = 1000;
			// System.out.println(""+k);
			for (int j = 0; j < len; j++) {
				randomStr1 += (char) ('a' + r.nextInt(26));
				randomStr2 += (char) ('a' + r.nextInt(26));
			}
			long startTime = System.currentTimeMillis();
			int dr = editDistance(randomStr1, randomStr2);
			long endTime = System.currentTimeMillis();

			val += (endTime - startTime);
		}
		double avg = val / 100;

		// System.out.println("Average Distance "+ avg);
	}
}
