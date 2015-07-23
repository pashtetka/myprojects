package by.grsu.publication.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LanguageSelectAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String lang = request.getParameter("lang");
		if (!"en".equals(lang.toLowerCase())
				&& !"ru".equals(lang.toLowerCase())) {
			lang = "en";
		}

		Locale locale = new Locale(lang);
		String forwardTo = request.getHeader("Referer");

		request.getSession(true).setAttribute(Globals.LOCALE_KEY, locale);
		response.setLocale(locale);

		if (forwardTo == null) {
			forwardTo = "/periodicalsList.do";
		}

		return new ActionForward(forwardTo, true);
	}
}
