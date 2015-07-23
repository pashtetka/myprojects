package by.grsu.publication.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import by.grsu.publication.forms.CommentNewForm;
import by.grsu.publication.model.UserSession;
import by.grsu.publication.service.CommentService;
import by.grsu.publication.service.ServiceManager;

public class CommentNewAction extends UserRoleCheckAction {

	private static final Logger logger = Logger
			.getLogger(CommentNewAction.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CommentNewForm commentNewForm = (CommentNewForm) form;
		String target = userChack(request, CommentNewAction.class);
		if (target.equals(ForwardActionTo.ERROR)) {
			CommentService commentService = ServiceManager.getDefault()
					.getCommentService();
			try {
				HttpSession session = request.getSession();
				UserSession userSession = (UserSession) session
						.getAttribute("auth");
				commentService.getNewComment(commentNewForm.getPeriodicalId(),
						userSession.getUserId(), commentNewForm.getComm());
				logger.info("User ID :" + userSession.getUserId()
						+ " added comment to periodical ID: "
						+ commentNewForm.getPeriodicalId());
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		commentNewForm.reset(mapping, request);
		return new ActionForward("/comments.do", true);
	}

}
