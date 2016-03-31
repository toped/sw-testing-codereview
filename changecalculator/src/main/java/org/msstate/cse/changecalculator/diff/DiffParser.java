package org.msstate.cse.changecalculator.diff;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class DiffParser {
	
	public static List<String> getChangedFiles(String diffContent) {  //AJAY-NOTE: Change return from list<string> set<string>
		if (diffContent != null && !diffContent.isEmpty()) {
			List<String> changedFileList = new ArrayList<String>();
			InputStream in = new ByteArrayInputStream(diffContent.getBytes());
			Reader unbufferedReader = new InputStreamReader(in);
			BufferedReader reader = new BufferedReader(unbufferedReader);
			String currentLine;
			try {
				while ((currentLine = reader.readLine()) != null) {
					if (currentLine.startsWith("Index:")) {
						String[] splitStr = currentLine.split("Index:");
						if (splitStr.length == 2 && splitStr[1] != null
								&& !splitStr[1].isEmpty()) {
							changedFileList.add(splitStr[1].trim());
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (reader != null)
						reader.close();
					if (unbufferedReader != null)
						unbufferedReader.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			return changedFileList;
		}
		return null;
	}

}
