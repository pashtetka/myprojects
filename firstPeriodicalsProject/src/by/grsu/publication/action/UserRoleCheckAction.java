package by.grsu.publication.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;

import by.grsu.publication.exception.NoAdminException;
import by.grsu.publication.exception.NullSessionException;
import by.grsu.publication.model.UserSession;

public class UserRoleCheckAction extends Action {

	@SuppressWarnings("rawtypes")
	public String adminChack(HttpServletRequest request, Class clazz)
			throws NoAdminException, NullSessionException {

		final Logger logger = Logger.getLogger(clazz);
		String target = ForwardActionTo.ERROR;
		HttpSession session = request.getSession();
		UserSession userSession = (UserSession) session.getAttribute("auth");
		try {
			if (userSession == null) {
				throw new NullSessionException("null session");
			} else {
				if (!"admin".equals(userSession.getUserRole())) {
					throw new NoAdminException("no admin");
				}
			}
		} catch (NoAdminException e) {
			target = ForwardActionTo.GUEST;
			logger.error(e.getMessage());
		} catch (NullSessionException e) {
			target = ForwardActionTo.GUEST;
			logger.error(e.getMessage());
		}
		return target;
	}

	@SuppressWarnings("rawtypes")
	public String userChack(HttpServletRequest request, Class clazz)
			throws NullSessionException {

		final Logger logger = Logger.getLogger(clazz);
		String target = ForwardActionTo.ERROR;
		HttpSession session = request.getSession();
		UserSession userSession = (UserSession) session.getAttribute("auth");
		try {
			if (userSession == null) {
				throw new NullSessionException("null session");
			}
		} catch (NullSessionException e) {
			target = ForwardActionTo.GUEST;
			logger.error(e.getMessage());
		}
		return target;
	}
}
