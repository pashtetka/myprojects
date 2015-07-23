package by.grsu.publication.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import by.grsu.publication.forms.ChangeImageForm;
import by.grsu.publication.service.PeriodicalService;
import by.grsu.publication.service.ServiceManager;

public class ChangeImageAction extends UserRoleCheckAction {

	private static final Logger logger = Logger
			.getLogger(ChangeImageAction.class);

	private static final String NEW_ERROR = "failed to add image : ";

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ActionMessages errors = new ActionMessages();
		ChangeImageForm newImageForm = (ChangeImageForm) form;
		String target = adminChack(request, ChangeImageAction.class);
		String message = null;
		if (target.equals(ForwardActionTo.ERROR)) {
			try {
				PeriodicalService periodicalService = ServiceManager
						.getDefault().getPeriodicalService();
				message = periodicalService.getNewImage(newImageForm.getId(),
						newImageForm.getImage());
				target = ForwardActionTo.SUCCESS;
			} catch (Exception e) {
				logger.error(ChangeImageAction.NEW_ERROR + e);
			}
			errors.add(message, new ActionMessage("error." + message));
			saveErrors(request, errors);
		}
		return mapping.findForward(target);
	}
}
