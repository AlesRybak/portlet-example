package eu.ibacz.edu;

import javax.portlet.*;
import java.io.IOException;

/**
 * @author Ales Rybak (ales.rybak@ibacz.eu)
 */
public class EventConsumerPortlet extends GenericPortlet {

    public static final String PARAM_SELECTED_NUMBER = "selectedNumber";
    public static final String ATTR_NUMBER_FROM_EVENT = "numberFromEvent";

    @ProcessEvent(qname="{http://edu.ibacz.eu/events}numberSelectedEvent")
    public void processNumberSelectedEvent(EventRequest request, EventResponse response) throws PortletException, IOException {
        Event e = request.getEvent();
        Long number = (Long)e.getValue();
        response.setRenderParameter(PARAM_SELECTED_NUMBER, Long.toString(number));
    }

    @Override
    protected void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        String numberStr = request.getParameter(PARAM_SELECTED_NUMBER);
        request.setAttribute(ATTR_NUMBER_FROM_EVENT, numberStr);

        PortletRequestDispatcher prd = getPortletContext().getRequestDispatcher("/WEB-INF/jsp/event-consumer/view.jsp");
        prd.include(request, response);
    }
}
