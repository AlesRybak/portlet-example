<%@include file="../init.jspf" %>

<h3><fmt:message key="first-20-fibo-numbers" /></h3>

<ul>
    <c:forEach items="${fiboNumbers}" var="number">
        <li>${number}</li>
    </c:forEach>
</ul>