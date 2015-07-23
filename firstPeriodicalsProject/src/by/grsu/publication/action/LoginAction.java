package by.grsu.publication.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.grsu.publication.forms.LoginForm;
import by.grsu.publication.model.IUser;
import by.grsu.publication.model.UserSession;
import by.grsu.publication.service.ServiceManager;
import by.grsu.publication.service.UserService;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class LoginAction extends Action {

	private static final Logger logger = Logger.getLogger(LoginAction.class);

	private static final String LOGIN_ERROR = "Could not logged: ";

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ActionMessages errors = new ActionMessages();
		String target = "login.layout";
		LoginForm loginForm = (LoginForm) form;
		IUser user = null;
		UserService userService = ServiceManager.getDefault().getUserService();
		try {
			if (loginForm != null) {
				user = userService.getUser(loginForm.getUserName());
				if (user != null) {
					if (user.getPassword().equals(loginForm.getPassword())) {
						target = "/periodicalsList.do";
						HttpSession session = request.getSession(true);
						UserSession userSession = new UserSession(user.getId(),
								user.getUserName(), user.getUserType());
						logger.info("User ID: " + user.getId() + " logged");
						session.setAttribute("auth", userSession);
					} else {
						errors.add("invalidPassword", new ActionMessage(
								"error.password.fail"));
					}
				} else {
					errors.add("invalidUserName", new ActionMessage(
							"error.login.fail"));
					loginForm.reset(mapping, request);
				}
			}
			saveErrors(request, errors);
			request.setAttribute("user", user);
		} catch (Exception e) {
			logger.error(LoginAction.LOGIN_ERROR + e.getMessage());
		}

		return new ActionForward(target, true);
	}
}