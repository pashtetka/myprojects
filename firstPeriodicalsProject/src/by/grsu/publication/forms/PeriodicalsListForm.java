package by.grsu.publication.forms;

import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")
public class PeriodicalsListForm extends ActionForm {

	private String name;
	private int page;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

}
