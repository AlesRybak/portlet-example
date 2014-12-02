package eu.ibacz.edu;

import javax.portlet.*;
import java.io.IOException;

/**
 * @author Ales Rybak (ales.rybak@ibacz.eu)
 */
public class PrpConsumerPortlet extends GenericPortlet {

    public static final String PARAM_SELECTED_NUMBER = "selectedNumber";
    public static final String ATTR_NUMBER_FROM_PRP = "numberFromPrp";

    @Override
    protected void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        String numberStr = request.getParameter(PARAM_SELECTED_NUMBER);
        request.setAttribute(ATTR_NUMBER_FROM_PRP, numberStr);

        PortletRequestDispatcher prd = getPortletContext().getRequestDispatcher("/WEB-INF/jsp/prp-consumer/view.jsp");
        prd.include(request, response);
    }
}
