public class Hash {
	
	private static final int BASE=256;
	/*
	 * Returns a HASH value for the given string. 
	 * Assumptions: 
	 * We are assuming the BASE of the string to be 256
	 * Formula used to calculate the HASH value is :
	 * H = s[0] * B ^ m + s[1] * B ^m-1..+s[m-1]*1
	 * 
	 * @param text String to be hashed to a given value
	 * 
	 * @returns hash value generated.
	 */
	public static int getHASHValue(String s)
	{
		char[] charArray = s.toCharArray();
		// reuse the power value from first run
		
		int lengthArray  = charArray.length;
		int hashVal = charArray[lengthArray-1];
		int  multiplier = 1;
		
		for (int i = lengthArray-2; i>= 0 ; i--)
		{
			multiplier *= BASE;
			hashVal += charArray[i] * multiplier;
		}
		
		return hashVal;
		
	}
	
	public static int getBaseToPowerMValue(int patternLength)
	{
		return powerFunc(BASE,patternLength);
	}
	
	private static int powerFunc(int base, int power)
	{
		if (power == 0)
			return 1;
		if (power % 2 == 0)
			return powerFunc(base,power/2)*powerFunc(base,power/2);
		else
			return base*powerFunc(base,power/2)*powerFunc(base,power/2);
			
	}
	
	public static int getNextHashValue(int hashVal, char prevChar, char newChar, int baseToPowerM)
	{
		int hashValue = BASE * hashVal - prevChar*baseToPowerM + newChar;
		return hashValue;
	}
	
	// Create KMP table

	
	private static void KMPMatcher(String pattern, Integer[] matcherTable)
	{
		int m = pattern.length();
		matcherTable[0] = -1;
		int k = -1;
		int q = 1;
		
		for (;q < m;q++)
		{
		
			while(k >= 0 && pattern.charAt(k+1) != pattern.charAt(q))
			// loop through the matcher table 
			{
				k = matcherTable[k];
			}                         
			
			if (pattern.charAt(k+1) == pattern.charAt(q))
				k += 1;
			matcherTable[q] = k;
		}	
	}
	
	public static void stringMatcher(String pattern, String text)
	{
		Integer[] matcherTable = new Integer[pattern.length()];
		KMPMatcher(pattern, matcherTable);
		
		int k = -1;
		int q = 0;
		
		while(q < text.length())
		{
			while (k >=0 && pattern.charAt(k+1) != text.charAt(q))
				k = matcherTable[k];
			
			if (pattern.charAt(k+1) == text.charAt(q))
				k++;
			q++;
			
			if (k == pattern.length()-1)
			{
				System.out.printf("\nPattern String found at index %d and string %s\n", q, text);
				k = -1;
			}
		}
	}
}