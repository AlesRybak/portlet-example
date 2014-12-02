package eu.ibacz.edu;

import javax.portlet.*;
import java.io.IOException;

/**
 * @author Ales Rybak (ales.rybak@ibacz.eu)
 */
public class SessionConsumerPortlet extends GenericPortlet {


    public static final String ATTR_NUMBER_FROM_SESSION = "numberFromSession";

    @Override
    protected void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        PortletSession session = request.getPortletSession();
        Long number = (Long)session.getAttribute(SharedConstants.SESS_SELECTED_NUMBER, PortletSession.APPLICATION_SCOPE);
        request.setAttribute(ATTR_NUMBER_FROM_SESSION, number);

        PortletRequestDispatcher prd = getPortletContext().getRequestDispatcher("/WEB-INF/jsp/session-consumer/view.jsp");
        prd.include(request, response);
    }
}
