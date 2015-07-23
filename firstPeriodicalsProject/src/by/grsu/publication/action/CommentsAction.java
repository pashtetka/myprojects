package by.grsu.publication.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import by.grsu.publication.forms.CommentsForm;
import by.grsu.publication.model.Comments;
import by.grsu.publication.model.IPeriodical;
import by.grsu.publication.service.CommentService;
import by.grsu.publication.service.PeriodicalService;
import by.grsu.publication.service.ServiceManager;

public class CommentsAction extends UserRoleCheckAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ActionMessages errors = new ActionMessages();
		CommentsForm commentsForm = (CommentsForm) form;
		String target = userChack(request, CommentsAction.class);

		IPeriodical periodical = null;
		List<Comments> comments = null;
		PeriodicalService periodicalService = ServiceManager.getDefault()
				.getPeriodicalService();
		CommentService commentService = ServiceManager.getDefault()
				.getCommentService();

		periodical = periodicalService.getPeriodicalsById(commentsForm
				.getPeriodicalId());
		request.setAttribute("per", periodical);
		request.setAttribute("perId", commentsForm.getPeriodicalId());

		comments = commentService.getPeriodicalComment(commentsForm
				.getPeriodicalId());
		request.setAttribute("comments", comments);
		if (target.equals(ForwardActionTo.ERROR)) {
			target = ForwardActionTo.SUCCESS;
		}

		if (target.equals(ForwardActionTo.GUEST)) {
			errors.add("guestComment", new ActionMessage("error.guestComment"));
			saveErrors(request, errors);
		}
		return mapping.findForward(target);
	}

}
