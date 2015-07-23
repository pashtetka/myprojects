package by.grsu.publication.service;

import java.util.List;

import org.apache.struts.upload.FormFile;

import by.grsu.publication.exception.LargeSizePicturesException;
import by.grsu.publication.model.IPeriodical;

public interface PeriodicalService {

	public List<IPeriodical> getPeriodicals(final int page);

	public void getPeriodicalDelete(final int id);

	public void getPeriodicalNew(final String periodicalName, final int cost,
			final int outputsInMonth, final String topic, final FormFile image) throws LargeSizePicturesException;

	public IPeriodical getPeriodicalsById(final int id);

	public List<IPeriodical> getUserBasket(final int id, final String status);
	
	public String getNewImage(final int id, final FormFile image);
	
	public List<IPeriodical> getPeriodicalSearch(final String name);
	
	public int getNumberPeriodicals();
	
	public int getNumberPeriodicalsSerach(final String name);

}
