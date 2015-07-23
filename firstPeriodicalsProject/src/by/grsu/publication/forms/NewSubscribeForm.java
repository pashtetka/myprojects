package by.grsu.publication.forms;

import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")
public class NewSubscribeForm extends ActionForm {
	
	private int[] periodicalId;

	public int[] getPeriodicalId() {
		return periodicalId;
	}

	public void setPeriodicalId(int[] periodicalId) {
		this.periodicalId = periodicalId;
	}

}
