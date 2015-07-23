package by.grsu.publication.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

@SuppressWarnings("serial")
public class PeriodicalNewForm extends ActionForm {

	private String periodicalName;
	private int cost;
	private int outputsInMonth;
	private String topic;
	private FormFile image = null;

	public String getPeriodicalName() {
		return periodicalName;
	}

	public void setPeriodicalName(String periodicalName) {
		this.periodicalName = periodicalName;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getOutputsInMonth() {
		return outputsInMonth;
	}

	public void setOutputsInMonth(int outputsInMonth) {
		this.outputsInMonth = outputsInMonth;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	public FormFile getImage() {
		return image;
	}

	public void setImage(FormFile image) {
		this.image = image;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

		ActionErrors actionErrors = new ActionErrors();

		if ((periodicalName == null) || (periodicalName.trim().equals(""))) {
			actionErrors.add("periodicalName", new ActionMessage(
					"error.periodicalName", periodicalName));
		}

		if (cost == 0) {
			actionErrors.add("cost", new ActionMessage("error.cost", cost));
		}
		if (outputsInMonth == 0) {
			actionErrors.add("outputsInMonth", new ActionMessage(
					"error.outputs", outputsInMonth));
		}
		if ((topic == null) || (topic.trim().equals(""))) {
			actionErrors.add("topic", new ActionMessage("error.topic", topic));
		}

		return actionErrors;
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.periodicalName = null;
		this.cost = 0;
		this.outputsInMonth = 0;
		this.topic = null;
		super.reset(mapping, request);
	}

}
