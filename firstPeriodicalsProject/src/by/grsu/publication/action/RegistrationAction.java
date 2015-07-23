package by.grsu.publication.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import by.grsu.publication.forms.RegistrationForm;
import by.grsu.publication.model.IUser;
import by.grsu.publication.service.ServiceManager;
import by.grsu.publication.service.UserService;

public class RegistrationAction extends Action {

	private static final Logger logger = Logger
			.getLogger(RegistrationAction.class);

	private static final String REGISTRATION_ERROR = "Could not logout: ";

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String target = ForwardActionTo.ERROR;
		RegistrationForm registrationForm = (RegistrationForm) form;
		ActionErrors actionErrors = new ActionErrors();
		UserService userService = ServiceManager.getDefault().getUserService();

		try {
			if (registrationForm != null) {
				String userName = registrationForm.getUserName();
				String userSurname = registrationForm.getUserSurname();
				String login = registrationForm.getLoginReg();
				String password = registrationForm.getPasswordReg();
				String password2 = registrationForm.getPassword2();
				String mail = registrationForm.getMail();

				IUser user = userService.getUser(login);

				if (user != null) {
					actionErrors.add("equallogin", new ActionMessage(
							"error.equallogin", login));
				} else {
					if (password2.trim().equals(password)) {
						userService.getUserNew(userName, userSurname, login,
								password2, mail);
						logger.info("Registrated new user: " + login);
						target = ForwardActionTo.SUCCESS;

						registrationForm.reset(mapping, request);
					} else {
						actionErrors.add("password2", new ActionMessage(
								"error.password2", password2));
					}
				}
				saveErrors(request, actionErrors);
			}
		} catch (Exception e) {
			logger.error(RegistrationAction.REGISTRATION_ERROR + e.getMessage());
		}

		return mapping.findForward(target);
	}
}
