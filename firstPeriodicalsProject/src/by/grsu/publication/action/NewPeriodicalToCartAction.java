package by.grsu.publication.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import by.grsu.publication.forms.NewPeriodicalToCartForm;
import by.grsu.publication.model.Subscribe;
import by.grsu.publication.model.UserSession;
import by.grsu.publication.service.ServiceManager;
import by.grsu.publication.service.SubscribeService;

public class NewPeriodicalToCartAction extends UserRoleCheckAction {

	private static final Logger logger = Logger
			.getLogger(NewPeriodicalToCartAction.class);

	private static final String NEW_ERROR = "failed to add subscribe : ";

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		NewPeriodicalToCartForm input = (NewPeriodicalToCartForm) form;

		SubscribeService subscribeService = ServiceManager.getDefault()
				.getSubscribeService();

		int periodicalId = input.getPeriodicalId();
		HttpSession session = request.getSession();
		UserSession userSession = (UserSession) session.getAttribute("auth");
		int userId = userSession.getUserId();
		ActionErrors actionErrors = new ActionErrors();
		ActionMessages actionMessage = new ActionMessages();
		List<Subscribe> subscribes = null;
		String target = userChack(request, NewPeriodicalToCartAction.class);
		if (target.equals(ForwardActionTo.ERROR)) {
			try {
				subscribes = subscribeService.getAllSubscribe();
				boolean k = true;

				if (periodicalId != 0 && userId != 0) {
					for (Subscribe subscribe : subscribes) {
						if (subscribe.getPeriodicalId() == periodicalId
								&& subscribe.getUserId() == userId
								&& subscribe.getStatusSubscribe().equals(
										"basket")) {
							k = false;
							actionErrors.add("statusbasket", new ActionMessage(
									"error.statusbasket"));
						}
						if (subscribe.getPeriodicalId() == periodicalId
								&& subscribe.getUserId() == userId
								&& subscribe.getStatusSubscribe().equals(
										"subscribe")) {
							k = false;
							actionErrors.add("statussubscribe",
									new ActionMessage("error.statussubscribe"));
						}
					}
					if (k == true) {
						actionMessage.add("performed", new ActionMessage(
								"message.performed"));
						subscribeService.getCartNew(periodicalId, userId);
						logger.info("User ID: " + userId
								+ " added to shopping cart periodical ID: "
								+ periodicalId);
						target = ForwardActionTo.SUCCESS;
					}
					saveErrors(request, actionErrors);
					saveMessages(request, actionMessage);
				}
			} catch (Exception e) {
				logger.error(NewPeriodicalToCartAction.NEW_ERROR + e);
			}
		}

		return mapping.findForward(target);
	}
}
