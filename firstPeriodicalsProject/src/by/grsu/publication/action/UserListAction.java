package by.grsu.publication.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import by.grsu.publication.forms.SearchUserForm;
import by.grsu.publication.model.IUser;
import by.grsu.publication.service.ServiceManager;
import by.grsu.publication.service.UserService;

public class UserListAction extends UserRoleCheckAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ActionMessages errors = new ActionMessages();
		SearchUserForm searchUserForm = (SearchUserForm) form;
		UserService userService = ServiceManager.getDefault().getUserService();
		List<IUser> userList = new ArrayList<IUser>();
		String target = adminChack(request, ChangeImageAction.class);
		if (target.equals(ForwardActionTo.ERROR)) {
			if (searchUserForm.getUserLogin() != null
					&& searchUserForm.getUserLogin() != "") {
				userList = userService.getUsersByLogin(searchUserForm
						.getUserLogin());
				target = ForwardActionTo.SUCCESS;
				if (userList.isEmpty()) {
					errors.add("nullUser", new ActionMessage("error.nullUser"));
					saveErrors(request, errors);
				}
			} else {
				userList = userService.getAllUsers();
				target = ForwardActionTo.SUCCESS;
			}
			request.setAttribute("userList", userList);
		} else {
			target = ForwardActionTo.GUEST;
		}
		return mapping.findForward(target);
	}
}
