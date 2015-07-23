package by.grsu.publication.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import by.grsu.publication.forms.UpdateUserRoleForm;
import by.grsu.publication.model.UserSession;
import by.grsu.publication.service.ServiceManager;
import by.grsu.publication.service.UserService;

public class UpdateUserRoleAction extends UserRoleCheckAction {

	private static final Logger logger = Logger.getLogger(LogoutAction.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ActionMessages errors = new ActionMessages();
		UpdateUserRoleForm updateUserRoleForm = (UpdateUserRoleForm) form;
		HttpSession session = request.getSession();
		UserSession userSession = (UserSession) session.getAttribute("auth");
		String target = adminChack(request, UpdateUserRoleAction.class);
		if (target.equals(ForwardActionTo.ERROR)) {
			if (updateUserRoleForm.getUserId() != 1) {
				if (userSession.getUserId() != updateUserRoleForm.getUserId()) {
					UserService userService = ServiceManager.getDefault()
							.getUserService();
					userService.getUpdateUserRole(
							updateUserRoleForm.getUserId(),
							updateUserRoleForm.getUserRole());
					logger.info("Admin ID: " + userSession.getUserId()
							+ " changed the role of the user ID: "
							+ updateUserRoleForm.getUserId() + " to the "
							+ updateUserRoleForm.getUserRole());
				} else {
					errors.add("ownRole", new ActionMessage("error.ownRole"));
				}
			} else {
				errors.add("adminRole", new ActionMessage("error.adminRole"));
			}
			saveErrors(request, errors);
			target = ForwardActionTo.SUCCESS;
		}
		return mapping.findForward(target);
	}

}
