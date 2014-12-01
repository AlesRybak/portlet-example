package eu.ibacz.edu;

import javax.portlet.*;
import java.io.IOException;

/**
 * @author Ales Rybak (ales.rybak@ibacz.eu)
 */
public class SimplePortlet extends GenericPortlet {

    @Override
    protected void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        PortletRequestDispatcher prd = getPortletContext().getRequestDispatcher("/view.jsp");
        prd.include(request, response);
    }
}
