package by.grsu.publication.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import by.grsu.publication.forms.UserSubscribesForm;
import by.grsu.publication.model.IPeriodical;
import by.grsu.publication.model.IUser;
import by.grsu.publication.service.PeriodicalService;
import by.grsu.publication.service.ServiceManager;
import by.grsu.publication.service.UserService;

public class UserSubscribesAction extends UserRoleCheckAction {
	
	private static final Logger logger = Logger
			.getLogger(UserSubscribesAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ActionMessages errors = new ActionMessages();
		UserSubscribesForm userSubscribe = (UserSubscribesForm) form;
		List<IPeriodical> periodicals = null;
		PeriodicalService periodicalService = ServiceManager.getDefault()
				.getPeriodicalService();
		UserService userService = ServiceManager.getDefault().getUserService();
		String target = adminChack(request, UserSubscribesAction.class);
		if (target.equals(ForwardActionTo.ERROR)) {
			int summ = 0;
			IUser user = null;
			user = userService.getUserById(userSubscribe.getUserId());
			request.setAttribute("user", user);
			try {
				if (userSubscribe != null) {
					periodicals = periodicalService.getUserBasket(
							userSubscribe.getUserId(), "subscribe");

					if (periodicals.isEmpty()) {
						errors.add("itemsSubscribe", new ActionMessage(
								"error.itemsSubscribe"));
						saveErrors(request, errors);
					}
					for (IPeriodical per : periodicals) {
						summ += per.getCost() * per.getOutputsInMonth();
					}
					request.setAttribute("userSubscribes", periodicals);
					request.setAttribute("summ", summ);
					target = ForwardActionTo.SUCCESS;
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return mapping.findForward(target);
	}
}
