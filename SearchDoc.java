import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.HashMap;

public class SearchDoc {
	
	public static void main(String[] args)
	{
		// Read the file path from input and load into the string.
		String fileName = args[0];
		String pattern = args[1].toLowerCase();
		int patternLength = pattern.length();
		int patternHashCode = Hash.getHASHValue(pattern);
		int baseToPowerMValue = Hash.getBaseToPowerMValue(patternLength);
		
		
		try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
		{

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				
				if (sCurrentLine.length() < patternLength)
					continue;
				
				sCurrentLine = sCurrentLine.toLowerCase();
				
				// process the current string and search in it
				
				
				// 1. Method to search the string in the given text using HASHING
				/*
				int i = 0;
				int textHashCode = Hash.getHASHValue(sCurrentLine.substring(i, i+patternLength));
				i++;
				if (textHashCode == patternHashCode)
					System.out.printf("Found characters in line  %s", sCurrentLine);
				while (i < sCurrentLine.length()-patternLength && i > 0)
				{
					textHashCode = Hash.getNextHashValue(textHashCode,sCurrentLine.charAt(i-1),sCurrentLine.charAt(i+patternLength-1),baseToPowerMValue);
					
					if (textHashCode == patternHashCode)
						System.out.printf("Found characters %s and hashValues %d and %d\n", sCurrentLine, textHashCode, patternHashCode);
					
					i++;	
				}
				*/
				
				// 2. Method to search the string in the given text using KMP matcher algorithm
				
				//System.out.printf("Now using KMP matcher algorithm to search the string \n");
				
				Hash.stringMatcher(pattern, sCurrentLine);
				
				
				
				
				
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}