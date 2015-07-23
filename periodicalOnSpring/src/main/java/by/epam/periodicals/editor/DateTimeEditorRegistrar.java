package by.epam.periodicals.editor;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.core.convert.converter.Converter;

public class DateTimeEditorRegistrar implements PropertyEditorRegistrar, Converter<String, DateTime> {
	
	private DateTimeFormatter dateTimeFormatter;

	public DateTimeEditorRegistrar(String dateTimeFormatter) {
		this.dateTimeFormatter = DateTimeFormat.forPattern(dateTimeFormatter);
	}

	@Override
	public void registerCustomEditors(
			PropertyEditorRegistry registry) {
		registry.registerCustomEditor(DateTime.class, new DateTimeEditor(dateTimeFormatter));
		
	}

	@Override
	public DateTime convert(String paramS) {
		return dateTimeFormatter.parseDateTime(paramS);
	}

}
