package se.ifkgoteborg.stat.importer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import se.ifkgoteborg.stat.controller.RegistrationDAO;

public class NotesImporter {
	private final RegistrationDAO dao;
	
	private Pattern p = Pattern.compile("^\\d{2}\\.\\d{1,2}\\s\\d{4}\\s.");
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM yyyy");

	public NotesImporter(RegistrationDAO dao) {
		this.dao = dao;		
	}

	public void importNotes(String data) {
		String[] rows = data.split("\n");
		int n = 0;
		for(String row : rows) {
			if(row.trim().length() > 0) {
				//System.out.println("Row: " + row.trim().replaceAll("\r", ""));
				processNoteRow(row.trim().replaceAll("\r", ""));
				n++;
			}
		}
		System.out.println("Totalt antal rader: " + n);
	}

	private void processNoteRow(String row) {
		//System.out.println(row);
		String[] parts = row.split("\t");
		if(parts.length == 2 && !parts[0].startsWith("€€€")) {
			try {
				Date date = sdf.parse(parts[0]);
				dao.updateGameWithNote(date, parts[1]);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
