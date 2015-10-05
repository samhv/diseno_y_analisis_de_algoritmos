package algoritmos;

public class KMP extends TextSearcher {

	public KMP() {

	}

	void computeLPS(String pattern, int[] lps) {
		int currMaxLPS = 0;
		lps[0] = 0;

		for (int i = 1; i < pattern.length();) {
			numberOfComparations++;
			numberOfComparations++;

			if (pattern.charAt(i) == pattern.charAt(currMaxLPS)) {
				currMaxLPS++;
				lps[i] = currMaxLPS;
				i++;
			} else {
				if (currMaxLPS != 0) {
					// Note that lps[currMaxLPS-1] contains the largest
					// prefix of pattern that matches the suffix ending
					// at pattern[currMaxLPS-1]
					// So we try to match from that prefix (which has to be
					// smaller than current prefix)
					// Observe that i is not incrementing here.
					currMaxLPS = lps[currMaxLPS - 1];
				} else {
					lps[i] = 0;
					i++;
				}
			}
		}
	}

	/**
	 * KMP is just a re-application of the LPS concept shown above. Its
	 * algorithm is very similar to the above one.
	 */
	int kmp(String pattern, String txt) {
		int cantidad = 0;

		int lps[] = new int[pattern.length()];
		computeLPS(pattern, lps);

		int txtPos = 0, patternPos = 0;
		while (txtPos < txt.length()) {
			numberOfComparations++;
			numberOfComparations++;

			if (pattern.charAt(patternPos) == txt.charAt(txtPos)) {
				patternPos++;
				txtPos++;
			}

			numberOfComparations++;
			numberOfComparations++;

			if (patternPos == pattern.length()) {
				numberOfComparations--;
				numberOfComparations--;

				cantidad++;
				// System.out.println
				// ("Pattern is found at index " + (txtPos-patternPos));
				patternPos = lps[patternPos - 1];
			}

			else if (txt.length() > txtPos && pattern.charAt(patternPos) != txt.charAt(txtPos)) {
				// mismatch occurs after j matches
				if (patternPos != 0) {
					patternPos = lps[patternPos - 1];
				} else
					txtPos++;
			}
		}

		return cantidad;
	}

	@Override
	protected int search(String pattern, String text) {

		int cantidad = 0;

		cantidad = kmp(pattern, text);

		return cantidad;
	}
}