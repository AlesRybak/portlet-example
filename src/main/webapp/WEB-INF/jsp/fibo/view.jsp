<%@include file="../init.jspf" %>

<h3>
    <fmt:message key="first-n-fibo-numbers">
        <fmt:param value="${numbersCount}" />
    </fmt:message>
</h3>

<ul>
    <c:forEach begin="0" end="${numbersCount - 1}" var="number">
        <li>${fiboNumbers.get(number)}</li>
    </c:forEach>
</ul>