package by.grsu.publication.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

@SuppressWarnings("serial")
public class PeriodicalDeleteForm extends ActionForm {

	private int[] selectedDevices;
	private int page;

	public int[] getSelectedDevices() {
		return selectedDevices;
	}

	public void setSelectedDevices(int[] selectedDevices) {
		this.selectedDevices = selectedDevices;
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		selectedDevices = new int[0];
		super.reset(mapping, request);
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

}
