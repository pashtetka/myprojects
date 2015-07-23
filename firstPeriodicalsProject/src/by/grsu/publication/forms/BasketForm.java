package by.grsu.publication.forms;

import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")
public class BasketForm extends ActionForm {

	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
