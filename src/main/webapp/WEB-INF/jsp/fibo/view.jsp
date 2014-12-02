<%@ page import="static eu.ibacz.edu.FiboPortlet.*" %>
<%@include file="../init.jspf" %>

<h3>
    <fmt:message key="first-n-fibo-numbers">
        <fmt:param value="${numbersCount}" />
    </fmt:message>
</h3>

<ul>
    <c:forEach begin="0" end="${numbersCount - 1}" var="number">
        <portlet:actionURL name="<%= ACTION_SPREAD_NUMBER %>" var="spreadUrl">
            <portlet:param name="<%= PARAM_SELECTED_NUMBER %>" value="${fiboNumbers.get(number)}"/>
        </portlet:actionURL>

        <li>
            <a href="${spreadUrl}">
                ${fiboNumbers.get(number)}
            </a>
        </li>
    </c:forEach>
</ul>