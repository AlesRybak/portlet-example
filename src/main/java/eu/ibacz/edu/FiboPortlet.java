package eu.ibacz.edu;

import javax.portlet.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ales Rybak (ales.rybak@ibacz.eu)
 */
public class FiboPortlet extends GenericPortlet {

    public static final String ATTR_FIBO_NUMBERS = "fiboNumbers";
    public static final String ATTR_NUMBERS_COUNT = "numbersCount";

    public static final String PREF_NUMBERS_COUNT = "numbersCount";
    public static final String DEFAULT_PREF_NUMBERS_COUNT = "10";

    @Override
    protected void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        request.setAttribute(ATTR_FIBO_NUMBERS, getFiboNumbers());
        request.setAttribute(ATTR_NUMBERS_COUNT, getNumbersCount(request));

        PortletRequestDispatcher prd = getPortletContext().getRequestDispatcher("/WEB-INF/jsp/fibo/view.jsp");
        prd.include(request, response);
    }

    @Override
    protected void doEdit(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        request.setAttribute(ATTR_FIBO_NUMBERS, getFiboNumbers());

        PortletRequestDispatcher prd = getPortletContext().getRequestDispatcher("/WEB-INF/jsp/fibo/edit.jsp");
        prd.include(request, response);
    }

    private long getNumbersCount(RenderRequest request) {
        PortletPreferences prefs = request.getPreferences();
        String numbersCountStr = prefs.getValue(PREF_NUMBERS_COUNT, DEFAULT_PREF_NUMBERS_COUNT);
        long numbersCount = 0;

        try {
            numbersCount = Long.parseLong(numbersCountStr);
        } catch (NumberFormatException nfe) {
            // intentionally left empty
        }

        return numbersCount;
    }

    private List<Long> getFiboNumbers() {
        ArrayList<Long> numbers = new ArrayList<Long>();
        numbers.add(1L);
        numbers.add(1L);
        numbers.add(2L);
        numbers.add(3L);
        numbers.add(5L);
        numbers.add(8L);
        numbers.add(13L);
        numbers.add(21L);
        numbers.add(34L);
        numbers.add(55L);
        numbers.add(89L);
        numbers.add(144L);
        numbers.add(233L);
        numbers.add(377L);
        numbers.add(610L);
        numbers.add(987L);
        numbers.add(1597L);
        numbers.add(2584L);
        numbers.add(4181L);
        numbers.add(6765L);
        return numbers;
    }

}
