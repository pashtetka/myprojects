package by.grsu.publication.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import by.grsu.publication.forms.NewSubscribeForm;
import by.grsu.publication.model.UserSession;
import by.grsu.publication.service.ServiceManager;
import by.grsu.publication.service.SubscribeService;

public class NewSubscribeAction extends UserRoleCheckAction {

	private static final Logger logger = Logger
			.getLogger(NewSubscribeAction.class);
	private static final String SUBSCRIBE_ERROR = "Could not subscribe periodical: ";

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		NewSubscribeForm newSubscribeForm = (NewSubscribeForm) form;
		SubscribeService subscribeService = ServiceManager.getDefault()
				.getSubscribeService();

		HttpSession session = request.getSession();
		UserSession userSession = (UserSession) session.getAttribute("auth");
		String target = userChack(request, NewSubscribeAction.class);
		if (target.equals(ForwardActionTo.ERROR)) {
			try {
				int[] per = newSubscribeForm.getPeriodicalId();
				if (per.length > 0) {
					for (int i = 0; i < per.length; i++) {
						subscribeService.getNewSubscribe(per[i],
								userSession.getUserId());
						logger.info("New subscribe periodical ID: " + per[i]);
					}
				}
				target = ForwardActionTo.SUCCESS;
			} catch (Exception e) {
				logger.error(NewSubscribeAction.SUBSCRIBE_ERROR
						+ e.getMessage());
			}
		}
		return mapping.findForward(target);
	}
}
