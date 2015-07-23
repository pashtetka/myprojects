package by.grsu.publication.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import by.grsu.publication.forms.CommentDeleteForm;
import by.grsu.publication.service.CommentService;
import by.grsu.publication.service.ServiceManager;

public class CommentDeleteAction extends UserRoleCheckAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CommentDeleteForm commentDeleteForm = (CommentDeleteForm) form;
		CommentService commentService = ServiceManager.getDefault()
				.getCommentService();
		String target = adminChack(request, CommentDeleteAction.class);
		if (target.equals(ForwardActionTo.ERROR)) {
			commentService.getDeleteCommentBuId(commentDeleteForm.getId());
			target = ForwardActionTo.SUCCESS;
		}
		return mapping.findForward(target);
	}

}
