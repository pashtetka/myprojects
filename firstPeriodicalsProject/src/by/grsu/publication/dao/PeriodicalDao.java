package by.grsu.publication.dao;

import java.util.List;

import org.apache.struts.upload.FormFile;

import by.grsu.publication.entity.PeriodicalEntity;
import by.grsu.publication.exception.LargeSizePicturesException;
import by.grsu.publication.exception.NullImageException;

public interface PeriodicalDao {

	public List<PeriodicalEntity> getPeriodicals(int num);

	public void getPeriodicalDelete(int id);

	public void getPeriodicalNew(String periodicalName, int cost,
			int outputsInMonth, String topic, FormFile image)
			throws LargeSizePicturesException;

	public void getPeriodicalNew(String periodicalName, int cost,
			int outputsInMonth, String topic);

	public PeriodicalEntity getPeriodicalsById(int id);

	public void getNewImage(int id, FormFile image)
			throws LargeSizePicturesException, NullImageException;

	public List<PeriodicalEntity> getPeriodicalSearch(String name);

	public int getNumberPeriodicals();

	public int getNumberPeriodicalsSerach(String name);

}
