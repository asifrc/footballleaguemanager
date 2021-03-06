<%@include file="partials/libraryHeader.jsp" %>
<c:set var="pageTitle" scope="request" value="Trade Players"/>
<%@include file="partials/header.jsp" %>

<h1>Trade Players</h1>

<%@include file="partials/playerTable.jsp" %>

<c:if test="${not empty players}">
    <form:form method="POST" id="trade-players-form" action="/trade">
        <input type="submit" id="player-trade-button" value="Trade Players!">
    </form:form>
</c:if>

<%@include file="partials/footer.jsp" %>
