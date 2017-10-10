<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<h1>Отчеты</h1>
<hr>
<c:if test="${empty efs && empty lates && empty earlies}">

            <p>По данному пользователю нет отчетов!</p>

</c:if>

<c:if test="${not empty efs}">
    <p>Эффективность</p>
    <%@include file="tasks.jsp" %>
</c:if>
<c:if test="${not empty lates}">
    <p>Опоздания</p>
    <%@include file="lates1.jsp" %>
</c:if>
<c:if test="${not empty earlies}">
    <p>Ранние уходы</p>
    <%@include file="lates2.jsp" %>
</c:if>
