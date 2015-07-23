package by.grsu.publication.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import by.grsu.publication.forms.PeriodicalsListForm;
import by.grsu.publication.model.IPeriodical;
import by.grsu.publication.model.UserSession;
import by.grsu.publication.service.PeriodicalService;
import by.grsu.publication.service.ServiceManager;

public class PeriodicalsListAction extends Action {
	
	private static final Logger logger = Logger
			.getLogger(PeriodicalsListAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ActionMessages errors = new ActionMessages();

		String target = ForwardActionTo.ERROR;
		List<IPeriodical> periodicals = null;
		PeriodicalsListForm periodicalsListForm = (PeriodicalsListForm) form;
		PeriodicalService periodicalService = ServiceManager.getDefault()
				.getPeriodicalService();
		int num = 0;
		int[] pages = null;
		HttpSession session = request.getSession();
		UserSession userSession = (UserSession) session.getAttribute("auth");
		try {
			if (periodicalsListForm.getName() != null
					&& periodicalsListForm.getName() != "") {
				num = periodicalService.getNumberPeriodicalsSerach(periodicalsListForm.getName());
				pages = new int[num];
				periodicals = periodicalService
						.getPeriodicalSearch(periodicalsListForm.getName());
			} else {
				num = periodicalService.getNumberPeriodicals();
				if(periodicalsListForm.getPage()<1 || periodicalsListForm.getPage()>num){
					periodicalsListForm.setPage(1);
				}
				pages = new int[num];
				periodicals = periodicalService.getPeriodicals(periodicalsListForm.getPage());
			}
			for(int i = 1 ; num>=i ; i++){
				pages[i-1] = i;
			}
			request.setAttribute("periodicals", periodicals);
			request.setAttribute("pages", pages);
			request.setAttribute("currentPage", periodicalsListForm.getPage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		if (periodicals.isEmpty()) {
			errors.add("nullPer", new ActionMessage("error.nullPer"));
			saveErrors(request, errors);
		}

		if (userSession != null) {
			target = ForwardActionTo.SUCCESS;
		} else {
			target = ForwardActionTo.GUEST;
		}

		return mapping.findForward(target);
	}
}
