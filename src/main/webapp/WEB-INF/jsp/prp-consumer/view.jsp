<%@include file="../init.jspf" %>

<c:if test="${numberFromPrp != null}">
    <fmt:message key="prp-received-number-is">
        <fmt:param value="${numberFromPrp}" />
    </fmt:message>
</c:if>

<c:if test="${numberFromPrp == null}">
    <fmt:message key="nothing-here"/>
</c:if>
