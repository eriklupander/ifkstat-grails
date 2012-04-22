package se.ifkgoteborg.stat.util;

public class StringUtil {
	public static int countOccurrences(String haystack, String needle)
	{
	    int count = 0;
	    for (int i=0; i < haystack.length(); i++)
	    {
	        if (haystack.charAt(i) == needle.charAt(0))
	        {
	             count++;
	        }
	    }
	    return count;
	}

	public static String getLines(String str, int first, int last) {
		StringBuilder buf = new StringBuilder();
		String[] rows = str.split("\n");
		for(int a = first; a < last; a++) {
			buf.append(rows[a]).append("\n");
		}
		if(buf.length() > 0) {
			buf.setLength(buf.length() - 1);
		}
		return buf.toString();
	}

	public static String getLines(String str, int first) {
		StringBuilder buf = new StringBuilder();
		String[] rows = str.split("\n");
		for(int a = first; a < rows.length; a++) {
			buf.append(rows[a]).append("\n");
		}
		if(buf.length() > 0) {
			buf.setLength(buf.length() - 2);
		}
		return buf.toString();
	}

	public static int parseSeasonStringToStartYear(String seasonName) {
		seasonName = seasonName.replaceAll(" ", "/");
		if(seasonName.trim().indexOf("/") > -1) {
			String[] parts = seasonName.trim().split("/");
			if(parts.length > 0)
				if(parts[0].trim().length() == 2) {
					parts[0] = "19" + parts[0].trim();
				}
				
				return Integer.parseInt(parts[0].trim());
		} else {
			return Integer.parseInt(seasonName.trim());
		}
	}

}
