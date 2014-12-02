<%@include file="../init.jspf" %>

<c:if test="${numberFromEvent != null}">
    <fmt:message key="event-received-number-is">
        <fmt:param value="${numberFromEvent}" />
    </fmt:message>
</c:if>

<c:if test="${numberFromEvent == null}">
    <fmt:message key="nothing-here"/>
</c:if>
