<%@ page import="static eu.ibacz.edu.FiboPortlet.*" %>
<%@include file="../init.jspf" %>

<h3>
    <fmt:message key="edit-preferences"/>
</h3>

<c:if test="${successMessage != null}">
    <div class="success"><fmt:message key="${successMessage}" /></div>
</c:if>

<portlet:renderURL portletMode="view" var="backUrl" />
<portlet:actionURL name="<%= ACTION_SAVE_PREFS %>" var="savePrefsActionUrl"/>

<form action="${savePrefsActionUrl}" method="post">
    <fieldset>
        <label for="${ns}count"><fmt:message key="numbersCount-label"/></label>
        <input id="${ns}count" name="<%= PARAM_NUMBERS_COUNT %>" value="${numbersCount}" />
        <c:if test="${errorMessage != null}">
            <div class="error"><fmt:message key="${errorMessage}" /></div>
        </c:if>
    </fieldset>

    <input type="submit" value="<fmt:message key="save"/>"/>
    <a href="${backUrl}"><fmt:message key="back"/></a>
</form>
