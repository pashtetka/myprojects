package by.grsu.publication.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import by.grsu.publication.forms.PeriodicalDeleteForm;
import by.grsu.publication.model.IPeriodical;
import by.grsu.publication.service.PeriodicalService;
import by.grsu.publication.service.ServiceManager;

public class PeriodicalDeleteAction extends UserRoleCheckAction {

	private static final Logger logger = Logger
			.getLogger(PeriodicalDeleteAction.class);

	private static final String DELETE_ERROR = "Could not deleted periodical: ";

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PeriodicalDeleteForm input = (PeriodicalDeleteForm) form;

		String target = adminChack(request, PeriodicalDeleteAction.class);
		int num = 0;
		int[] pages = null;
		if (target.equals(ForwardActionTo.ERROR)) {
			try {
				List<IPeriodical> publications = new ArrayList<IPeriodical>();
				PeriodicalService periodicalService = ServiceManager
						.getDefault().getPeriodicalService();
				num = periodicalService.getNumberPeriodicals();
				if(input.getPage()<1 || input.getPage()>num){
					input.setPage(1);
				}
				pages = new int[num];
				publications = periodicalService.getPeriodicals(input.getPage());
				request.setAttribute("publications", publications);
				int[] devs = input.getSelectedDevices();
				for(int i = 1 ; num>=i ; i++){
					pages[i-1] = i;
				}
				if (devs.length > 0) {
					for (int i = 0; i < devs.length; i++) {
						periodicalService.getPeriodicalDelete(devs[i]);
						logger.info("Deleted periodical ID: " + devs[i]);
					}
					target = ForwardActionTo.SUCCESS;
				}
				request.setAttribute("pages", pages);
				request.setAttribute("currentPage", input.getPage());
				input.reset(mapping, request);				
			} catch (Exception e) {
				logger.error(PeriodicalDeleteAction.DELETE_ERROR
						+ e.getMessage());
			}
		}
		return mapping.findForward(target);
	}
}
