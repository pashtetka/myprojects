package by.grsu.publication.forms;

import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")
public class SearchUserForm extends ActionForm {
	
	private String userLogin;

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

}
