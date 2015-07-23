package by.grsu.publication.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import by.grsu.publication.forms.DeleteBasketForm;
import by.grsu.publication.model.UserSession;
import by.grsu.publication.service.ServiceManager;
import by.grsu.publication.service.SubscribeService;

public class DeleteBasketAction extends UserRoleCheckAction {

	private static final Logger logger = Logger
			.getLogger(DeleteBasketAction.class);
	private static final String LOGGER_ERROR = "Delete cart error: ";

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DeleteBasketForm delete = (DeleteBasketForm) form;
		int periodicalId = delete.getPeriodicalId();
		SubscribeService subscribeService = ServiceManager.getDefault()
				.getSubscribeService();
		String target = userChack(request, DeleteBasketAction.class);
		HttpSession session = request.getSession();
		UserSession userSession = (UserSession) session.getAttribute("auth");

		if (target.equals(ForwardActionTo.ERROR)) {
			int userId = userSession.getUserId();

			try {
				subscribeService.getDeleteBasketById(userId, periodicalId);
				target = ForwardActionTo.SUCCESS;
			} catch (Exception e) {
				logger.error(DeleteBasketAction.LOGGER_ERROR + e.getMessage());
			}
		}
		return mapping.findForward(target);
	}

}
