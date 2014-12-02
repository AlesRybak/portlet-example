package eu.ibacz.edu;

import javax.portlet.*;
import javax.xml.namespace.QName;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ales Rybak (ales.rybak@ibacz.eu)
 */
public class FiboPortlet extends GenericPortlet {

    public static final long MAX_NUMBERS_COUNT = 20;

    public static final String ATTR_FIBO_NUMBERS = "fiboNumbers";
    public static final String ATTR_NUMBERS_COUNT = "numbersCount";
    public static final String ATTR_ERROR_MESSAGE = "errorMessage";
    public static final String ATTR_SUCCESS_MESSAGE = "successMessage";

    public static final String PREF_NUMBERS_COUNT = "numbersCount";
    public static final String DEFAULT_PREF_NUMBERS_COUNT = "10";

    public static final String ACTION_SAVE_PREFS = "savePrefs";
    public static final String ACTION_SPREAD_NUMBER = "spreadNumber";

    public static final String PARAM_NUMBERS_COUNT = "count";
    public static final String PARAM_SELECTED_NUMBER = "selectedNumber";

    public static final String SESS_ERROR_MESSAGE = "errorMessage";
    public static final String SESS_SUCCESS_MESSAGE = "successMessage";


    @Override
    protected void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        populateDefaultAttributes(request);

        PortletRequestDispatcher prd = getPortletContext().getRequestDispatcher("/WEB-INF/jsp/fibo/view.jsp");
        prd.include(request, response);
    }

    @Override
    protected void doEdit(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        populateDefaultAttributes(request);

        PortletRequestDispatcher prd = getPortletContext().getRequestDispatcher("/WEB-INF/jsp/fibo/edit.jsp");
        prd.include(request, response);
    }

    @ProcessAction(name = ACTION_SPREAD_NUMBER)
    public void spreadNumber(ActionRequest request, ActionResponse response) throws PortletException, IOException {
        Long selectedNumber = getNumberFromParameters(request);
        spreadNumberViaSession(request, selectedNumber);
        spreadNumberViaEvent(response, selectedNumber);
    }

    @ProcessAction(name = ACTION_SAVE_PREFS)
    public void processSavePrefs(ActionRequest request, ActionResponse response) throws PortletException, IOException {
        String countStr = request.getParameter(PARAM_NUMBERS_COUNT);
        PortletSession session = request.getPortletSession();

        if (countStr == null) {
            session.setAttribute(SESS_ERROR_MESSAGE, "null-parameter");
        } else {
            long numbersCount = 0;
            try {
                numbersCount = Long.parseLong(countStr);
            } catch (NumberFormatException nfe) {
                session.setAttribute(SESS_ERROR_MESSAGE, "type-mismatch");
            }

            if (numbersCount < 0) {
                numbersCount = 0;
                session.setAttribute(SESS_ERROR_MESSAGE, "number-too-low");
            }

            if (numbersCount > MAX_NUMBERS_COUNT) {
                numbersCount = MAX_NUMBERS_COUNT;
                session.setAttribute(SESS_ERROR_MESSAGE, "number-too-high");
            }

            PortletPreferences prefs = request.getPreferences();
            prefs.setValue(PREF_NUMBERS_COUNT, Long.toString(numbersCount));
            prefs.store();
            session.setAttribute(SESS_SUCCESS_MESSAGE, "preferences-saved");
        }
    }

    private void populateDefaultAttributes(RenderRequest request) {
        request.setAttribute(ATTR_FIBO_NUMBERS, getFiboNumbers());
        request.setAttribute(ATTR_NUMBERS_COUNT, getNumbersCount(request));

        PortletSession session = request.getPortletSession();
        if (session.getAttribute(SESS_ERROR_MESSAGE) != null) {
            request.setAttribute(ATTR_ERROR_MESSAGE, session.getAttribute(SESS_ERROR_MESSAGE));
            session.removeAttribute(SESS_ERROR_MESSAGE);
        }
        if (session.getAttribute(SESS_SUCCESS_MESSAGE) != null) {
            request.setAttribute(ATTR_SUCCESS_MESSAGE, session.getAttribute(SESS_SUCCESS_MESSAGE));
            session.removeAttribute(SESS_SUCCESS_MESSAGE);
        }
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

    private void spreadNumberViaSession(ActionRequest request, Long number) {
        PortletSession session = request.getPortletSession();
        session.setAttribute(SharedConstants.SESS_SELECTED_NUMBER, number, PortletSession.APPLICATION_SCOPE);
    }

    private void spreadNumberViaEvent(ActionResponse response, Long number) {
        QName name = new QName("http://edu.ibacz.eu/events", "numberSelectedEvent");
        response.setEvent(name, number);
    }

    private long getNumberFromParameters(PortletRequest request) {
        String numberStr = request.getParameter(PARAM_SELECTED_NUMBER);
        if (numberStr != null) {
            try {
                return Long.parseLong(numberStr);
            } catch (NumberFormatException nfe) {
                return -1;
            }
        } else {
            return -1;
        }
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
