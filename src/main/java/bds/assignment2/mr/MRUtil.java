package bds.assignment2.mr;

import java.text.ParseException;
import java.util.Date;

public class MRUtil {

	public static boolean is2010(String dateStr) {

		try {
			// Parse the date string
			Date invoiceDate = IRunConfig.DATE_FORMAT.parse(dateStr);
			int year = invoiceDate.getYear() + 1900; // Add 1900 to get the actual year

			if (year == 2010) {
				return true;
			}
		} catch (Exception e) { // catch (ParseException | NumberFormatException e) {
			System.err.println("Error while parsing date: " + dateStr);
		}

		return false;

	}

}
