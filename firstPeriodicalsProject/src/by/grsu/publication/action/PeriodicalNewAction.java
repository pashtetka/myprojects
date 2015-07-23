package by.grsu.publication.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

import by.grsu.publication.exception.LargeSizePicturesException;
import by.grsu.publication.forms.PeriodicalNewForm;
import by.grsu.publication.service.PeriodicalService;
import by.grsu.publication.service.ServiceManager;

public class PeriodicalNewAction extends UserRoleCheckAction {

	private static final Logger logger = Logger
			.getLogger(PeriodicalNewAction.class);

	private static final String PERIODICAL_NEW_ERROR = "Could not added new periodical: ";

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PeriodicalNewForm periodicalNewForm = (PeriodicalNewForm) form;
		ActionErrors actionErrors = new ActionErrors();
		String periodicalName = periodicalNewForm.getPeriodicalName();
		int cost = periodicalNewForm.getCost();
		int outputsInMonth = periodicalNewForm.getOutputsInMonth();
		String topic = periodicalNewForm.getTopic();
		FormFile image = periodicalNewForm.getImage();
		PeriodicalService serviceManager = ServiceManager.getDefault()
				.getPeriodicalService();
		String target = adminChack(request, PeriodicalNewAction.class);
		if (target.equals(ForwardActionTo.ERROR)) {
			try {
				serviceManager.getPeriodicalNew(periodicalName, cost,
						outputsInMonth, topic, image);
				target = ForwardActionTo.SUCCESS;
				logger.info("added new periodical. Name: " + periodicalName
						+ " Cost: " + cost + " Outputs In Month: "
						+ outputsInMonth + " Topic: " + topic);
				periodicalNewForm.reset(mapping, request);
			} catch (LargeSizePicturesException e) {
				actionErrors.add("largeSizePictures", new ActionMessage(
						"error.largeSizePictures"));
				saveErrors(request, actionErrors);
			} catch (Exception e) {
				logger.error(PeriodicalNewAction.PERIODICAL_NEW_ERROR
						+ e.getMessage());
			}
		}
		return mapping.findForward(target);

	}
}
