package by.grsu.publication.forms;

import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")
public class UpdateUserRoleForm extends ActionForm {
	
	private int userId;
	private String userRole;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
}
