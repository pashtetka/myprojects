package by.grsu.publication.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import by.grsu.publication.forms.BasketForm;
import by.grsu.publication.model.IPeriodical;
import by.grsu.publication.model.UserSession;
import by.grsu.publication.service.PeriodicalService;
import by.grsu.publication.service.ServiceManager;

public class BasketAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String target = ForwardActionTo.ERROR;
		ActionMessages errors = new ActionMessages();
		BasketForm bascetForm = (BasketForm) form;
		PeriodicalService periodicalService = ServiceManager.getDefault()
				.getPeriodicalService();
		int summ = 0;
		List<IPeriodical> periodicalsBasket = null;
		HttpSession session = request.getSession();
		UserSession userSession = (UserSession) session.getAttribute("auth");
		if (userSession != null) {
			int userId = userSession.getUserId();
			try {
				if (userId > 0) {
					periodicalsBasket = periodicalService.getUserBasket(userId,
							bascetForm.getStatus());
					if (periodicalsBasket.isEmpty()) {
						if ("basket".equals(bascetForm.getStatus())) {
							errors.add("itemsBasket", new ActionMessage(
									"error.itemsBasket"));
						}
						if ("subscribe".equals(bascetForm.getStatus())) {
							errors.add("itemsSubscribe", new ActionMessage(
									"error.itemsSubscribe"));
						}
						saveErrors(request, errors);
					}
					for (IPeriodical per : periodicalsBasket) {
						summ += per.getCost() * per.getOutputsInMonth();
					}
					request.setAttribute("periodicalsBasket", periodicalsBasket);
					target = ForwardActionTo.SUCCESS;
				}
				request.setAttribute("summ", summ);
			} catch (Exception e) {
				target = ForwardActionTo.GUEST;
			}
		} else {
			target = ForwardActionTo.GUEST;
		}
		return mapping.findForward(target);

	}
}
