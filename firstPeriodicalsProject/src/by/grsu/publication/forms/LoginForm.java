package by.grsu.publication.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

@SuppressWarnings("serial")
public class LoginForm extends ActionForm {

	private String userName;
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

		ActionErrors actionErrors = new ActionErrors();

		if ((userName == null) || (userName.trim().equals(""))) {
			actionErrors.add("userName", new ActionMessage("error.userName",
					userName));
		}

		try {
			if ((password == null) || (password.trim().equals(""))) {
				actionErrors.add("password",
						new ActionMessage("error.password"));
			}
		} catch (Exception e) {
		}

		return actionErrors;
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.userName = null;
		this.password = null;
		super.reset(mapping, request);
	}

}
