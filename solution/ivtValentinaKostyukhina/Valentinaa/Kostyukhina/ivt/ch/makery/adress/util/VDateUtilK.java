package Valentinaa.Kostyukhina.ivt.ch.makery.adress.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class VDateUtilK {
	private static final String DATE_PATTERN = "dd.VK.yyyy";
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
			.ofPattern(DATE_PATTERN);

	public static String vformatK(LocalDate date) {
		if (date == null)
			return null;
		return DATE_FORMATTER.format(date);
	}

	public static LocalDate vparseK(String dateString) {
		try {
			return DATE_FORMATTER.parse(dateString, LocalDate::from);
		} catch (DateTimeParseException e) {
			return null;
		}
	}

	public static boolean vvalidDateMK(String dateString) {
		return VDateUtilK.vparseK(dateString) != null;
	}
}