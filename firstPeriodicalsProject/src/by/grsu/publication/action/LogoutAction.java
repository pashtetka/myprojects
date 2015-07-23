package by.grsu.publication.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LogoutAction extends Action {

	private static final Logger logger = Logger.getLogger(LogoutAction.class);

	private static final String LOGOUT_ERROR = "Could not logout: ";

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession(true);

		String target = ForwardActionTo.ERROR;
		logger.info("logging out...");

		try {
			session.removeAttribute("auth");
			session.invalidate();
			target = "/loginTile.jsp";

		} catch (Exception ex) {
			target = ForwardActionTo.ERROR;
			logger.error(LogoutAction.LOGOUT_ERROR + ex.getMessage());

		}

		return new ActionForward(target, true);
	}

}
