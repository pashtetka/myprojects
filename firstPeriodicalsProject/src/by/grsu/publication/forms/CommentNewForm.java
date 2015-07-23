package by.grsu.publication.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

@SuppressWarnings("serial")
public class CommentNewForm extends ActionForm {
	
	private int periodicalId;
	private String comm;

	public String getComm() {
		return comm;
	}
	public void setComm(String comm) {
		this.comm = comm;
	}
	
	public int getPeriodicalId() {
		return periodicalId;
	}
	public void setPeriodicalId(int periodicalId) {
		this.periodicalId = periodicalId;
	}
	
	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		
		ActionErrors actionErrors = new ActionErrors();
		
		if(comm == null || comm == ""){
			actionErrors.add("commentNull", new ActionMessage("error.nullComment"));
		}		
		return actionErrors;
	}
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.comm = null;
		super.reset(mapping, request);
	}

}
