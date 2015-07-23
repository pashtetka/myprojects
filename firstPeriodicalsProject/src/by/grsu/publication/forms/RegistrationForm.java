package by.grsu.publication.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

@SuppressWarnings("serial")
public class RegistrationForm extends ActionForm {

	private String userName;
	private String userSurname;
	private String loginReg;
	private String passwordReg;
	private String password2;
	private String mail;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSurname() {
		return userSurname;
	}

	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}

	public String getLoginReg() {
		return loginReg;
	}

	public void setLoginReg(String loginReg) {
		this.loginReg = loginReg;
	}

	public String getPasswordReg() {
		return passwordReg;
	}

	public void setPasswordReg(String passwordReg) {
		this.passwordReg = passwordReg;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

		ActionErrors actionErrors = new ActionErrors();

		if ((userName == null) || (userName.trim().equals(""))) {
			actionErrors.add("userName", new ActionMessage("error.userName",
					userName));
		}
		if ((userSurname == null) || (userSurname.trim().equals(""))) {
			actionErrors.add("userSurname", new ActionMessage(
					"error.usersurname", userSurname));
		}

		if ((loginReg == null) || (loginReg.trim().equals(""))) {
			actionErrors.add("login", new ActionMessage("error.login", loginReg));
		}

		if ((passwordReg == null) || (passwordReg.trim().equals(""))) {
			actionErrors.add("password", new ActionMessage("error.password",
					passwordReg));
		}

		if ((password2 == null) || (password2.trim().equals(""))) {
			actionErrors.add("passwordnull", new ActionMessage(
					"error.passwordnull", password2));
		}

		if ((mail == null) || (mail.trim().equals(""))) {
			actionErrors.add("mail", new ActionMessage("error.mail", mail));
		}

		return actionErrors;
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.userName = null;
		this.userSurname = null;
		this.loginReg = null;
		this.passwordReg = null;
		this.password2 = null;
		this.mail = null;
		super.reset(mapping, request);
	}

}
