package by.epam.periodicals.editor;

import java.beans.PropertyEditorSupport;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;

public class DateTimeEditor extends PropertyEditorSupport {

	private DateTimeFormatter dateTimeFormatter;

	public DateTimeEditor(DateTimeFormatter dateTimeFormatter) {
		this.dateTimeFormatter = dateTimeFormatter;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		setValue(DateTime.parse(text, dateTimeFormatter));
	}

}
